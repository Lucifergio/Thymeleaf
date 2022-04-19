package ru.gb.thymeleafprepare.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.thymeleafprepare.dao.ProductDao;
import ru.gb.thymeleafprepare.dao.cartDao;
import ru.gb.thymeleafprepare.entity.Cart;
import ru.gb.thymeleafprepare.entity.Product;
import ru.gb.thymeleafprepare.entity.enums.Status;

import javax.persistence.JoinTable;
import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductDao productDao;
    private final cartDao cartDao;

    //    private final Long DEFAULT_CART;
    @Transactional(propagation = Propagation.NEVER, isolation = Isolation.DEFAULT)
    public long count() {
        System.out.println(productDao.count());
        return productDao.count();
    }

    public Product save(Product product) {
        if (product.getId() != null) {
            Optional<Product> productFromDBOptional = productDao.findById(product.getId());
            if (productFromDBOptional.isPresent()) {
                Product productFromDB = productFromDBOptional.get();
                productFromDB.setTitle(product.getTitle());
                productFromDB.setCost(product.getCost());
                productFromDB.setManufactureDate(product.getManufactureDate());
                productFromDB.setStatus(product.getStatus());
                return productDao.save(productFromDB);
            }
        }
        return productDao.save(product);
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productDao.findById(id).orElse(null);
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public List<Product> findAllActive() {
        return productDao.findAllByStatus(Status.ACTIVE);
    }

    public void deleteById(Long id) {
        try {
            productDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
        }
    }

    @Transactional
    public List<Product> allProductInCart() {
        return cartDao.getById(1L).getProducts();
    }

    public Product addProductInCart(Long id) {
        Cart cart = cartDao.getById(1L);

        Product product = productDao.getById(id);
        cart.addProduct(product);
        cartDao.save(cart);
        return product;
    }

    public void deleteFromCartById(Long id) {
        Cart cart = cartDao.getById(1L);
        for (Product prod : cart.getProducts()) {
            if (prod.getId().equals(id)) {
                cart.getProducts().remove(prod);
                return;
            }
        }
    }

}
