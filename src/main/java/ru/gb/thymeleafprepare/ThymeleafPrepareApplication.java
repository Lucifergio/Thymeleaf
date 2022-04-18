package ru.gb.thymeleafprepare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.thymeleafprepare.entity.Product;
import ru.gb.thymeleafprepare.entity.enums.Status;
import ru.gb.thymeleafprepare.service.ProductService;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class ThymeleafPrepareApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(ThymeleafPrepareApplication.class, args);
        ProductService productService = context.getBean(ProductService.class);
//
//        productService.findAll();
//
//        final Product product = productService.save(Product.builder()
//                        .cost(new BigDecimal(50.0))
//                        .status(Status.ACTIVE)
//                        .title("Test")
//                        .manufactureDate(LocalDate.now())
//                .build());
//
//        System.out.println("До " + productService.findById(product.getId()));
//
//        productService.deleteById(product.getId());
//
//        System.out.println("После " + productService.findById(product.getId()));


    }

}
