package me.khw7385.waitingroom.common.web.config;

import me.khw7385.waitingroom.common.web.exception.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class ExceptionHandlingAutoConfig {

    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    @Import(GlobalExceptionHandler.class)
    public static class ImportExceptionHandler {}
}

