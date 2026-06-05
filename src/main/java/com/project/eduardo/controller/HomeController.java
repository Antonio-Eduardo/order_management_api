package com.project.eduardo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "")
public class HomeController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> direcionamento() {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Bem-vindo a API de Gerenciamento de Pedidos");
        resposta.put("status", "Online");

        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("usuarios", "/users");
        endpoints.put("produtos", "/product");
        endpoints.put("categorias", "/category");
        endpoints.put("pedidos", "/orders");

        resposta.put("endpoints_disponiveis", endpoints);

        return ResponseEntity.ok().body(resposta);
    }
}