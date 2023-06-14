package com.ankur.prog.orderservice.service;

import com.ankur.prog.orderservice.dto.InventoryResponse;
import com.ankur.prog.orderservice.dto.OrderLineItemsDto;
import com.ankur.prog.orderservice.dto.OrderRequest;
import com.ankur.prog.orderservice.model.Order;
import com.ankur.prog.orderservice.model.OrderLineItems;
import com.ankur.prog.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    public Order placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList =orderRequest.getOrderLineItemsdtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItemsList);
        List<String> skuCodes = orderLineItemsList.stream()
                .map(orderLineItems -> orderLineItems.getSkuCode())
                .toList();
        log.info("Number of skuCodes : {}",skuCodes.size());
        // call inventory service to check product availability
        InventoryResponse[] inventoryResponses=webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);
        if(allProductInStock)
        {
            orderRepository.save(order);
            log.info("order placed successfully");
        }
        else
            throw new IllegalArgumentException("Product is not in stock . Please try later.");

        return order;
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}

