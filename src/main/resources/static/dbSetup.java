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

// CREATE TABLE IF NOT EXISTS expense_groups (
// id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
// name VARCHAR(255) NOT NULL UNIQUE,
// user_id UUID NOT NULL,
// created_at TIMESTAMP DEFAULT NOW(),
// updated_at TIMESTAMP DEFAULT NOW(),

// CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE
// CASCADE
// )

// CREATE TABLE IF NOT EXISTS expenses (
// id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

// time TIMESTAMP NOT NULL,
// amount NUMERIC(12, 2) NOT NULL,
// item VARCHAR(255) NOT NULL,

// user_id UUID NOT NULL,
// expense_type_id UUID NOT NULL,
// expense_group_id UUID,

// created_at TIMESTAMP NOT NULL DEFAULT NOW(),
// updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

// CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE
// CASCADE,
// CONSTRAINT fk_expense_type FOREIGN KEY (expense_type_id) REFERENCES
// expense_types (id),
// CONSTRAINT fk_expense_group FOREIGN KEY (expense_group_id) REFERENCES
// expense_groups (id)
// )