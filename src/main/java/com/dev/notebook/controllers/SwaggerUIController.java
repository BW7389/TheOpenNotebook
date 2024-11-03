package com.dev.notebook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerUIController {

    @GetMapping("/swagger-ui")
    public String swaggerUi(){
        return "swagger-ui";
    }
}
