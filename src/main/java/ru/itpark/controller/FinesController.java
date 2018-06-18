package ru.itpark.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.domain.Account;
import ru.itpark.domain.Car;
import ru.itpark.service.AccountServiceImpl;
import ru.itpark.service.CarService;
import ru.itpark.service.FineService;

@Controller
@RequestMapping("/")

public class FinesController {

    private final FineService fineService;
    private final AccountServiceImpl accountService;
    private final CarService carService;

    public FinesController(FineService fineService, AccountServiceImpl accountService, CarService carService) {
        this.fineService = fineService;
        this.accountService = accountService;
        this.carService = carService;
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

    @GetMapping("registration")
//    @PreAuthorize("hasRole('ADMIN')")
    public String getRegistration() {

        return "pages/registration";
    }


    @PostMapping("registration")
//    @PreAuthorize("hasRole('ADMIN')")
    public String completeRegistration(@RequestParam String username, @RequestParam String password, @RequestParam String eMail) {
        accountService.createAccount(username, password, eMail);

        return "redirect:/login";
    }

    @GetMapping("my-car")
    public String getFormAddMyCar(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("account", account);
        model.addAttribute("cars", carService.findAllByAccountId(account.getId()));

        return "pages/my-car";
    }

    @PostMapping("my-car")
    public String addMyCar(@ModelAttribute Car car, @AuthenticationPrincipal Account account) {
        car.setAccount(account);
        carService.save(car);

        return "redirect:/my-car";
    }

    //FIXME: carNumber вместо id ?
    @GetMapping("my-car/{id}")
    public String getCar(@PathVariable int id, Model model) {
        model.addAttribute("car", carService.findById(id));

        return "pages/car";
    }

    @PostMapping("/{id}/remove")
    public String remove(@PathVariable int id) {
        carService.deleteById(id);

        return "redirect:/my-car";
    }




}
