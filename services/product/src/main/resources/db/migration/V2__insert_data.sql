-- Insert mock data into category table
INSERT INTO category (id, description, name)
VALUES
    (nextval('category_seq'), 'Electronics devices and accessories', 'Electronics'),
    (nextval('category_seq'), 'Books, e-books, and reading materials', 'Books'),
    (nextval('category_seq'), 'Health and fitness products', 'Health & Fitness'),
    (nextval('category_seq'), 'Home and kitchen appliances', 'Home & Kitchen'),
    (nextval('category_seq'), 'Fashion and clothing items', 'Fashion');

-- Insert mock data into product table
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'Smartphone with 64GB storage', 'Smartphone', 100, 699.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Wireless noise-cancelling headphones', 'Headphones', 50, 199.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Bestselling novel by popular author', 'Novel', 200, 14.99, (SELECT id FROM category WHERE name = 'Books')),
    (nextval('product_seq'), 'Yoga mat with non-slip surface', 'Yoga Mat', 120, 29.99, (SELECT id FROM category WHERE name = 'Health & Fitness')),
    (nextval('product_seq'), 'Blender with multiple speed settings', 'Blender', 30, 89.99, (SELECT id FROM category WHERE name = 'Home & Kitchen')),
    (nextval('product_seq'), 'Men’s casual jacket', 'Jacket', 80, 59.99, (SELECT id FROM category WHERE name = 'Fashion')),
    (nextval('product_seq'), 'Women’s running shoes', 'Running Shoes', 70, 79.99, (SELECT id FROM category WHERE name = 'Fashion'));
