package com.example.clientes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {

    @GetMapping("/test")
    public String pruebaServicio(){
        return "hola";
    }
}
