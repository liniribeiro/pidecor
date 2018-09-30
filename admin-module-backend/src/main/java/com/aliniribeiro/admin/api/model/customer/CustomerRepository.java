package com.aliniribeiro.admin.api.model.customer;

import com.aliniribeiro.admin.api.model.common.RepositoryBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends RepositoryBase<CustomerEntity>, CustomerRepositoryCustom  {

    Page<CustomerEntity> findAll(Pageable pageable);

    List<CustomerEntity> findBycpf(String cpf);

    CustomerEntity findById(UUID id);

}
