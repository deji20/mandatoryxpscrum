package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.services.ProductService;
import com.group.mandatoryxpscrum.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    List<Product> cart = new ArrayList<Product>();

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public String index(Model model){
        model.addAttribute("cart", cart);
        model.addAttribute("products", productService.findAll());
        return "products/index";
    }

    @PostMapping("/products/addtocart")
    public String addToCart(Product product, int amount){
        for(int i = 0; i < amount; i++){
            cart.add(product);
        }
        return "redirect:/products";
    }

    @GetMapping("/products/cart")
    public String viewCart(Model model){
        model.addAttribute("cart", cart);
        Map<Product, Integer> cartMap = new HashMap<>();
        int total = 0;
        for(Product product : cart) {
            if(!cartMap.containsKey(product)){
                cartMap.put(product, 1);
            }
            else {
                cartMap.put(product, cartMap.get(product) + 1);
            }
            total = total + product.getPrice();
        }
        model.addAttribute("cartMap", cartMap);
        model.addAttribute("total", total);
        return "products/cart";
    }

    @GetMapping("/manager/products/")
    public String managerIndex(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("product", new Product());
        return "/manager/products";
    }

    @PostMapping("/manager/products/new")
    public String addEquipment(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/manager/products/";
    }
}

