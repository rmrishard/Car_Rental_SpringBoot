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
    image_url VARCHAR(500)
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
VALUES ('admin', 'Richard', 'Smith', 'richard@carrental.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'ADMIN')
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
INSERT INTO cars (make, model, year, price_per_day, type, image_url) VALUES
('Toyota', 'Camry', 2023, 45, 'Sedan', 'https://res.cloudinary.com/dgnqrrnqk/image/upload/v1755714635/toyota_camry_wh06pj.jpg'),
('Honda', 'Civic', 2022, 40, 'Compact', 'https://res.cloudinary.com/dgnqrrnqk/image/upload/v1755714634/honda_civic_rlvrhh.jpg'),
('Ford', 'Mustang', 2023, 75, 'Sports', 'https://res.cloudinary.com/dgnqrrnqk/image/upload/v1755714634/ford_mustang_jccp4n.jpg'),
('BMW', 'X5', 2023, 95, 'SUV', 'https://res.cloudinary.com/dgnqrrnqk/image/upload/v1755714634/bmw_x5_fvk5ll.jpg'),
('Mercedes', 'C-Class', 2022, 85, 'Luxury', 'https://res.cloudinary.com/dgnqrrnqk/image/upload/v1755714634/Mercedes_Cclass_xmohlg.jpg'),
('Nissan', 'Altima', 2023, 42, 'Sedan', 'https://res.cloudinary.com/dgnqrrnqk/image/upload/v1755714635/nissan_altima_v8lxzk.jpg'),
('Hyundai', 'Elantra', 2022, 38, 'Compact', 'https://res.cloudinary.com/dgnqrrnqk/image/upload/v1755714634/Hyundai_Elantra_wvmxwk.jpg'),
('Audi', 'A4', 2023, 80, 'Luxury', 'https://res.cloudinary.com/dgnqrrnqk/image/upload/v1755714634/audi_a4_gb7zvz.jpg'),
('Chevrolet', 'Tahoe', 2023, 90, 'SUV', 'https://res.cloudinary.com/dgnqrrnqk/image/upload/v1755714634/Chevrolet_Tahoe_vaihnq.jpg'),
('Tesla', 'Model 3', 2023, 70, 'Electric', 'https://res.cloudinary.com/dgnqrrnqk/image/upload/v1755714635/tesla_model3_k4cdmp.jpg'),
('Rivian', 'R1S', 2025, 90, 'SUV', 'https://res.cloudinary.com/dgnqrrnqk/image/upload/v1755714635/Rivian_R1S_ihyxdm.jpg'),
('Toyota', 'Land Cruiser', 2025, 115, 'SUV', 'https://res.cloudinary.com/dgnqrrnqk/image/upload/v1755714636/Toyota_Land_Cruiser_h17aks.jpg')
ON CONFLICT DO NOTHING;

-- Link cars to categories
INSERT INTO car_category (car_id, category_id) VALUES
-- Toyota Camry -> Economy, Mid-size
(1, 1), (1, 3),
-- Honda Civic -> Compact
(2, 2),
-- Ford Mustang -> Sports
(3, 8),
-- BMW X5 -> Luxury, SUV
(4, 5), (4, 6),
-- Mercedes C-Class -> Luxury, Full-size
(5, 5), (5, 4),
-- Nissan Altima -> Mid-size
(6, 3),
-- Hyundai Elantra -> Compact, Economy
(7, 2), (7, 1),
-- Audi A4 -> Luxury, Mid-size
(8, 5), (8, 3),
-- Chevrolet Tahoe -> SUV, Full-size
(9, 6), (9, 4),
-- Tesla Model 3 -> Luxury, Mid-size
(10, 5), (10, 3),
-- Rivian R1S -> SUV, Luxury
(11, 6), (11, 5),
-- Toyota Land Cruiser -> SUV, Full-size
(12, 6), (12, 4)
ON CONFLICT DO NOTHING;