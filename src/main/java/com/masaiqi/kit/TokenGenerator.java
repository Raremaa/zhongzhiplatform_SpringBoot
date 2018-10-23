package com.masaiqi.kit;

import org.springframework.stereotype.Component;

/**
 * token生成器接口
 */
@Component
public interface TokenGenerator {
    public String generate(String... strings);
}
