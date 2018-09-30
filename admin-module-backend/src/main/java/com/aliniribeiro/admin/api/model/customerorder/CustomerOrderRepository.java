package com.aliniribeiro.admin.api.model.customerorder;

import com.aliniribeiro.admin.api.model.customerorder.enums.OrderStatus;
import com.aliniribeiro.admin.api.model.common.RepositoryBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CustomerOrderRepository extends RepositoryBase<CustomerOrderEntity>, CustomerOrderRepositoryCustom {

    Page<CustomerOrderEntity> findAll(Pageable pageable);

    List<CustomerOrderEntity> findByStatus(OrderStatus status);

    CustomerOrderEntity findById(UUID id);
}
