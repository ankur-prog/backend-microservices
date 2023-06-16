package com.ankur.prog.orderservice.controller;

import com.ankur.prog.orderservice.dto.OrderRequest;

import com.ankur.prog.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
private final OrderService orderService;

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {

        return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest)) ;
    }



    // implement fallback method  for circuit breaker
    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException)
    {
        return CompletableFuture.supplyAsync(()->"Something went wrong. Please try after some time. ") ;
    }

}
