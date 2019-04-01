package com.springinaction.pizza.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = GET)
    public String redirectToFlow() {
        return "redirect:/pizza";
    }

}
