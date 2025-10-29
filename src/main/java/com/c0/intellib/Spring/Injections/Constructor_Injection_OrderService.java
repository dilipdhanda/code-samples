package com.c0.intellib.Spring.Injections;

import org.springframework.stereotype.Component;

/*
✅ Benefits:
Immutability: paymentService is final, can’t be reassigned — makes the class safer and easier to reason about.
Required dependencies enforced:
- Spring must provide PaymentService when creating OrderService.
- The app won’t start if the dependency is missing → fail fast.
Testability: You can easily instantiate it in tests without Spring:

✅ No @Autowired needed if the class has only one constructor (Spring does it automatically).
✅ Dependencies are final — immutable.
✅ The app fails to start if dependencies are missing — fail-fast.
 */
@Component
public class Constructor_Injection_OrderService {
  private final PaymentService paymentService;
  public Constructor_Injection_OrderService(PaymentService paymentService) {
    this.paymentService = paymentService;
  }
}

