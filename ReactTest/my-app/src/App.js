
import React, { useState } from "react";
import ProductApi from "./ApiTest/ProductApi";
import ProductsCategoryApi from "./ApiTest/ProductCategoryApi";
import UserCartApi from "./ApiTest/UserCartApi";
import UserLoginApi from "./ApiTest/UserLoginApi";

function App() {
    const [view, setView] = useState("product"); // 기본 뷰를 'product'로 설정

    return (
        <div>
            <h1>API Test App</h1>
            <div style={{ marginBottom: "20px" }}>
                <button onClick={() => setView("product1")}>ProductApi1</button>
                <button onClick={() => setView("product2")}>ProductApi2</button>
                <button onClick={() => setView("category")}>ProductCategoryApi</button>
                <button onClick={() => setView("cart")}>UserCartApi</button>
                <button onClick={() => setView("login")}>UserLoginApi</button>
            </div>

            <div>
                {view === "product1" && <ProductApi id={1} />} {/* 예제 ID로 1 설정 */}
                {view === "product2" && <ProductApi id={140} />} {/* 예제 ID로 140 설정 */}
                {view === "category" && <ProductsCategoryApi categoryId={3} />} {/* 예제 카테고리 ID로 1 설정 */}
                {view === "cart" && <UserCartApi userId={1} />} {/* 예제 사용자 ID로 1 설정 */}
                {view === "login" && <UserLoginApi />}
            </div>
        </div>
    );
}

export default App;


/*
import React from "react";
import UserLoginApi from "./ApiTest/UserLoginApi"; // UserLoginApi.jsx의 경로에 따라 수정

function App() {
    return (
        <div>
            <h1>Welcome to the User Login Page</h1>
            <UserLoginApi />
        </div>
    );
}

export default App;

/*
import React, { useState } from "react";
import CartApi from "./ApiTest/UserCartApi";

const App = () => {
  const [userId, setUserId] = useState(1); // 테스트할 user_id 설정

  return (
    <div>
      <h1>Cart API Test</h1>
      <input
        type="number"
        value={userId}
        onChange={(e) => setUserId(e.target.value)}
        placeholder="Enter user ID"
      />
      <CartApi userId={userId} />
    </div>
  );
};

export default App;
*/

/*
import React from "react";
import ProductCategoryApi from "./ProductCategoryApi";

function App() {
  return (
    <div>
      <h1>Product App</h1>
      <ProductCategoryApi categoryId={3} />
    </div>
  );
}

export default App;

/*
import React from "react";
import ImageComponent from "./ImageApi"; // ImageApi.jsx 파일 경로

const App = () => {
  return (
    <div>
      <h1>React App</h1>

      <ImageComponent id={1}/>
    </div>
  );
};

export default App;



import React, { useState } from "react";
import ProductApi from "./ProductApi";

function App() {
    const [productId, setProductId] = useState("");

    const handleInputChange = (event) => {
        setProductId(event.target.value);
    };

    return (
        <div>
            <h1>Product Viewer</h1>
            <div>
                <label htmlFor="product-id">Enter Product ID:</label>
                <input
                    id="product-id"
                    type="number"
                    value={productId}
                    onChange={handleInputChange}
                    placeholder="Enter Product ID"
                />
            </div>
            {productId && <ProductApi id={productId} />}
        </div>

        
    );
}

export default App;
*/