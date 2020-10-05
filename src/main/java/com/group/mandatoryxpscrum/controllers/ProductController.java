package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public String index(Model model){
        model.addAttribute("products", productService.findAll());
        return "product/index";
    }

    @GetMapping("/manager/products/")
    public String managerIndex(Model model) {
        model.addAttribute("products", productService.findAll());
        return "/manager/products";
    }
}
