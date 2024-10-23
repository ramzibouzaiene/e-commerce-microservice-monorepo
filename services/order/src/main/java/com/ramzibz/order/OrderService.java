package com.ramzibz.order;

import com.ramzibz.customer.CustomerClient;
import com.ramzibz.exception.BusinessException;
import com.ramzibz.kafka.OrderConfirmation;
import com.ramzibz.kafka.OrderProducer;
import com.ramzibz.orderLine.OrderLineRequest;
import com.ramzibz.orderLine.OrderLineService;
import com.ramzibz.payment.PaymentClient;
import com.ramzibz.payment.PaymentRequest;
import com.ramzibz.product.ProductClient;
import com.ramzibz.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest) {
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exists with the provided ID"));
        var purchasedProducts = this.productClient.purchaseProducts(orderRequest.products());

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
        paymentClient.requestOrderPayment(
                new PaymentRequest(
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        order.getId(),
                        order.getReference(),
                        customer
                )
        );

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();

    }

    public List<OrderResponse> findAll() {
        List<Order> orders = repository.findAll();
        return orderMapper.toResponse(orders);
    }

    public OrderResponse findById(Integer orderId) {
        Optional<Order> order = repository.findById(orderId);
        if (order.isEmpty()) {
            throw new EntityNotFoundException(String.format("No order found"));
        }
        return orderMapper.toResponseOrder(order);
    }
}
