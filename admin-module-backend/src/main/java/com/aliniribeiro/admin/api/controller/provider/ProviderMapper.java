package com.aliniribeiro.admin.api.controller.provider;

import com.aliniribeiro.admin.api.model.provider.enums.*;
import com.aliniribeiro.admin.api.model.provider.ProviderEntity;
import org.springframework.stereotype.Component;
import providers.wsdl.Provider;

import java.time.LocalDate;

@Component
public class ProviderMapper {

    /**
     * Método que mapeia um Provider para um ProviderDTO.
     *
     * @param provider objeto que será mapeado.
     * @param status   Status do Fornecedor.
     * @return ProviderDTO com as informações do fornecedor.
     */
    public ProviderDTO providerToDTO(Provider provider, ProviderStatus status) {
        ProviderDTO dto = new ProviderDTO();
        dto.providerAPIID = provider.getId();
        dto.name = provider.getName();
        dto.social_name = provider.getSocianName();
        dto.cnpj = provider.getCnpj();
        dto.address = provider.getAdress();
        dto.city = provider.getCity();
        dto.phone = provider.getPhone();
        dto.homepage = provider.getHomepage();
        dto.email = provider.getEmail();
        dto.companyType = ProviderCompanyType.getDscription(provider.getCompanyType());
        dto.annual_billing = ProviderAnnualBilling.getDscription(provider.getAnnualBilling());
        dto.foundation_year = provider.getFundationYear();
        dto.qtd_clients = provider.getClientNumber();
        dto.reputation = ProviderReputation.getDscription(provider.getReputation());
        dto.category = ProviderCategory.getDscription(provider.getCategory());
        dto.qtd_items = provider.getItems();
        dto.percentage = provider.getPercent();
        dto.delivery_days = provider.getDeliveryDays();
        dto.rejection_rate = provider.getRejectionRate();
        dto.qtd_items_sold = provider.getQtdItemsSold();
        dto.status = status.name();
        return dto;
    }

    /**
     * Método que mapeia um ProviderEntity para um ProviderDTO.
     *
     * @param provider objeto que será mapeado.
     * @return ProviderDTO com as informações do fornecedor.
     */
    public ProviderDTO entityToDTO(ProviderEntity provider) {
        ProviderDTO dto = new ProviderDTO();
        dto.id = provider.getId().toString();
        dto.providerAPIID = provider.getProvidersAPIId();
        dto.name = provider.getName();
        dto.social_name = provider.getSocialName();
        dto.cnpj = provider.getCnpj();
        dto.address = provider.getAddress();
        dto.city = provider.getCity();
        dto.phone = provider.getPhone();
        dto.homepage = provider.getHomePage();
        dto.email = provider.getEmail();
        dto.companyType = provider.getCompanyType() != null ? ProviderCompanyType.getDscription(provider.getCompanyType().name()) : null;
        dto.annual_billing = provider.getAnnualBilling() != null ? ProviderAnnualBilling.getDscription(provider.getAnnualBilling().name()) : null;
        dto.foundation_year = provider.getFoundationYear();
        dto.qtd_clients = provider.getQtdClient();
        dto.reputation = provider.getReputation() != null ? ProviderReputation.getDscription(provider.getReputation().name()) : null;
        dto.category = provider.getCategory() != null ? ProviderCategory.getDscription(provider.getCategory().name()) : null;
        dto.qtd_items = provider.getItems();
        dto.percentage = provider.getPercentage();
        dto.delivery_days = provider.getDeliveryDays();
        dto.rejection_rate = provider.getRejectionRate();
        dto.qtd_items_sold = provider.getQtdItemsSold();
        dto.status = provider.getStatus().name();
        return dto;
    }

    /**
     * Método que mapeia um Provider para um ProviderEntity.
     *
     * @param provider objeto que será mapeado.
     * @param status   Status do Fornecedor.
     * @return ProviderEntity com as informações do fornecedor.
     */
    public ProviderEntity providerToEntity(Provider provider, ProviderStatus status) {
        ProviderEntity entity = new ProviderEntity();
        entity.setProvidersAPIId(provider.getId());
        entity.setName(provider.getName());
        entity.setSocialName(provider.getSocianName());
        entity.setCnpj(provider.getCnpj());
        entity.setAddress(provider.getAdress());
        entity.setCity(provider.getCity());
        entity.setPhone(provider.getPhone());
        entity.setHomePage(provider.getHomepage());
        entity.setEmail(provider.getEmail());
        entity.setCompanyType(ProviderCompanyType.getCompanyType(provider.getCompanyType()));
        entity.setAnnualBilling(ProviderAnnualBilling.getAnualBilling(provider.getAnnualBilling()));
        entity.setFoundationYear(provider.getFundationYear());
        entity.setQtdClient(provider.getClientNumber());
        entity.setReputation(ProviderReputation.getReputation(provider.getReputation()));
        entity.setCategory(ProviderCategory.getCategory(provider.getCategory()));
        entity.setItems(provider.getItems());
        entity.setPercentage(provider.getPercent());
        entity.setDeliveryDays(provider.getDeliveryDays());
        entity.setRejectionRate(provider.getRejectionRate());
        entity.setQtdItemsSold(provider.getQtdItemsSold());
        entity.setStatus(status);
        return entity;
    }

    /**
     * Método que mapeia um Provider para um ProviderEntity.
     *
     * @param provider objeto que será mapeado.
     * @param status   Status do Fornecedor.
     * @return ProviderEntity com as informações do fornecedor.
     */
    public ProviderEntity ProviderDTOToEntity(ProviderDTO provider, ProviderStatus status) {
        ProviderEntity entity = new ProviderEntity();
        entity.setProvidersAPIId(provider.providerAPIID);
        entity.setName(provider.name);
        entity.setSocialName(provider.social_name);
        entity.setCnpj(provider.cnpj);
        entity.setAddress(provider.address);
        entity.setCity(provider.city);
        entity.setPhone(provider.phone);
        entity.setHomePage(provider.homepage);
        entity.setEmail(provider.email);
        entity.setCompanyType(ProviderCompanyType.getCompanyType(provider.companyType));
        entity.setAnnualBilling(ProviderAnnualBilling.getAnualBilling(provider.annual_billing));
        entity.setFoundationYear(provider.foundation_year);
        entity.setQtdClient(provider.qtd_clients);
        entity.setReputation(ProviderReputation.getReputation(provider.reputation));
        entity.setCategory(ProviderCategory.getCategory(provider.category));
        entity.setItems(provider.qtd_items);
        entity.setPercentage(provider.percentage);
        entity.setDeliveryDays(provider.delivery_days);
        entity.setRejectionRate(provider.rejection_rate);
        entity.setQtdItemsSold(provider.qtd_items_sold);
        entity.setStatus(status);
        entity.setActivatedDate(LocalDate.now());
        return entity;
    }

}
