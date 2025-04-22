CREATE TABLE IF NOT EXISTS base_user(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(50) NOT NULL,
    role VARCHAR(10) DEFAULT 'user',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT NOW()
);

-- create the company
CREATE TABLE IF NOT EXISTS base_company(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(10) DEFAULT 'ACTIVE',
    owner INTEGER UNIQUE,
    FOREIGN KEY (owner) REFERENCES base_user(id) ON DELETE SET NULL
);

--create the department
CREATE TABLE IF NOT EXISTS base_department(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    head INTEGER,
    company INTEGER,
    FOREIGN KEY (head) REFERENCES base_user(id) ON DELETE SET NULL,
    FOREIGN KEY (company) REFERENCES base_company(id) ON DELETE SET NULL
);

--create a project
CREATE TABLE IF NOT EXISTS base_project(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    company INTEGER,
    FOREIGN KEY (company) REFERENCES base_company(id) ON DELETE CASCADE
);

-- create a task for a given user
CREATE TABLE IF NOT EXISTS base_task(
    id SERIAL PRIMARY KEY,
    title VARCHAR(50),
    description TEXT,
    duration INTEGER,
    is_drafted BOOLEAN DEFAULT FALSE,
    author INTEGER,
    created_at TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (author) REFERENCES base_user(id) ON DELETE CASCADE
);