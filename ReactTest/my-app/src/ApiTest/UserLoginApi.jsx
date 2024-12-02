import React, { useState } from "react";

function UserLoginApi() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loginResult, setLoginResult] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    const handleLogin = async () => {
        setLoading(true);
        setError(null);

        try {
            const response = await fetch("http://3.35.58.101:8080/api/users/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email, password }),
            });

            if (!response.ok) {
                throw new Error("Failed to log in");
            }

            const data = await response.json();
            setLoginResult(data);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <h1>User Login</h1>
            <div>
                <label>
                    Email:
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        placeholder="Enter your email"
                    />
                </label>
            </div>
            <div>
                <label>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="Enter your password"
                    />
                </label>
            </div>
            <button onClick={handleLogin} disabled={loading}>
                {loading ? "Logging in..." : "Login"}
            </button>

            {error && <p style={{ color: "red" }}>Error: {error}</p>}
            {loginResult && (
                <div>
                    <h2>Login Successful!</h2>
                    <p>
                        <strong>ID:</strong> {loginResult.user.id}
                    </p>
                    <p>
                        <strong>Name:</strong> {loginResult.user.name}
                    </p>
                    <p>
                        <strong>Grade:</strong> {loginResult.user.grade}
                    </p>
                </div>
            )}
        </div>
    );
}

export default UserLoginApi;
