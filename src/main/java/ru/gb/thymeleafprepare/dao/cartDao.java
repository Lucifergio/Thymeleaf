package ru.gb.thymeleafprepare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.gb.thymeleafprepare.entity.Cart;
import ru.gb.thymeleafprepare.entity.Product;

public interface cartDao extends JpaRepository<Cart, Long> {

    Cart save(Cart cart);
}
