import React, { useEffect, useState } from "react";

const CategoryApi = () => {
    const [categories, setCategories] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        // 모든 카테고리 조회 API 호출
        const fetchCategories = async () => {
            try {
                const response = await fetch("http://3.35.58.101:8080/api/category/all");
                
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                const data = await response.json();
                setCategories(data);
            } catch (err) {
                setError(err.message || "An error occurred");
            } finally {
                setLoading(false);
            }
        };

        fetchCategories();
    }, []);

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div>
            <h2>All Categories</h2>
            <ul>
                {categories.map((category) => (
                    <li key={category.id}>
                        <strong>ID:</strong> {category.id}, <strong>Name:</strong> {category.name},<strong>parent ID:</strong> {category.parent_id}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default CategoryApi;
