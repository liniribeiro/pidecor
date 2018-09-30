package com.aliniribeiro.admin.api.model.provider;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProviderRepositoryCustom {

    UUID findByProviderAPIId(String id);

    Optional<List<ProviderEntity>> findBetweenDates(LocalDate startDate, LocalDate endDate);
}
