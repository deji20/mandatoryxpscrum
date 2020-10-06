package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.services.ProductService;
import com.group.mandatoryxpscrum.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("product", new Product());
        return "/manager/products";
    }

    @PostMapping("/manager/products/new")
    public String addProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/manager/products/";
    }

    @GetMapping("/manager/products/delete")
    public String deleteProduct(@RequestParam("id") String productId){
        Product product = productService.fetchById(Integer.parseInt(productId));
        productService.delete(product);
        return "redirect:/manager/products/";
    }
}

