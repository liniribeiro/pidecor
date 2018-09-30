package com.aliniribeiro.admin.api.model.customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryCustom{

    Optional<List<CustomerEntity>> findBetweenDates(LocalDate startDate, LocalDate endDate);

}
