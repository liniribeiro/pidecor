package com.aliniribeiro.admin.api.controller.customer;

import com.aliniribeiro.admin.api.common.exceptions.ExceptionType;
import com.aliniribeiro.admin.api.common.util.Spring;
import com.aliniribeiro.admin.api.controller.customer.contracts.CustomerDTO;
import com.aliniribeiro.admin.api.controller.user.UserService;
import com.aliniribeiro.admin.api.model.customer.CustomerEntity;
import com.aliniribeiro.admin.api.model.customer.CustomerRepository;
import com.aliniribeiro.admin.api.model.user.UserEntity;
import com.aliniribeiro.admin.api.model.user.enums.UserProfile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class CustomerService {

    /**
     * Método que cria um novo cliente.
     *
     * @param customerDTO dto com as informações do cliente que será criado.
     * @return DTO com as informações d cliente já atualizado.
     */
    public CustomerDTO createClient(CustomerDTO customerDTO) {
        validateClient(customerDTO);
        UserEntity useraccount = Spring.bean(UserService.class).createUser(customerDTO.email, customerDTO.password, customerDTO.name, UserProfile.ROLE_CUSTOMER);
        CustomerEntity customer = new CustomerEntity();
        customer.setAddress(customerDTO.address);
        customer.setCpf(customerDTO.cpf);
        customer.setCreationDate(LocalDate.now());
        customer.setName(customerDTO.name);
        customer.setUser(useraccount.getId());
        try {
            Spring.bean(CustomerRepository.class).save(customer);
        } catch (Exception e) {
            Spring.bean(UserService.class).deleteUser(useraccount);
        }

        return Spring.bean(CustomerMapper.class).customerDTO(customer);
    }

    /**
     * Método que valida se o DTO informado está com os parâmetros corretos.
     *
     * @param customerDTO DTO que será realizado a validação.
     */
    private void validateClient(CustomerDTO customerDTO) {
        if (customerDTO.email == null || customerDTO.address == null || customerDTO.cpf == null || customerDTO.password == null) {
            throw new IllegalArgumentException(ExceptionType.MISSING_INFORMATIONS.name());
        }
    }

    /**
     * Método que retorna o total de clientes cadastrados no sistema.
     *
     * @return Optional copm o total de clientes cadastrados no sistema.
     */
    public Optional<String> getTotalClients() {
        Long totalClients = Spring.bean(CustomerRepository.class).count();
        return Optional.ofNullable(totalClients.toString());
    }

}
