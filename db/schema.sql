CREATE DATABASE IF NOT EXISTS enterprise_helpdesk;

CREATE USER IF NOT EXISTS 'helpdesk_user'@'%' IDENTIFIED BY 'helpdesk_password';
GRANT ALL PRIVILEGES ON enterprise_helpdesk.* TO 'helpdesk_user'@'%';
FLUSH PRIVILEGES;

USE enterprise_helpdesk;

CREATE TABLE IF NOT EXISTS service_tickets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(120) NOT NULL,
    description VARCHAR(500) NOT NULL,
    requester_email VARCHAR(120) NOT NULL,
    priority VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    category VARCHAR(120) NOT NULL,
    assignee VARCHAR(120),
    created_at DATETIME NOT NULL,
    updated_at DATETIME
);
