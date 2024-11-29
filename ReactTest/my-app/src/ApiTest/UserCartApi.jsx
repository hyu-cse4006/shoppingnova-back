import React, { useState, useEffect } from "react";

function UserCartApi({ userId }) {
    const [cartData, setCartData] = useState([]);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // API 요청
        fetch(`http://localhost:8080/api/cart/${userId}/intro`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`Failed to fetch cart data for userId: ${userId}`);
                }
                return response.json();
            })
            .then((data) => {
                setCartData(data);
                setLoading(false);
            })
            .catch((error) => {
                setError(error.message);
                setLoading(false);
            });
    }, [userId]);

    if (loading) {
        return <div>Loading cart data...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    if (!cartData || cartData.length === 0) {
        return <div>No items in the cart.</div>;
    }

    return (
        <div>
            <h1>Cart for User {userId}</h1>
            <ul>
                {cartData.map((item) => (
                    <li key={item.id} style={{ marginBottom: "20px", border: "1px solid #ddd", padding: "10px" }}>
                        <h2>{item.name}</h2>
                        <img
                            src={item.image_url1}
                            alt={item.name}
                            style={{ width: "150px", height: "150px", objectFit: "cover" }}
                        />
                        <p>
                            <strong>Price:</strong> {item.price.toLocaleString()}원
                        </p>
                        <p>
                            <strong>Rating:</strong> {item.rating} / 5
                        </p>
                        <p>
                            <strong>Quantity:</strong> {item.quantity}
                        </p>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default UserCartApi;
