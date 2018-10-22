package com.masaiqi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication//SpringBoot的核心注解，它是一个组合注解，该注解组合了：@Configuration、@EnableAutoConfiguration、@ComponentScan；若不是用 @SpringBootApplication 注解也可以使用这三个注解代替。
@EnableTransactionManagement//开启事务管理,之后就可以在service层使用@Transactional注解声明事务
@MapperScan("com.masaiqi.mapper")//与dao层的@Mapper二选一写上即可(主要作用是扫包)
public class ZhongzhiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhongzhiApplication.class, args);
    }
}
