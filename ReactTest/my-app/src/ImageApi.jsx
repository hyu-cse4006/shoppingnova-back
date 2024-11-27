import React, { useEffect, useState } from "react";

const ImageApi = ({ folderName, fileName }) => {
  const [imageUrl, setImageUrl] = useState("");

  useEffect(() => {
    // API 요청
    fetch(`http://localhost:8080/api/${folderName}/${fileName}`)
      .then((response) => response.json())
      .then((data) => {
        setImageUrl(data.imageUrl); // API에서 반환된 이미지 URL 저장
      })
      .catch((error) => console.error("Error fetching image URL:", error));
  }, [folderName, fileName]);

  return (
    <div>
      <h1>Test Image</h1>
      {imageUrl ? (
        <img src={imageUrl} alt="Test" />
      ) : (
        <p>Loading image...</p>
      )}
    </div>
  );
};

export default ImageApi;
