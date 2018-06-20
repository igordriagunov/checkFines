package ru.itpark.exceptionhandler;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itpark.exception.PageNotFoundException;
import ru.itpark.exception.UsernameAlreadyExistsException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public String handleUserExists(
            Model model, UsernameAlreadyExistsException e
    ) {
    model.addAttribute("message", e.getMessage());
    return "pages/user-exists";
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleNotFound(Model model, UsernameNotFoundException e
    ) {
        model.addAttribute("message", e.getMessage());
        return "pages/user-not-found";
    }
}




