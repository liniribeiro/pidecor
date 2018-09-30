package com.aliniribeiro.admin.api.model.item;

import com.aliniribeiro.admin.api.model.customerorder.CustomerOrderEntity;
import com.aliniribeiro.admin.api.model.item.enums.ItemStatus;
import com.aliniribeiro.admin.api.model.provider.ProviderEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "item")
public class ItemEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false)
    private UUID id;

    @Size(min = 2)
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ItemStatus status;

    @Column(name = "creationdate")
    private LocalDate date;

    @Column(name = "provider")
    private UUID providerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider", referencedColumnName = "ID", insertable = false, updatable = false)
    private ProviderEntity provider;

    @Column(name = "photolink", nullable = false)
    private String photoLink;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "customer_order")
    private UUID customerOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_order", referencedColumnName = "ID", insertable = false, updatable = false)
    private CustomerOrderEntity customerOrder;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public UUID getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(UUID customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UUID getProviderId() {
        return providerId;
    }

    public void setProviderId(UUID providerId) {
        this.providerId = providerId;
    }

    public ProviderEntity getProvider() {
        return provider;
    }

    public void setProvider(ProviderEntity provider) {
        this.provider = provider;
    }

    public CustomerOrderEntity getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrderEntity customerOrder) {
        this.customerOrder = customerOrder;
    }

}
