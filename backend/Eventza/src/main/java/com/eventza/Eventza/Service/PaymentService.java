package com.eventza.Eventza.Service;


import com.eventza.Eventza.model.PaymentModel;
import com.stripe.exception.StripeException;
import java.util.HashMap;
import java.util.Map;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  @Value("${STRIPE_SECRET_KEY}")
  private String secretKey;

  @PostConstruct
  public void init(){
    Stripe.apiKey = secretKey;
  }

  public Charge charge(PaymentModel paymentModel)
      throws StripeException {

    Map<String, Object> chargeParams = new HashMap<>();

    chargeParams.put("amount", paymentModel.getAmount());
    chargeParams.put("currency", paymentModel.getCurrency());
    chargeParams.put("source", paymentModel.getStripeToken());

    return Charge.create(chargeParams);
  }
}
