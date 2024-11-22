package com.example.ShoppingNova_BE.Entity.Cart;


import com.example.ShoppingNova_BE.Entity.Product.*;
import com.example.ShoppingNova_BE.Entity.User.*;
import com.example.ShoppingNova_BE.Entity.User.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // 장바구니 추가
    @Transactional
    public Cart addToCart(Long userId, Long productId, int quantity, int price) {
        // DB에서 User와 Product를 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        // Cart 객체 생성
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cart.setPrice(price);

        // Cart 저장
        return cartRepository.save(cart);
    }

    // 사용자별 장바구니 조회
    public List<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    // 장바구니 항목 삭제
    @Transactional
    public void removeFromCart(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new IllegalArgumentException("Cart item not found with id: " + cartId);
        }
        cartRepository.deleteById(cartId);
    }

    // 장바구니 수정
    @Transactional
    public Cart updateCart(Long cartId, int quantity, boolean isChecked) {
        // DB에서 Cart 조회
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found with id: " + cartId));

        // 필드 업데이트
        cart.setQuantity(quantity);

        // Cart 저장
        return cartRepository.save(cart);
    }
}
