package com.ramzibz.order;

import com.ramzibz.customer.CustomerClient;
import com.ramzibz.exception.BusinessException;
import com.ramzibz.orderLine.OrderLineRequest;
import com.ramzibz.orderLine.OrderLineService;
import com.ramzibz.product.ProductClient;
import com.ramzibz.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(OrderRequest orderRequest) {
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exists with the provided ID"));
        this.productClient.purchaseProducts(orderRequest.products());

        var order = this.repository.save(orderMapper.toOrder(orderRequest));

        for (PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        //TODO: Integrating payment process

        //TODO: Sending order confirmation notification
        return null;

    }
}
