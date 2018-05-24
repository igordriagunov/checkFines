package ru.itpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fines")
public class FinesController {

    @GetMapping
    public String welcome() {
        return "fines";
    }

}
