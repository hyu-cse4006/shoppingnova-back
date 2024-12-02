import React, { useState } from "react";

const CartDelTest = () => {
    const [userId, setUserId] = useState("");
    const [productId, setProductId] = useState("");
    const [responseMessage, setResponseMessage] = useState("");
    const [error, setError] = useState(null);

    const handleRemoveFromCart = async () => {
        if (!userId || !productId) {
            alert("User ID와 Product ID를 입력해주세요.");
            return;
        }

        try {
            const response = await fetch(
                `http://3.35.58.101:8080/api/cart/${userId}/del_cart?product_id=${productId}`,
                {
                    method: "DELETE",
                }
            );

            if (!response.ok) {
                throw new Error(`Failed to remove product from cart for User ID: ${userId}`);
            }

            const data = await response.text(); // API에서 반환되는 메시지 가져오기
            setResponseMessage(data);
            setError(null); // 에러 초기화
        } catch (err) {
            console.error("Error removing product from cart:", err);
            setError(err.message);
            setResponseMessage(""); // 성공 메시지 초기화
        }
    };

    return (
        <div>
            <h2>Remove Product from Cart</h2>
            <div>
                <label>
                    User ID:{" "}
                    <input
                        type="text"
                        value={userId}
                        onChange={(e) => setUserId(e.target.value)}
                    />
                </label>
            </div>
            <div>
                <label>
                    Product ID:{" "}
                    <input
                        type="text"
                        value={productId}
                        onChange={(e) => setProductId(e.target.value)}
                    />
                </label>
            </div>
            <button onClick={handleRemoveFromCart}>Remove from Cart</button>

            {responseMessage && <p style={{ color: "green" }}>Response: {responseMessage}</p>}
            {error && <p style={{ color: "red" }}>Error: {error}</p>}
        </div>
    );
};

export default CartDelTest;
