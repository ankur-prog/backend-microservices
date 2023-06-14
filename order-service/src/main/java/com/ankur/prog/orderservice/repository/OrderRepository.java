package com.ankur.prog.orderservice.repository;


import com.ankur.prog.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}