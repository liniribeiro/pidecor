package com.aliniribeiro.admin.api.model.customerorder;

import com.aliniribeiro.admin.api.model.item.ItemEntity;
import com.querydsl.core.Tuple;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface CustomerOrderRepositoryCustom {

    /**
     * Método que retorna todos os pedidos realizados, agrupados pelo seu status
     *
     * @return Lista com as informações dos status do pedido e sua quantidade.
     */
    Optional<List<Tuple>> findAllByStatus();

    /**
     * Método que retorna a quantidade de pedidos realizados mensalmente nos últimos 6 meses.
     *
     * @return Lista com as informações dos pedidos.
     */
    Optional<Long> getOrderEvolutionValue(YearMonth month);

    /**
     * Método que uma lista com os itens vendidos no mês.
     *
     * @return Lista com os itens que foram vendidos no mês solicitado.
     */
    Optional<List<ItemEntity>> getItemsSoldByMonth(YearMonth month);

    /**
     * Busca todos os pedidos que foram gerados entre os mêses informados po parâmetro.
     * @param startDate data de início.
     * @param endDate data de fim.
     * @return Lista com todos os pedidos encontrados.
     */
    Optional<List<CustomerOrderEntity>> findBetweenDates(LocalDate startDate, LocalDate endDate);

    /**
     * Busca todos os pedidos que não foram entregues.
     * @return Lista com os pedidos encontrados.
     */
    Optional<List<CustomerOrderEntity>> findNotDeliveredOrders();

}
