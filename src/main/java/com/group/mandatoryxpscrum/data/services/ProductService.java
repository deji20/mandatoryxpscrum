package com.group.mandatoryxpscrum.data.services;

import com.group.mandatoryxpscrum.data.repositories.ProductRepository;
import com.group.mandatoryxpscrum.models.Equipment;
import com.group.mandatoryxpscrum.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public void save(Product product){productRepository.save(product);}
    public Product fetchById(int id){
        Optional<Product> optional = productRepository.findById(id);
        return optional.get();
    }
    public void delete(Product product){
        productRepository.delete(product);
        //equipmentRepository.deleteById(equipment.getId());
    }

}
