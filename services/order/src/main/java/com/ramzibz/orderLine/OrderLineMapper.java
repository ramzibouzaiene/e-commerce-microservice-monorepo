package com.ramzibz.orderLine;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {
    @Mapping(target = "order.id", source = "orderId")
    OrderLine toOrderLine(OrderLineRequest orderLineRequest);
}
