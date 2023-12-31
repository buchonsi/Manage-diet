package com.yoons.managediet.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("key")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class KeyMappingConfig {
    private final String openApi;
}
