package com.project3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class PtaCartController {

    @GetMapping("/cart")
    public String cartPage() {
        return "PtaCart";  // PtaCart.html
    }
}