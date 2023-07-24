package com.yoons.managediet.api.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoons.managediet.api.dto.OpenApiParamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenApiBuilderService {

    @Value("${OPEN.API.KEY}")
    private String openApiKey;

    private final ObjectMapper objectMapper;

    private static final String BASE_URL = "https://openapi.foodsafetykorea.go.kr/api/";
    private static final String RECIPE_API_CODE = "COOKRCP01";
    private static final String RETURN_TYPE = "json";

    public URI buildUriByRecipeName(String startIdx, String endIdx, OpenApiParamDto openApiParamDto) {
        String params = buildParamPath(openApiParamDto);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(BASE_URL);
        URI uri = uriBuilder.path(openApiKey)
                .pathSegment(RECIPE_API_CODE, RETURN_TYPE, startIdx, endIdx, params)
                .build().toUri();

        log.info(uri.toString());
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
