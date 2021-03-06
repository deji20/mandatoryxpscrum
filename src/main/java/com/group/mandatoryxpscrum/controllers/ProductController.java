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
    public String addToCart(Product product, Integer amount){
        if(amount == null){
            amount = 1;
        }
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

    @PostMapping("/products/cart/emptyCart")
    public String emptyCart(){
        cart = new ArrayList<>();
        return "redirect:/products";
    }

    @GetMapping("/manager/products/")
    public String managerIndex(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("product", new Product());
        return "/manager/products";
    }

    @PostMapping("/manager/products/new")
    public String addProduct(@ModelAttribute Product product) {
        product.setImage("default.jpg");
        productService.save(product);
        return "redirect:/manager/products/";
    }

    @GetMapping("/manager/products/delete")
    public String deleteProduct(@RequestParam("id") String productId){
        Product product = productService.fetchById(Integer.parseInt(productId));
        productService.delete(product);
        return "redirect:/manager/products/";
    }

    @PostMapping("/manager/products/update")
    public String updateProduct(Product product) {
        productService.save(product);
        return "redirect:/manager/products/";
    }

}