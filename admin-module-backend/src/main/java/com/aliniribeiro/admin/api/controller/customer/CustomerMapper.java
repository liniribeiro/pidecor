package com.aliniribeiro.admin.api.controller.customer;

import com.aliniribeiro.admin.api.controller.customer.contracts.CustomerDTO;
import com.aliniribeiro.admin.api.model.customer.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO customerDTO(CustomerEntity entity) {
        if (entity == null) {
            return null;
        }

        CustomerDTO dto = new CustomerDTO();
        dto.address = entity.getAddress();
        dto.cpf = entity.getCpf();
        return dto;
    }
}
