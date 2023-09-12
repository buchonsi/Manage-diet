package com.yoons.managediet.api.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoons.managediet.api.dto.OpenApiParamDto;
import com.yoons.managediet.config.KeyMappingConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenApiBuilderService {

    private final KeyMappingConfig keyMappingConfig;
    private final ObjectMapper objectMapper;

    private static final String BASE_URL = "https://openapi.foodsafetykorea.go.kr/api/";
    private static final String RECIPE_API_CODE = "COOKRCP01";
    private static final String RETURN_TYPE = "json";

    public URI buildUriByRecipeName(int startIdx, int endIdx, OpenApiParamDto openApiParamDto) {
        String params = "";
        if (!Objects.isNull(openApiParamDto)) {
            params = buildParamPath(openApiParamDto);
        }

        URI uri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment(keyMappingConfig.getOpenApi(), RECIPE_API_CODE, RETURN_TYPE, String.valueOf(startIdx), String.valueOf(endIdx), params)
                .build().toUri();

        log.info("[OpenApiBuilderService buildUriByRecipeName] uri : {}", uri);
        return uri;
    }

    private String buildParamPath(OpenApiParamDto openApiParamDto) {
        StringBuilder stringBuilder = new StringBuilder();

        Map<String, String> params = objectMapper.convertValue(openApiParamDto, new TypeReference<>(){});

        for (String key : params.keySet()) {
            if (params.get(key) == null) {
                continue;
            }
            stringBuilder.append(key)
                    .append("=")
                    .append(params.get(key))
                    .append("/");
        }
        return stringBuilder.toString();
    }
}
