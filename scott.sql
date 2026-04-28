-- 1. Tabloları Oluşturma
CREATE TABLE IF NOT EXISTS drivers (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(16),
    address VARCHAR(32),
    phone VARCHAR(16),
    is_deleted BOOLEAN DEFAULT FALSE
    );

CREATE TABLE IF NOT EXISTS vehicles (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        brand VARCHAR(16),
    model VARCHAR(16),
    color VARCHAR(16),
    type VARCHAR(16),
    is_deleted BOOLEAN DEFAULT FALSE
    );

CREATE TABLE IF NOT EXISTS assignments (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           assigned_date DATETIME(6),
    return_date DATETIME(6),
    driver_id BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,
    FOREIGN KEY (driver_id) REFERENCES drivers(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
    );

-- 2. Şoför (Driver) Verilerini Ekleme
INSERT INTO drivers (name, address, phone, is_deleted) VALUES ('Kerem', 'Sakarya', '5551234567', false);
INSERT INTO drivers (name, address, phone, is_deleted) VALUES ('Ahmet Hoca', 'Kampus', '5559876543', false);

-- 3. Araç (Vehicle) Verilerini Ekleme
INSERT INTO vehicles (brand, model, color, type, is_deleted) VALUES ('Ferrari', 'SF-24', 'Kırmızı', 'F1', false);
INSERT INTO vehicles (brand, model, color, type, is_deleted) VALUES ('Renault', 'Megane', 'Beyaz', 'Sedan', false);

-- 4. Atama (Assignment) Verilerini Ekleme (Kerem'e Ferrari'yi atıyoruz!)
INSERT INTO assignments (assigned_date, return_date, driver_id, vehicle_id) VALUES ('2026-03-22 09:00:00', '2026-03-25 18:00:00', 1, 1);