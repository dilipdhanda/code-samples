package com.c0.intellib.Spring.Injections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
👉 In almost all modern Spring applications, setter injection can be completely avoided
— just use constructor injection everywhere.
 */
@Component
public class Setter_Injection_OrderService {
  private PaymentService paymentService;
  @Autowired
  public void setPaymentService(PaymentService paymentService) {
    this.paymentService = paymentService;
  }
}
