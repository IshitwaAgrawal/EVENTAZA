package com.eventza.Eventza.controller;



import com.eventza.Eventza.Events.EventService;
import com.eventza.Eventza.Service.PaymentService;
import com.eventza.Eventza.model.PaymentModel.Currency;
import com.eventza.Eventza.model.PaymentModel;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentController {

  @Value("${STRIPE_PUBLIC_KEY}")
  private String publicKey;

  @Autowired
  PaymentService paymentService;

  @Autowired
  EventService eventService;

  @RequestMapping("/{eventName}/checkout")
  public String checkout(@PathVariable("eventName") String eventName, Model model) {

    Integer price = eventService.getRequestedEvent(eventName).getPrice();

    model.addAttribute("amount", price * 100);
    model.addAttribute("stripePublicKey", publicKey);
    model.addAttribute("currency", Currency.INR);

    return "checkout";
  }


  @PostMapping("/charge")
  public String charge(PaymentModel paymentModel, Model model)
      throws StripeException {

    paymentModel.setCurrency(Currency.INR);
    Charge charge = paymentService.charge(paymentModel);

    model.addAttribute("status", charge.getStatus());
    model.addAttribute("balance_transaction", charge.getBalanceTransaction());

    return "result";
  }

  @ExceptionHandler(StripeException.class)
  public String handleError(Model model, StripeException ex) {
    model.addAttribute("error", ex.getMessage());
    return "result";
  }

}
