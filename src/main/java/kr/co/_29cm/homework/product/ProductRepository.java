package kr.co._29cm.homework.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByProductNo(String productNo);

    Optional<Product> findByProductNo(String productNo);
}
