package com.arobot.assignment.service.impl;

import com.arobot.assignment.entity.Robot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RobotService {

    private RestTemplate restTemplate;

    private static final String URL = "https://robotstakeover20210903110417.azurewebsites.net/robotcpu";

    @Autowired
    public RobotService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Robot> findAllRobotsAndDetails() {
        ResponseEntity<List<Robot>> responseEntity = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Robot>>() { });
        List<Robot> robotList = responseEntity.getBody().stream()
                .sorted(Comparator.comparing(Robot::getCategory)
                        .thenComparing(Robot::getModel))
                .collect(Collectors.toList());

        return robotList != null ? robotList : new ArrayList<>();
    }
}
