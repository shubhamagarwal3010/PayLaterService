package com.paylater;

import com.paylater.account_service.IPlatformAccountService;
import com.paylater.merchant_service.IPlatformMerchantService;
import com.paylater.payment_service.IPlatformPaymentService;
import com.paylater.user_service.IPlatformUserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class PayLaterServiceApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.getBean(IPlatformUserService.class);
        ctx.getBean(IPlatformAccountService.class);
        ctx.getBean(IPlatformPaymentService.class);
    }
}
