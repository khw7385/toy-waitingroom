package me.khw7385.waitingroom.coupon.core.exception;

import me.khw7385.waitingroom.common.web.exception.ErrorCode;
import me.khw7385.waitingroom.common.web.exception.NotFoundException;

import static me.khw7385.waitingroom.common.web.exception.ErrorCode.*;

public class CouponNotFoundException extends NotFoundException {
    public CouponNotFoundException() {
        super(COUPON_NOT_FOUND.getMeesage());
    }

    @Override
    public String getErrorCode() {
        return COUPON_NOT_FOUND.name();
    }
}
