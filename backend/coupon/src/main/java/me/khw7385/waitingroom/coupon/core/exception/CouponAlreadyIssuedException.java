package me.khw7385.waitingroom.coupon.core.exception;

import me.khw7385.waitingroom.common.web.exception.BadRequestException;

import static me.khw7385.waitingroom.common.web.exception.ErrorCode.*;

public class CouponAlreadyIssuedException extends BadRequestException {
    public CouponAlreadyIssuedException() {
        super(COUPON_ALREADY_ISSUED.getMeesage());
    }

    @Override
    public String getErrorCode() {
        return COUPON_ALREADY_ISSUED.name();
    }
}
