package com.ankur.prog.orderservice.service;

import com.ankur.prog.orderservice.dto.InventoryResponse;
import com.ankur.prog.orderservice.dto.OrderLineItemsDto;
import com.ankur.prog.orderservice.dto.OrderRequest;
import com.ankur.prog.orderservice.event.OrderPlacedEvent;
import com.ankur.prog.orderservice.model.Order;
import com.ankur.prog.orderservice.model.OrderLineItems;
import com.ankur.prog.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsdtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItemsList);
        List<String> skuCodes = orderLineItemsList.stream()
                .map(OrderLineItems::getSkuCode)
                .toList();
        log.info("Number of skuCodes : {}", skuCodes.size());
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        log.info("Number of sku codes in stock : {}", inventoryResponses.length);
        boolean allProductInStock = (skuCodes.size() == inventoryResponses.length);
        log.info("All product are in stock : {}", allProductInStock);
        if (allProductInStock) {
            orderRepository.save(order);
            kafkaTemplate.send("NotificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
        }
        else throw new IllegalArgumentException("Sorry , All products are not in stock. Please try after sometime.");

        return "Order Placed Successfully.";
    }



    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}

