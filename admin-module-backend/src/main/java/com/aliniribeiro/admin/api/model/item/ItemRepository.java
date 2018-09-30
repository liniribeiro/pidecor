package com.aliniribeiro.admin.api.model.item;

import com.aliniribeiro.admin.api.model.common.RepositoryBase;
import com.aliniribeiro.admin.api.model.item.enums.ItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends RepositoryBase<ItemEntity>, ItemRepositoryCustom  {

    Page<ItemEntity> findAll(Pageable pageable);

    List<ItemEntity> findByStatus(ItemStatus status);

    ItemEntity findById(UUID id);

}
