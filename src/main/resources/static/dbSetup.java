// CREATE TABLE IF NOT EXISTS users (
// id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
// username VARCHAR(255) NOT NULL UNIQUE,
// password VARCHAR(255) NOT NULL,
// email VARCHAR(255) NOT NULL UNIQUE,
// verified BOOLEAN DEFAULT FALSE,
// created_at TIMESTAMP DEFAULT NOW(),
// updated_at TIMESTAMP DEFAULT NOW(),
// auth_level INTEGER DEFAULT 2
// );

// CREATE TABLE IF NOT EXISTS expense_types (
// id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
// name VARCHAR(255) NOT NULL UNIQUE,
// created_at TIMESTAMP DEFAULT NOW(),
// updated_at TIMESTAMP DEFAULT NOW()
// );