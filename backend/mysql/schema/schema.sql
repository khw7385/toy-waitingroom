CREATE TABLE member (
    member_id bigint NOT NULL AUTO_INCREMENT,
    login_id VARCHAR(256)	NOT NULL,
    password VARCHAR(256) NOT NULL,
    created_at datetime NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (member_id)
);

CREATE TABLE coupon(
    coupon_id bigint NOT NULL AUTO_INCREMENT,
    name VARCHAR(256) NOT NULL,
    quantity int NOT NULL,
    valid_until DATETIME NULL COMMENT '쿠폰 유효 종료일(절대 기간)',
    created_at datetime NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (coupon_id)
);

CREATE TABLE coupon_issue(
    coupon_issue_id bigint NOT NULL AUTO_INCREMENT,
    coupon_id bigint NOT NULL,
    member_id bigint NOT NULL,
    is_used tinyint(1) NOT NULL DEFAULT 0,
    expired_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at datetime NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (coupon_issue_id),
    FOREIGN KEY (coupon_id) REFERENCES coupon(coupon_id),
    FOREIGN KEY (member_id) REFERENCES member(member_id),
    CONSTRAINT unique_coupon_issue_coupon_member UNIQUE (coupon_id, member_id)
);

