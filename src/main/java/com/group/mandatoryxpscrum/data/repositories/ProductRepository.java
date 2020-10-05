package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
