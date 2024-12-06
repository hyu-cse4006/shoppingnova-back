# shoppingnova-back

## Backend Feature List

1. Login, Signup
    - [O] Login with email and password.
    - [O] Sign up by entering required information on the signup page.

2. Product Viewing (Read)
    - [O] Click on categories to navigate to detailed pages and view individual products.
    - [O] Sort products by price and rating in ascending order within the category.
    - Search by product name to navigate to the relevant subcategory.
    - Implement price range filtering for more detailed filtering options.

3. Cart Management  
    - [O] Display the cart data for the logged-in user.
    - [O] Add and remove products from the cart.
    - [O] Same product can be added multiple times, but checkout must be done for the entire cart at once.
    - When adding the same product to the cart, prevent duplicate entries and redirect to the cart instead.
    - Implement the ability to adjust the quantity of items in the cart.
    - Implement the option to select specific products from the cart for checkout.
    - (Frontend) Show a UI that displays stars on the cart list when a product is added.



4. Product Ordering
    - Order the products in the cart.
    - Order directly from the product detail page (single product).
    - Add the product to the order list upon placing the order.

5. Product Rating
    - [O] Display product ratings and the number of reviews.
    - Add and edit product ratings (can only be edited within a specific period after submission).
    - Allow adding a product review (text, photos) along with the rating.

6. Time Sale and Limited Edition Product Listings
    - For time sale products, set a time limit.
    - For limited edition products, show the remaining stock.
    - (Frontend) Display a UI where stars explode (supernova) when the product becomes unavailable. 
    - (Backend) Automatically delete the product when it becomes unavailable.

7. Product Management (Admin)
    - The administrator manages changes to products and removes discontinued products (Update, Delete).
    - Manage events such as price discounts, time sales, and limited edition items.

## Development Goals

#### Phase 1: User signup and login functionality, product database setup, category navigation and single product view, adding product ratings.
#### Phase 2: User filtering, search product listings, product, cart management, adding products to the cart and placing orders.

## ERD

### 1st ERD
<img src="./imgs/ERD-1.JPG" alt="이미지 설명" width="500" height="300">

### 2nd ERD
<img src="./imgs/ERD-2.png" alt="이미지 설명" width="500" height="300">

### 3rd ERD
<img src="./imgs/ERD-3.png" alt="이미지 설명" width="500" height="300">

## API Table

| No   | Entity      | Function                         | API Name            | Description                          |
|------|-------------|----------------------------------|---------------------|--------------------------------------|
| 1    | Users       | Login                            | user_login          | Log in with existing user information |
| 2    | Product     | Category product list            | product_list        | List of all products in a subcategory |
| 3    |             | Partial product information      | product_listInfo    | Partial information of products in the list |
| 4    |             | Specific product partial info    | product_info        | Partial information of a specific product |
| 5    |             | Specific product detail info     | product_detail      | Detailed information of a specific product |
| 6    |             | Product sorting (by rating)      | product_rateSort    | Sort products in descending order by rating |
| 7    |             | Product sorting (by price)       | product_priceSort   | Sort products in descending order by price |
| 8    | Category    | Parent and subcategory info      | category_table      | Category table                       |
| 9    |             | Specific category info           | category_info       | Information of a specific category   |
| 10   |             | Subcategory info                 | category_child      | View subcategories                   |
| 11   | Cart        | Cart list                        | cart_list           | View the cart list                   |
| 12   |             | Add to cart                      | cart_append         | Add product to the cart              |
| 13   |             | Remove from cart                 | cart_delete         | Remove product from the cart         |


