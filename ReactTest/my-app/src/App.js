
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