CREATE TABLE member (
    member_id	bigint NOT NULL AUTO_INCREMENT,
    login_id VARCHAR(256)	NOT NULL,
    password VARCHAR(256) NOT NULL,
    created_at datetime NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (member_id)
);



