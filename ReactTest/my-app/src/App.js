import React from "react";
import ImageComponent from "./ImageApi"; // ImageApi.jsx 파일 경로

const App = () => {
  return (
    <div>
      <h1>React App</h1>
      {/* ImageComponent 호출 */}
      <ImageComponent folderName="image" fileName="product_fridge_1_3.png" />
    </div>
  );
};

export default App;
