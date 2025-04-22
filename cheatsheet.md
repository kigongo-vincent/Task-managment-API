
# 📘 PostgreSQL Full Cheat Sheet (Inspired by Supabase Practices)

This cheat sheet provides essential PostgreSQL commands and best practices including user management, database structure, and access control.

---

## 🔧 General Commands

### Connect to PostgreSQL

```bash
psql -h localhost -U user -d dbname
```

### List all databases

```sql
\l
```

### Switch to a database

```sql
\c dbname
```

### List tables

```sql
\dt
```

### Describe table

```sql
\d tablename
```

---

## 🧑‍💻 User (Role) Management

### Create a secure user

```sql
CREATE ROLE app_user WITH LOGIN PASSWORD 'secure_password';
```

### Grant database privileges

```sql
GRANT CONNECT ON DATABASE your_db TO app_user;
```

### Grant schema usage

```sql
GRANT USAGE ON SCHEMA public TO app_user;
```

### Grant table access

```sql
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO app_user;
```

> 🔁 To apply permissions on future tables automatically:

```sql
ALTER DEFAULT PRIVILEGES IN SCHEMA public
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO app_user;
```

### Create readonly user

```sql
CREATE ROLE readonly WITH LOGIN PASSWORD 'readonly_pass';
GRANT CONNECT ON DATABASE your_db TO readonly;
GRANT USAGE ON SCHEMA public TO readonly;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO readonly;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT ON TABLES TO readonly;
```

---

## 🗃 Database & Table Management

### Create a database

```sql
CREATE DATABASE your_db;
```

### Drop a database

```sql
DROP DATABASE your_db;
```

### Create a table

```sql
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  email TEXT UNIQUE NOT NULL,
  created_at TIMESTAMP DEFAULT NOW()
);
```

### Alter a table

```sql
ALTER TABLE users ADD COLUMN last_login TIMESTAMP;
```

### Drop a table

```sql
DROP TABLE users;
```

---

## 🧪 CRUD Operations

### Insert

```sql
INSERT INTO users (email) VALUES ('user@example.com');
```

### Select

```sql
SELECT * FROM users;
```

### Update

```sql
UPDATE users SET email = 'new@example.com' WHERE id = 1;
```

### Delete

```sql
DELETE FROM users WHERE id = 1;
```

---

## 🔍 Query Helpers

### Where clause

```sql
SELECT * FROM users WHERE email LIKE '%example.com';
```

### Join

```sql
SELECT u.*, p.* FROM users u
JOIN profiles p ON u.id = p.user_id;
```

### Order & Limit

```sql
SELECT * FROM users ORDER BY created_at DESC LIMIT 10;
```

---

## 🛠 Indexing & Performance

### Create index

```sql
CREATE INDEX idx_email ON users(email);
```

### Drop index

```sql
DROP INDEX idx_email;
```

---

## 🔒 Security & Privileges

### Revoke access

```sql
REVOKE ALL PRIVILEGES ON DATABASE your_db FROM app_user;
```

### Reassign owned objects

```sql
REASSIGN OWNED BY old_user TO new_user;
```

### Drop user

```sql
DROP OWNED BY old_user;
DROP ROLE old_user;
```

---

## 🧰 Maintenance

### Backup

```bash
pg_dump -U user -d dbname > backup.sql
```

### Restore

```bash
psql -U user -d dbname < backup.sql
```

### Check current connection

```sql
\conninfo
```

### Quit

```sql
\q
```

---

## 📌 Notes

- Always use `;` at the end of SQL statements.
- Use `\?` in `psql` for help.
- Use `\timing` to benchmark queries.
- Use parameterized queries in your app to prevent SQL injection.

---
