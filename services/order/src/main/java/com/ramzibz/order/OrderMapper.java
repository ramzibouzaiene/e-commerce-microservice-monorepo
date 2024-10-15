package com.ramzibz.order;

import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(OrderRequest orderRequest);

    List<OrderResponse> toResponse(List<Order> order);
    OrderResponse toResponseOrder(Optional<Order> order);
}
