package com.s2daw.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @RequestMapping(value="prueba")
    public List<String> prueba(){
        return List.of("prueba","prueba2","prueba3");
    }
}
