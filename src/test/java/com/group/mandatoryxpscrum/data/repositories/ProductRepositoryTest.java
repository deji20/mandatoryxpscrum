package com.group.mandatoryxpscrum.data.repositories;

import com.group.mandatoryxpscrum.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Test
    void injectedComponentsAreNotNull(){
        assertNotNull(repository);
    }

    @Test
    void testFetchByNonExistingId(){
        assertThrows(NoSuchElementException.class, () -> repository.findById(1).get());
    }

    @Test
    void findAllProductsIsNullTest(){
        int expected = 0;

        List<Product> products = repository.findAll();
        int actual = products.size();

        assertEquals(expected, actual);
    }

    @Test
    void productIsSavedTest(){
        int expected = 1;

        Product product = new Product();
        product.setName("Test_Name");
        product.setImage("Test_Image");
        product.setPrice(100);
        repository.save(product);

        int actual = repository.findAll().size();

        assertEquals(expected, actual);
    }


}