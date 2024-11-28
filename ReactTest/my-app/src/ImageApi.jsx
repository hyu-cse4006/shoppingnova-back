import React, { useEffect, useState } from "react";

const ImageComponent = ({ id, cnt }) => {
  const [imageUrls, setImageUrls] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const endpoint = cnt
      ? `http://localhost:8080/api/images/${id}/${cnt}`
      : `http://localhost:8080/api/images/${id}/all`;

    fetch(endpoint)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch image URLs");
        }
        return response.json();
      })
      .then((data) => {
        // 단일 이미지 요청이면 `imageUrl` 사용
        if (cnt) {
          setImageUrls([data.imageUrl]);
        } else {
          // 다중 이미지 요청이면 리스트 저장
          setImageUrls(data.map((item) => item.imageUrl));
        }
      })
      .catch((error) => {
        console.error("Error fetching images:", error);
        setError(error.message);
      });
  }, [id, cnt]);

  return (
    <div>
      <h1>Image Viewer</h1>
      {error && <p>Error: {error}</p>}
      {imageUrls.length > 0 ? (
        <div>
          {imageUrls.map((url, index) => (
            <img key={index} src={url} alt={`Image ${index + 1}`} />
          ))}
        </div>
      ) : (
        !error && <p>Loading images...</p>
      )}
    </div>
  );
};

export default ImageComponent;
