import React, { useEffect, useState } from "react";
import axios from "axios";
const ProductsCategoryApi = ({ categoryId }) => {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // API 호출
    fetch(`http://localhost:8080/api/products/${categoryId}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error: ${response.status} - ${response.statusText}`);
        }
        return response.json();
      })
      .then((data) => {
        setProducts(data); // 상품 데이터 저장
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching products:", error);
        setError(error.message);
        setLoading(false);
      });
  }, [categoryId]);

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error}</p>;
  }

  return (
    <div>
      <h1>Products in Category {categoryId}</h1>
      {products.length > 0 ? (
        <ul>
          {products.map((product) => (
            <li key={product.id}>
              <h2>{product.name}</h2>
              <p>Price: {product.price.toLocaleString()}원</p>
              <p>Rating: {product.rating}</p>
              <p>Relese: {product.release_date}</p>
            </li>
          ))}
        </ul>
      ) : (
        <p>No products found.</p>
      )}
    </div>
  );
};

export default ProductsCategoryApi;
