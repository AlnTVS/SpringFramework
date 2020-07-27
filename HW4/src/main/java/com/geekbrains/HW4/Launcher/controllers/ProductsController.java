package com.geekbrains.HW4.Launcher.controllers;

import com.geekbrains.HW4.Launcher.model.Product;
import com.geekbrains.HW4.Launcher.repositories.specification.ProductSpecifications;
import com.geekbrains.HW4.Launcher.sevices.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) { this.productsService = productsService; }

    @GetMapping
    public String showAllProducts(Model model) {
        List<Product> products = productsService.getAll();
        model.addAttribute("products",products);
        return "all_products";
    }

    @GetMapping("/filter")
    public String findProductsByMinPrice(Model model,
                                            @RequestParam(name = "min_price", required = false) Integer minPrice,
                                            @RequestParam(name = "max_price", required = false) Integer maxPrice) {
        Specification<Product> spec = Specification.where(null);

        if(minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGEThan(minPrice));
        }
        if(maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLEThan(maxPrice));
        }

        List<Product> products = productsService.findAll(spec);
        model.addAttribute("products", products);
        return "all_products";
    }
}
