package com.masaiqi.interceptor;

import com.masaiqi.annotation.AuthToken;
import com.masaiqi.kit.ConstantKit;
import com.masaiqi.kit.StringKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 权限拦截器实现
 * 如果使用了AutoToken注解，则需要对其进行鉴权
 * redis相关存储信息:
 * 键token + username 值token生成日期
 * 键token            值username
 * 键username         值token
 */
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    //存放鉴权信息的Header名称,默认是Authorization
    private String httpHeaderName = "Authorization";

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    //从yml配置文件中读取redis配置
    @Value("${my-jedis.host}")
    private String jedis_host;
    @Value("${my-jedis.port}")
    private int jedis_port;

//    /**
//     * 存放登录用户模型Key的Request Key
//     */
//    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";
//
    /**
     * HandlerInterceptor接口拥有三个default类型方法 这里只使用了preHandle方法
     * 那么,为什么这里接口实现类可以不全部实现？
     * jdk8新特性 接口可以增加default类型方法/static方法
     * default方法:子类继承可以直接使用,不满足业务需求亦可以重写 通过实现类的对象实例进行调用
     * static方法:子类不继承此关键词标识方法 通过接口类调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * HandlerMethod 封装方法定义相关的信息,如类,方法,参数等.
         * 非此类实例 直接跳出 避免转型风险
         */
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //如果方法或者类打上了AuthToken注解则需要验证token
        if(method.getAnnotation(AuthToken.class) != null
                || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null){
            String token = request.getHeader(httpHeaderName);
            log.info("token is {}", token);
            String username = "";
            /**
             * jdk 1.7新特性 try(){}资源自动关闭
             */
            try(Jedis jedis = new Jedis(jedis_host, jedis_port)){
                if (!StringUtils.isEmpty(token)){
                    username = jedis.get(token);
                    log.info("username is {}", username);
                }
                if (!StringKit.isEmptyExcludeTrim(username)){
                    Long tokeBirthTime = Long.valueOf(jedis.get(token + username));
                    log.info("token Birth time is: {}", tokeBirthTime);
                    Long diff = System.currentTimeMillis() - tokeBirthTime;
                    log.info("token is exist : {} ms", diff);
                    if (diff > ConstantKit.TOKEN_RESET_TIME){
                        jedis.expire(username, ConstantKit.TOKEN_EXPIRE_TIME);
                        jedis.expire(token, ConstantKit.TOKEN_EXPIRE_TIME);
                        log.info("Reset expire time success!");
                        Long newBirthTime = System.currentTimeMillis();
                        jedis.set(token + username, newBirthTime.toString());
                    }
//                    request.setAttribute(REQUEST_CURRENT_KEY, username);
                    return true;
                }else {
                    try(PrintWriter out = response.getWriter()){
                        JSONObject jsonObject = new JSONObject();
                        response.setStatus(unauthorizedErrorCode);
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                        jsonObject.put("code", ((HttpServletResponse) response).getStatus());
                        jsonObject.put("message", HttpStatus.UNAUTHORIZED);
                        out.println(jsonObject);
                        return false;
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return true;
    }
}
