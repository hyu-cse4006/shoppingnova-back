package com.example.ShoppingNova_BE.Entity.TvDetail;
import com.example.ShoppingNova_BE.Entity.Product.Product;
import com.example.ShoppingNova_BE.Entity.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TvService {

    private final TvRepository tvRepository;

    @Autowired
    public TvService(TvRepository tvRepository) {
        this.tvRepository = tvRepository;
    }

    // Product ID를 기반으로 TvDetail 정보를 가져오는 메서드
    public TvDetail getTvDetailByProductId(int productId) {
        Optional<TvDetail> tvDetailOptional = tvRepository.findByProductId(productId);
        return tvDetailOptional.orElse(null);  // Optional 객체가 비어있으면 null 반환
    }

    // TvDetail 저장 메서드 (필요 시 추가)
    public TvDetail saveTvDetail(TvDetail tvDetail) {
        return tvRepository.save(tvDetail);
    }
}

