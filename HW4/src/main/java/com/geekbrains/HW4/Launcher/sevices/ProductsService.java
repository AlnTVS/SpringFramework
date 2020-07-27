package com.geekbrains.HW4.Launcher.sevices;

import com.geekbrains.HW4.Launcher.model.Product;
import com.geekbrains.HW4.Launcher.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> getAll() {
        return productsRepository.findAll();
    }
    public List<Product> findAll(Specification<Product> spec) {
        return productsRepository.findAll(spec);
    }
}
