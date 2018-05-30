package ru.itpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itpark.service.FineService;

@Controller
@RequestMapping("/")

public class FinesController {

    private final FineService fineService;

    public FinesController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping
    public String indexPage() {
        return "index";
    }

    @GetMapping("/{carNum}")
    public String get(@PathVariable String carNum, Model model) {
        model.addAttribute("fines", fineService.findByCarNumber(carNum));
        return "fines";
    }

}
