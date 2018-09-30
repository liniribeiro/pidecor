package com.aliniribeiro.admin.api.controller.customerorder;

import com.aliniribeiro.admin.api.common.Response;
import com.aliniribeiro.admin.api.common.util.Spring;
import com.aliniribeiro.admin.api.controller.APIPaths;
import com.aliniribeiro.admin.api.controller.customerorder.contracts.ClientOrderByStatusDTO;
import com.aliniribeiro.admin.api.controller.customerorder.contracts.ClientOrderMonthsEvolutionDTO;
import com.aliniribeiro.admin.api.controller.customerorder.contracts.OrderDTO;
import com.aliniribeiro.admin.api.controller.customerorder.contracts.ReceivedValueByMonthDTO;
import com.aliniribeiro.admin.api.controller.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(APIPaths.CUSTOMER_ORDER)
@CrossOrigin(origins = "*")
public class CustomerOrderController {


    @PostMapping(value = "/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<OrderDTO>> createOrder(HttpServletRequest request, @RequestBody OrderDTO order, BindingResult result) {
        Response<OrderDTO> response = new Response<OrderDTO>();
        try {
            OrderDTO orderSaved = Spring.bean(CustomerOrderService.class).createOrder(order);
            response.setData(orderSaved);
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    //Card Informações Gerenciais 2 - evolução de pedidos criados nos últimos 6 meses
    @GetMapping(value = "/getOrderEvolutionValue")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<List<ClientOrderMonthsEvolutionDTO>>> getOrderEvolutionValue() {
        Response<List<ClientOrderMonthsEvolutionDTO>> response = new Response<List<ClientOrderMonthsEvolutionDTO>>();
        try {
            Optional<List<ClientOrderMonthsEvolutionDTO>> orders = Spring.bean(CustomerOrderService.class).getOrderEvolutionValue();
            if (orders.isPresent()) {
                response.setData(orders.get());
            }
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    //Card Informações Gerenciais 1 - Pedidos por Status
    @GetMapping(value = "/allByStatus")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<List<ClientOrderByStatusDTO>>> findAllByStatus() {
        Response<List<ClientOrderByStatusDTO>> response = new Response<List<ClientOrderByStatusDTO>>();
        try {
            Optional<List<ClientOrderByStatusDTO>> orders = Spring.bean(CustomerOrderService.class).findAllByStatus();
            if (orders.isPresent()) {
                response.setData(orders.get());
            }
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    //Card Informações Gerenciais 3 - Massa mensal de pagamentos recebidos pelos itens vendidos dos fornecedores
    @GetMapping(value = "/receivedValueByMonth")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<List<ReceivedValueByMonthDTO>>> getReceivedValueByMonth() {
        Response<List<ReceivedValueByMonthDTO>> response = new Response<List<ReceivedValueByMonthDTO>>();
        try {
            Optional<List<ReceivedValueByMonthDTO>> orders = Spring.bean(CustomerOrderService.class).getReceivedValueByMonth();
            if (orders.isPresent()) {
                response.setData(orders.get());
            }
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    //Card Informações Gerenciais 5 - Massa  recebida pelos fornecedores no mês corrente.
    @GetMapping(value = "/receivedValueCurentMonth")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<String>> getReceivedValueCurentMonth() {
        Response<String> response = new Response<String>();
        try {
            Optional<String> orders = Spring.bean(CustomerOrderService.class).getReceivedValueCurentMonth();
            if (orders.isPresent()) {
                response.setData(orders.get());
            }
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
