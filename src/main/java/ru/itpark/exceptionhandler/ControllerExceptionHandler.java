package ru.itpark.exceptionhandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itpark.exception.PageNotFoundException;
import ru.itpark.exception.UsernameAlreadyExistsException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public String handlePageNotFound(
            Model model, UsernameAlreadyExistsException e
    ) {
    model.addAttribute("message", e.getMessage());
    return "user-exists";
    }
}




