package com.vt.spring_file_upload.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping(value = {"/api-doc", "/api-docs"})
    public RedirectView openApi() {
        return new RedirectView("/swagger-ui/index.html");
    }
}
