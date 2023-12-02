package com.tkgroupbd.pusti.api.controllers.errors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "Error Manager")
@RequestMapping(value = "/error")
public class ErrorController {

    @GetMapping(value = "/404")
    public String handle404() {
        return "error/404";
    }

    @GetMapping(value = { "/", "/500", "" })
    public String handleGeneralError() {
        return "error/500";
    }

    @GetMapping(value = "/accessdenied")
    public String handleAccessDenied() {
        return "error/accessdenied";
    }
}
