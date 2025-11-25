package com.suresh.accounts.feignclients;

import com.suresh.accounts.accountcustomerresponsedto.LoansResponse;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "LOANS", fallback = LoansFallBack.class)
@LoadBalancerClient(name = "LOANS")
public interface LoansFeignClient {
    @GetMapping("/api/v1/loans")
    ResponseEntity<LoansResponse> getLoans(@RequestParam String mobileNumber);
}
