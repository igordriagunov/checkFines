package ru.itpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itpark.service.FineService;

@Controller
@RequestMapping("/")

public class FinesController {

    private final FineService fineService;

    public FinesController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping("all-fines")
    public String findAll(Model model) {
        model.addAttribute("fines",fineService.findAllFines());
        return "fines";
    }

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("fines", "");
        return "index";
    }

    @GetMapping(params = "carNum")
    public String find(@RequestParam String carNum, Model model) {
        model.addAttribute("fines", fineService.findByCarNumber(carNum));
        model.addAttribute("carNum", carNum);
        return "fines";
    }

}
