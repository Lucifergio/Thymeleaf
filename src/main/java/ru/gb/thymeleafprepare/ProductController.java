package ru.gb.thymeleafprepare;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.thymeleafprepare.entity.Product;
import ru.gb.thymeleafprepare.service.ProductService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
@Transactional
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public String getProductList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product-list";
    }

    @GetMapping
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        Product product;
        if (id != null) {
            product = productService.findById(id);
        } else {
            product = new Product();
        }
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping
    public String saveProduct(Product product) {
        product.setManufactureDate(LocalDate.now());
        productService.save(product);
        return "redirect:/product/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
        return "redirect:/product/all";
    }

    @GetMapping("/cartList")
    public String cart(Model model) {
        model.addAttribute("prodCart", productService.allProductInCart());
        return "cartList";
    }

    @GetMapping("/addProductInCart/{id}")
    public String addProduct(@PathVariable(name = "id") Long id) {
        productService.addProductInCart(id);
        return "redirect:/product/all";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteFromCart(@PathVariable(name = "id") Long id) {
        productService.deleteFromCartById(id);
        return "redirect:/product/cartList";
    }


}
