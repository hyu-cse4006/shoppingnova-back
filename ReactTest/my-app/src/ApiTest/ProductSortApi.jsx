import React, { useState, useEffect } from "react";

const ProductSortTest = ({ categoryId }) => {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        // API 요청
        fetch(`http://3.35.58.101:8080/api/products/sort_rating/${categoryId}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`Failed to fetch products for category ID: ${categoryId}`);
                }
                return response.json();
            })
            .then((data) => {
                setProducts(data); // 데이터 저장
                setLoading(false); // 로딩 종료
            })
            .catch((error) => {
                setError(error.message); // 에러 메시지 저장
                setLoading(false); // 로딩 종료
            });
    }, [categoryId]);

    if (loading) {
        return <p>Loading...</p>; // 로딩 상태 표시
    }

    if (error) {
        return <p style={{ color: "red" }}>{error}</p>; // 에러 메시지 표시
    }

    return (
        <div>
            <h2>Sorted Products by Rating</h2>
            <ul>
                {products.map((product) => (
                    <li key={product.id}>
                        <strong>{product.name}</strong> - Rating: {product.rating} - Price: {product.price}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ProductSortTest;
