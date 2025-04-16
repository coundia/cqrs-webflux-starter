CREATE TABLE IF NOT EXISTS users
(
    id         VARCHAR(100) PRIMARY KEY,
    full_name  VARCHAR(255),
    email      VARCHAR(255) UNIQUE,
    phone      VARCHAR(30),
    address    TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS accounts
(
    id             VARCHAR(100) PRIMARY KEY,
    user_id    VARCHAR(100),
    account_number VARCHAR(50) UNIQUE,
    type           VARCHAR(20),
    balance        DECIMAL(18, 2) DEFAULT 0,
    currency       VARCHAR(10),
    created_at     TIMESTAMP      DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS transactions
(
    id                     VARCHAR(100) PRIMARY KEY,
    reference              VARCHAR(100) UNIQUE,
    source_account_id      VARCHAR(100),
    destination_account_id VARCHAR(100),
    amount                 DECIMAL(18, 2),
    currency               VARCHAR(10),
    type                   VARCHAR(20),
    status                 VARCHAR(20),
    description            TEXT,
    created_at             TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS transaction_audit
(
    id             VARCHAR(100) PRIMARY KEY,
    transaction_id VARCHAR(100),
    action         VARCHAR(50),
    performed_by   VARCHAR(255),
    performed_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    details        TEXT
);
