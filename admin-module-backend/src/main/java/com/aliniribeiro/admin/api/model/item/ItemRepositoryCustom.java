package com.aliniribeiro.admin.api.model.item;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ItemRepositoryCustom {

    Optional<List<ItemEntity>> findBetweenDates(LocalDate startDate, LocalDate endDate);

}
