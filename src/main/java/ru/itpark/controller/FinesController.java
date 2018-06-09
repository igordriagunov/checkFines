package ru.itpark.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.domain.Account;
import ru.itpark.service.FineService;

import java.util.Map;

@Controller
@RequestMapping("/")

public class FinesController {

    private final FineService fineService;

    public FinesController(FineService fineService) {
        this.fineService = fineService;
    }


    @GetMapping("all-fines")
//    @PreAuthorize("hasRole('ADMIN')")
    public String findAll(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("account", account);
        model.addAttribute("fines", fineService.findAllFines());
        return "/f";
    }

//    @GetMapping("/registration")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String registration(Map<String, Account> map) {
//        return "pages/registration";
//    }

    @GetMapping("registration")
//    @PreAuthorize("hasRole('ADMIN')")
    public String getRegistration(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("account", account);
        return "pages/registration";
    }


    @PostMapping("registration")
//    @PreAuthorize("hasRole('ADMIN')")
    public String completeRegistration(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("account", account);
        return "login";
    }


    @PostMapping(params = {"username", "eMail", "password"})
//    @PreAuthorize("hasRole('ADMIN')")
    public String reg(
            @RequestParam("username") String username,
            @RequestParam("eMail") String eMail,
            @RequestParam("password") String password,
            Model model) {
        model.addAttribute("username",username);
        model.addAttribute("eMail",eMail);
        model.addAttribute("password",password);

        return "pages/index";
    }





    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public String indexPage(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("account", account);
        return "pages/index";
    }

    @GetMapping(params = {"carNum", "regNum"})
    public String find(@RequestParam String carNum, String regNum, Model model) {
        model.addAttribute("fines", fineService.findByCarNumber(carNum, regNum));
        model.addAttribute("carNum", carNum);
        model.addAttribute("regNum", regNum);
        return "pages/index";
    }

}
