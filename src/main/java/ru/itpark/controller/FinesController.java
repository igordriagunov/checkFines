package ru.itpark.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.domain.Account;
import ru.itpark.service.AccountService;
import ru.itpark.service.AccountServiceImpl;
import ru.itpark.service.FineService;

@Controller
@RequestMapping("/")

public class FinesController {

    private final FineService fineService;
    private final AccountServiceImpl accountService;

    public FinesController(FineService fineService, AccountServiceImpl accountService) {
        this.fineService = fineService;
        this.accountService = accountService;
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
        //model.addAttribute("account", account);
        //accountService.saveAccount(account);
        return "pages/registration";
    }


    @PostMapping("registration")
//    @PreAuthorize("hasRole('ADMIN')")
    public String completeRegistration(@RequestParam String username, @RequestParam String password, @RequestParam String eMail) {
        accountService.createAccount(username, password, eMail);

        return "redirect:/login";
    }


//    @PostMapping(params = {"username", "eMail", "password"})
//    @PreAuthorize("hasRole('ADMIN')")
//    public String reg(
//            @RequestParam("username") String username,
//            @RequestParam("eMail") String eMail,
//            @RequestParam("password") String password,
//            Model model) {
//        model.addAttribute("username",username);
//        model.addAttribute("eMail",eMail);
//        model.addAttribute("password",password);
//
//        return "pages/index";
//    }


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
