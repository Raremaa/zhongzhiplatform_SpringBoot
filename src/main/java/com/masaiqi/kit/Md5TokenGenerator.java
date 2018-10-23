package com.masaiqi.kit;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * token生成器的一种实现
 * 使用的是简单的springboot内置MD5转换算法
 */
@Component
public class Md5TokenGenerator implements  TokenGenerator{

    @Override
    public String generate(String... strings) {
        long timestamp = System.currentTimeMillis();
        String tokenMeta = "";
        for(String s: strings){
            tokenMeta = tokenMeta + s;
        }
        tokenMeta = tokenMeta + timestamp;
        String token = DigestUtils.md5DigestAsHex(tokenMeta.getBytes());
        return token;
    }
}
