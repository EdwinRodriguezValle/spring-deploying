package com.rodriguez.novi.controllers;

import com.rodriguez.novi.entities.UserProfileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserProfileControllerTest {

    private TestRestTemplate testRestTemplate; //Esto permite enviar peticiones http

    @Autowired
    private RestTemplateBuilder restTemplateBuilder; // El objeto que nos permitira construir el primero

    @LocalServerPort
    private int port; //Crea un servidor local

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }


    @DisplayName("Comprabar Hola Mundo desde controladores springboot")
    @Test
    void holaMundo() {
        ResponseEntity<String> response  =
                testRestTemplate.getForEntity("/hola", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Hola mundo que tal vamos!!! Hasta luego!", response.getBody());
    }


    @Test
    void findAll() {
        ResponseEntity<UserProfileEntity[]> response =
        testRestTemplate.getForEntity("/api/get/users", UserProfileEntity[].class);

       assertEquals(HttpStatus.OK, response.getStatusCode());
       assertEquals(200, response.getStatusCodeValue());

        List<UserProfileEntity> userProfileEntityList = Arrays.asList(response.getBody());
        System.out.println(userProfileEntityList.size());

    }

    @Test
    void findOneById() {
        ResponseEntity<UserProfileEntity> response =
                testRestTemplate.getForEntity("/api/get/user/50", UserProfileEntity.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "name": "Edmundo",
                    "lastname": "Gonzales",
                    "gender": "Male",
                    "birthday": "1978-08-10",
                    "address": "Camera Obscuradreeft 45",
                    "email": "rodriguez.valle.edwin@gmail.com",
                    "languages": "Spanish",
                    "activeUser": true,
                    "telephone": 638497646,
                    "ocupation": "It professional"
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<UserProfileEntity> response = testRestTemplate.exchange("/api/save/user", HttpMethod.POST, request,UserProfileEntity.class);

        UserProfileEntity result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Edmundo", result.getName());

    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void deleteAll() {
//    }
}