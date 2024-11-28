package com.example.ShoppingNova_BE.Controller;

import com.example.ShoppingNova_BE.Entity.Product.Product;
import com.example.ShoppingNova_BE.Entity.Product.ProductService;
import com.example.ShoppingNova_BE.Entity.TvDetail.TvDetail;
import com.example.ShoppingNova_BE.Entity.TvDetail.TvService;
import com.example.ShoppingNova_BE.S3.S3Service;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final TvService tvService;
    private final S3Service s3Service;

    @Autowired
    public ProductController(ProductService productService, TvService tvService,
            S3Service s3Service) {
        this.productService = productService;
        this.tvService = tvService;
        this.s3Service = s3Service;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        model.addAttribute("product", product);
        return "testProductShow";
    }

    @GetMapping("/show")
    public String showProductDetail(@RequestParam("id") int productId, Model model) {
        Product product = productService.getProductById(productId);
        TvDetail tvDetail = tvService.getTvDetailByProductId(productId);

        // tvDetail이 null일 경우를 처리
        model.addAttribute("tvDetail", Objects.requireNonNullElseGet(tvDetail, TvDetail::new));

        model.addAttribute("product", product);
        return "testTvDetailShow";
    }


    @GetMapping("/all")
    public String testProductShowALL(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productListShow";  // 모든 상품 정보를 표시할 HTML 템플릿 이름
    }


}

