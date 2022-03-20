package com.taliban.anislasher;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@ResponseBody
public class Controller {

    @RequestMapping("/")
    public String getHomepage() {
        return "home";
    }
}
