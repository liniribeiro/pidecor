package com.aliniribeiro.admin.api.model.provider;

import com.aliniribeiro.admin.api.model.provider.enums.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "provider")
public class ProviderEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "providers_api_id", nullable = false)
    private String providersAPIId;

    @Size(min = 2)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 2)
    @Column(name = "social_name", nullable = false)
    private String socialName;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Size(min = 2)
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Size(min = 2)
    @Column(name = "phone", nullable = false)
    private String phone;

    @Size(min = 2)
    @Column(name = "home_page", nullable = false)
    private String homePage;

    @Size(min = 2)
    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "companytype", nullable = false)
    private ProviderCompanyType companyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "annual_billing", nullable = false)
    private ProviderAnnualBilling annualBilling;

    @Column(name = "foundation_year", nullable = false)
    private Integer foundationYear;

    @Column(name = "qtd_client", nullable = false)
    private Integer qtdClient;

    @Enumerated(EnumType.STRING)
    @Column(name = "reputation", nullable = false)
    private ProviderReputation reputation;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private ProviderCategory category;

    @Column(name = "qtd_items", nullable = false)
    private Integer items;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProviderStatus status;

    @Column(name = "percentage", nullable = false)
    private Double percentage;

    @Column(name = "delivery_days", nullable = false)
    private Integer deliveryDays;

    @Column(name = "rejection_rate", nullable = false)
    private Double rejectionRate;

    @Column(name = "qtd_items_sold", nullable = false)
    private Integer qtdItemsSold;

    @Column(name = "activateddate")
    private LocalDate activatedDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    public ProviderCategory getCategory() {
        return category;
    }

    public void setCategory(ProviderCategory category) {
        this.category = category;
    }

    public ProviderReputation getReputation() {
        return reputation;
    }

    public void setReputation(ProviderReputation reputation) {
        this.reputation = reputation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getProvidersAPIId() {
        return providersAPIId;
    }

    public void setProvidersAPIId(String providersAPIId) {
        this.providersAPIId = providersAPIId;
    }

    public ProviderStatus getStatus() {
        return status;
    }

    public void setStatus(ProviderStatus status) {
        this.status = status;
    }

    public String getSocialName() {
        return socialName;
    }

    public void setSocialName(String socialName) {
        this.socialName = socialName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProviderCompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(ProviderCompanyType companyType) {
        this.companyType = companyType;
    }

    public ProviderAnnualBilling getAnnualBilling() {
        return annualBilling;
    }

    public void setAnnualBilling(ProviderAnnualBilling annualBilling) {
        this.annualBilling = annualBilling;
    }

    public Integer getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(Integer foundationYear) {
        this.foundationYear = foundationYear;
    }

    public Integer getQtdClient() {
        return qtdClient;
    }

    public void setQtdClient(Integer qtdClient) {
        this.qtdClient = qtdClient;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Integer getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(Integer deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    public Double getRejectionRate() {
        return rejectionRate;
    }

    public void setRejectionRate(Double rejectionRate) {
        this.rejectionRate = rejectionRate;
    }

    public Integer getQtdItemsSold() {
        return qtdItemsSold;
    }

    public void setQtdItemsSold(Integer qtdItemsSold) {
        this.qtdItemsSold = qtdItemsSold;
    }

    public LocalDate getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(LocalDate activatedDate) {
        this.activatedDate = activatedDate;
    }
}
