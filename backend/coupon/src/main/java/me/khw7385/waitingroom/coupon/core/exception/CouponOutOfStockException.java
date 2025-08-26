package me.khw7385.waitingroom.coupon.core.exception;

import me.khw7385.waitingroom.common.web.exception.BadRequestException;

import static me.khw7385.waitingroom.common.web.exception.ErrorCode.*;

public class CouponOutOfStockException extends BadRequestException {
    public CouponOutOfStockException() {
        super(COUPON_OUT_OF_STOCK.getMeesage());
    }

    @Override
    public String getErrorCode() {
        return COUPON_OUT_OF_STOCK.name();
    }
}
