package com.yoons.managediet.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenApiRecipeSearchService {

    private final RestTemplate restTemplate;

    public void requestRecipeSearch() {



    }


}
