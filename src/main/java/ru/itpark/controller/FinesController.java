package ru.itpark.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.domain.Account;
import ru.itpark.domain.Car;
import ru.itpark.repository.CarRepositoryJpa;
import ru.itpark.service.AccountServiceImpl;
import ru.itpark.service.CarServiceJpa;
import ru.itpark.service.FineService;

@Controller
@RequestMapping("/")

public class FinesController {

    private final FineService fineService;
    private final AccountServiceImpl accountService;
    private final CarServiceJpa carService;

    public FinesController(FineService fineService, AccountServiceImpl accountService, CarServiceJpa carService) {
        this.fineService = fineService;
        this.accountService = accountService;
        this.carService = carService;
    }

    @GetMapping("registration")
//    @PreAuthorize("hasRole('ADMIN')")
    public String getRegistration() {
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

    @GetMapping("addMyCar")
    public String getFormAddMyCar(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("account", account);
        model.addAttribute("cars", carService.findAll());
        return "pages/addMyCar";
    }

    @PostMapping("addMyCar")
    public String addMyCar(@ModelAttribute Car car) {
        accountService.addMyCar(car);

        return "pages/addMyCar";
    }

    @GetMapping("/{carNumber}")
    public String getCar(@PathVariable String carNumber, Model model) {
        model.addAttribute("car", carService.findAllByCarNumber(carNumber));
        return "pages/car";
    }


    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public String indexPage(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("account", account);
        return "pages/index";
    }

    @GetMapping(params = {"carNum", "regNum"})
    public String find(@RequestParam String carNum, @RequestParam String regNum, Model model) {
        model.addAttribute("fines", fineService.findFineByCarNumberRegNumber(carNum, regNum));
        model.addAttribute("carNum", carNum);
        model.addAttribute("regNum", regNum);
        return "pages/index";
    }

}
