package com.aliniribeiro.admin.api.model.provider;

import com.aliniribeiro.admin.api.model.provider.enums.ProviderStatus;
import com.aliniribeiro.admin.api.model.common.RepositoryBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProviderRepository extends RepositoryBase<ProviderEntity>, ProviderRepositoryCustom  {

    Page<ProviderEntity> findAll(Pageable pageable);

    List<ProviderEntity> findByStatus(ProviderStatus status);

    ProviderEntity findById(UUID id);


}
