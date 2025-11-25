package com.suresh.accounts.feignclients;

import com.suresh.accounts.accountcustomerresponsedto.CardResponse;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CARDS", fallback = CardsFallBack.class)
@LoadBalancerClient(name = "CARDS")
public interface CardsFeignClient {
    @GetMapping("/api/v1/cards")
    ResponseEntity<CardResponse> getCards(@RequestParam String mobileNumber);
}
