import React, { useState, useEffect } from "react";

function ProductApi({ id }) {
    const [product, setProduct] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // API 요청
        fetch(`http://3.35.58.101:8080/api/product/${id}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`Product not found with ID: ${id}`);
                }
                return response.json();
            })
            .then((data) => {
                setProduct(data);
                setLoading(false);
            })
            .catch((error) => {
                setError(error.message);
                setLoading(false);
            });
    }, [id]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    if (!product) {
        return <div>No product found.</div>;
    }

    return (
        <div>
            <h1>{product.name}</h1>
            <p>Price: {product.price.toLocaleString()}원</p>
            <p>Rating: {product.rating} ({product.rate_num} reviews)</p>
            <p>Category ID: {product.category_id}</p>
            <p>Release Date: {product.release_date}</p>
            <p>Weight: {product.weight} kg</p>
            <p>Dimensions: {product.size_x} x {product.size_y} x {product.size_z} mm</p>
            <p>Color: {product.color}</p>
            <p>Energy Efficiency: {product.energy}</p>
            <p>Door Count: {product.door_count}</p>
            <p>Cold Volume: {product.volume_cold} L</p>
            <p>Freeze Volume: {product.volume_freeze} L</p>
        </div>
    );
}

export default ProductApi;
