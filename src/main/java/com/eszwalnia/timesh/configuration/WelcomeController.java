package com.eszwalnia.timesh.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class WelcomeController {

    @GetMapping("/")
    public String redirectToUi() {
        return "redirect:/swagger-ui.html#/";
    }
}
