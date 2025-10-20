package com.poly.lab7.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.lab7.entity.Product;
import com.poly.lab7.entity.Report;

public interface ProductDAO extends JpaRepository<Product, Integer> {

    // JPQL
    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
    List<Product> findByPrice(double minPrice, double maxPrice);

    @Query("FROM Product o WHERE o.name LIKE ?1")
    Page<Product> findByKeywords(String keywords, Pageable pageable);

    @Query("SELECT o.category AS group, SUM(o.price) AS sum, COUNT(o) AS count " +
            "FROM Product o GROUP BY o.category ORDER BY SUM(o.price) DESC")
    List<Report> getInventoryByCategory();

    // DSL
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);
}
