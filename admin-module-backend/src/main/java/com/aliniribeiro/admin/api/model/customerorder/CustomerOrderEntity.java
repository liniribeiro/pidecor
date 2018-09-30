package com.aliniribeiro.admin.api.model.customerorder;

import com.aliniribeiro.admin.api.model.customer.CustomerEntity;
import com.aliniribeiro.admin.api.model.customerorder.enums.OrderStatus;
import com.aliniribeiro.admin.api.model.item.ItemEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer_order")
public class CustomerOrderEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "customer", nullable = false)
    private UUID customerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer", referencedColumnName = "ID", insertable = false, updatable = false)
    private CustomerEntity customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_order", referencedColumnName = "id", insertable = false, updatable = false)
    private List<ItemEntity> items;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAdress;

    @Column(name = "delivery_receiver_name", nullable = false)
    private String deliveryReceiverName;

    @Column(name = "creationdate", nullable = false)
    private LocalDate creationDate;

    @Column(name = "providers_api_id", nullable = false)
    private String providersAPIId;

    public String getProvidersAPIId() {
        return providersAPIId;
    }

    public void setProvidersAPIId(String providersAPIId) {
        this.providersAPIId = providersAPIId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public String getDeliveryAdress() {
        return deliveryAdress;
    }

    public void setDeliveryAdress(String deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

    public String getDeliveryReceiverName() {
        return deliveryReceiverName;
    }

    public void setDeliveryReceiverName(String deliveryReceiverName) {
        this.deliveryReceiverName = deliveryReceiverName;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
