-- Initialize database for Car Rental System

-- Create role enum type
DO $$ BEGIN
    CREATE TYPE user_role AS ENUM ('USER', 'ADMIN');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

-- Create users table if not exists
CREATE TABLE IF NOT EXISTS users (
    user_id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role user_role NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create category table if not exists
CREATE TABLE IF NOT EXISTS category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create cars table if not exists
CREATE TABLE IF NOT EXISTS cars (
    id BIGSERIAL PRIMARY KEY,
    make VARCHAR(255),
    model VARCHAR(255),
    year INTEGER,
    price_per_day DOUBLE PRECISION,
    type VARCHAR(255),
    imageUrl VARCHAR(500)
);

-- Create car_category junction table if not exists
CREATE TABLE IF NOT EXISTS car_category (
    car_id BIGINT,
    category_id BIGINT,
    PRIMARY KEY (car_id, category_id),
    FOREIGN KEY (car_id) REFERENCES cars(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);

-- Insert admin user with password 'password'
INSERT INTO users (user_name, first_name, last_name, email, password, role) 
VALUES ('admin', 'Admin', 'User', 'admin@carrental.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'ADMIN')
ON CONFLICT (user_name) DO NOTHING;

-- Insert categories
INSERT INTO category (name) VALUES 
('Economy'),
('Compact'),
('Mid-size'),
('Full-size'),
('Luxury'),
('SUV'),
('Convertible'),
('Sports')
ON CONFLICT DO NOTHING;

-- Insert sample cars
INSERT INTO cars (make, model, year, price_per_day, type, imageUrl) VALUES
('Toyota', 'Corolla', 2023, 45.00, 'Sedan', 'https://images.unsplash.com/photo-1621007947382-bb3c3994e3fb?w=500'),
('Honda', 'Civic', 2023, 48.00, 'Sedan', 'https://images.unsplash.com/photo-1606664515524-ed2f786a0bd6?w=500'),
('Ford', 'Mustang', 2023, 85.00, 'Sports', 'https://images.unsplash.com/photo-1584345604476-8ec5e12e42dd?w=500'),
('BMW', 'X5', 2023, 120.00, 'SUV', 'https://images.unsplash.com/photo-1555215695-3004980ad54e?w=500'),
('Mercedes-Benz', 'C-Class', 2023, 95.00, 'Luxury', 'https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=500'),
('Jeep', 'Wrangler', 2023, 75.00, 'SUV', 'https://images.unsplash.com/photo-1519641471654-76ce0107ad1b?w=500'),
('Nissan', 'Altima', 2023, 52.00, 'Sedan', 'https://images.unsplash.com/photo-1502877338535-766e1452684a?w=500'),
('Chevrolet', 'Camaro', 2023, 78.00, 'Sports', 'https://images.unsplash.com/photo-1552519507-da3b142c6e3d?w=500'),
('Audi', 'A4', 2023, 88.00, 'Luxury', 'https://images.unsplash.com/photo-1606664515524-ed2f786a0bd6?w=500'),
('Volkswagen', 'Jetta', 2023, 42.00, 'Compact', 'https://images.unsplash.com/photo-1605559424843-9e4c228bf1c2?w=500')
ON CONFLICT DO NOTHING;

-- Link cars to categories
INSERT INTO car_category (car_id, category_id) VALUES
-- Toyota Corolla -> Economy, Compact
(1, 1), (1, 2),
-- Honda Civic -> Compact
(2, 2),
-- Ford Mustang -> Sports
(3, 8),
-- BMW X5 -> Luxury, SUV
(4, 5), (4, 6),
-- Mercedes C-Class -> Luxury, Full-size
(5, 5), (5, 4),
-- Jeep Wrangler -> SUV
(6, 6),
-- Nissan Altima -> Mid-size
(7, 3),
-- Chevrolet Camaro -> Sports
(8, 8),
-- Audi A4 -> Luxury, Mid-size
(9, 5), (9, 3),
-- Volkswagen Jetta -> Compact, Economy
(10, 2), (10, 1)
ON CONFLICT DO NOTHING;