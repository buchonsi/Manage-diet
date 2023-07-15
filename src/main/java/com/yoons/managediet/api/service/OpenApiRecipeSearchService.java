package com.yoons.managediet.api.service;

import com.yoons.managediet.api.dto.OpenApiParamDto;
import com.yoons.managediet.api.dto.OpenApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenApiRecipeSearchService {

    private static final String API_CODE = "COOKRCP01";
    private final WebClient webClient;
    private final OpenApiBuilderService openApiBuilderService;

    public void requestRecipeSearch(OpenApiParamDto openApiParamDto) {

        URI uri = openApiBuilderService.buildUriByRecipeName(openApiParamDto);

        Map<String, OpenApiResponseDto> responseDtoMap = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, OpenApiResponseDto>>() {})
                .block();

        //@todo null 처리
        OpenApiResponseDto openApiResponseDto = responseDtoMap.get(API_CODE);

//        log.info("[OpenApiRecipeSearchService requestRecipeSearch] result = {}", openApiResponseDto.getRawDto().get(0).getRecipeName());
    }


}
