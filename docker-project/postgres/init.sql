CREATE TABLE IF NOT EXISTS users(
                                    id BIGSERIAL PRIMARY KEY,
                                    email VARCHAR(255) UNIQUE NOT NULL,
                                    password VARCHAR(255) NOT NULL,
                                    name VARCHAR(255),
                                    phone VARCHAR(255),
                                    role VARCHAR(255) CHECK (role IN ('CLIENT', 'MASTER', 'ADMIN')),
                                    created_at TIMESTAMP,
                                    updated_at TIMESTAMP
);

COMMENT ON COLUMN users.role IS 'Possible values: CLIENT, MASTER, ADMIN';