package com.project3.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PtaContactController {

    @GetMapping("/contact")
    public String contactPage(Model model) {
        return "PtaContact";   // resources/templates/PtaContact.html
    }
}