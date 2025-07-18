package com.example.dk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")
public class Docker {
    @GetMapping
    public String saludo(){
        return "Bienvenido a docker";
    }
}
