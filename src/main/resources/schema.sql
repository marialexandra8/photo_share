CREATE TABLE IF NOT EXISTS accounts(
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(70) NOT NULL,
    password BINARY(60) NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)^;

CREATE TABLE IF NOT EXISTS authentication_tokens(
    id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT NOT NULL ,
    token VARCHAR(50) NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
)^;

CREATE TABLE IF NOT EXISTS users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT NOT NULL,
    email VARCHAR(75) NOT NULL,
    name VARCHAR(60),
    logo VARCHAR(60),
    birthday DATE,
    gender ENUM('MALE', 'FEMALE'),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY(account_id) REFERENCES accounts(id) ON DELETE CASCADE
)^;


CREATE TABLE IF NOT EXISTS contests(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(256) NOT NULL,
    --tags VARCHAR(256), -- JSON list of tags
    deadline TIMESTAMP NOT NULL,
    logo VARCHAR(100),

    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)^;

CREATE TABLE IF NOT EXISTS contest_entries(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    contest_id INT NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY(contest_id) REFERENCES contests(id) ON DELETE CASCADE
)^;

CREATE TABLE IF NOT EXISTS contest_entry_images(
    id INT PRIMARY KEY AUTO_INCREMENT,
    contest_entry_id INT NOT NULL,
    file_name VARCHAR(150) NOT NULL,
    mime_type VARCHAR(50),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY(contest_entry_id) REFERENCES contest_entries(id) ON DELETE CASCADE
)^;

CREATE TABLE IF NOT EXISTS reviews(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    contest_entry_id INT NOT NULL,
    rate INT NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY(contest_entry_id) REFERENCES contest_entries(id) ON DELETE CASCADE,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE

)^;

