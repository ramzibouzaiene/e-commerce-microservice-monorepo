package com.ramzibz.orderLine;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {
    @Mapping(target = "order.id", source = "orderId")
    OrderLine toOrderLine(OrderLineRequest orderLineRequest);

    List<OrderLineResponse> toOrderLineResponse(List<OrderLine> orderLine);
}
