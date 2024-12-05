import React, { useState } from "react";
import ProductApi from "./ApiTest/ProductApi";
import ProductsCategoryApi from "./ApiTest/ProductCategoryApi";
import UserCartApi from "./ApiTest/UserCartApi";
import UserLoginApi from "./ApiTest/UserLoginApi";
import ProductSortTest from "./ApiTest/ProductSortApi";
import CartAddTest from "./ApiTest/CartAddTest";
import CartDelTest from "./ApiTest/CartDelTest";
import CategoryApi from "./ApiTest/CategoryAPI"; // CategoryApi.jsx 경로에 따라 수정

function App() {
    const [view, setView] = useState("product"); // 기본 뷰를 'product'로 설정

    return (
        <div>
            <h1>API Test App</h1>
            <div style={{ marginBottom: "20px" }}>
                <button onClick={() => setView("product1")}>ProductApi1</button>
                <button onClick={() => setView("product2")}>ProductApi2</button>
                <button onClick={() => setView("category")}>ProductCategoryApi</button>
                <button onClick={() => setView("login")}>UserLoginApi</button>
                <button onClick={() => setView("sort")}>ProductSortTest</button>
                <button onClick={() => setView("cart")}>UserCartApi</button>
                <button onClick={() => setView("addCart")}>CartAddTest</button>
                <button onClick={() => setView("removeCart")}>CartDelTest</button>
                <button onClick={() => setView("allCategories")}>All Categories</button> {/* 버튼 추가 */}
            </div>

            <div>
                {view === "product1" && <ProductApi id={1} />}
                {view === "product2" && <ProductApi id={140} />}
                {view === "category" && <ProductsCategoryApi categoryId={14} />}
                {view === "cart" && <UserCartApi userId={1} />}
                {view === "login" && <UserLoginApi />}
                {view === "sort" && <ProductSortTest categoryId={3} />}
                {view === "addCart" && <CartAddTest />}
                {view === "removeCart" && <CartDelTest />}
                {view === "allCategories" && <CategoryApi />} {/* 뷰 추가 */}
            </div>
        </div>
    );
}

export default App;
