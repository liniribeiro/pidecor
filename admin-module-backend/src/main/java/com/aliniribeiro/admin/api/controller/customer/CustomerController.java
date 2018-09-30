
package com.aliniribeiro.admin.api.controller.customer;

import com.aliniribeiro.admin.api.common.Response;
import com.aliniribeiro.admin.api.common.util.Spring;
import com.aliniribeiro.admin.api.controller.APIPaths;
import com.aliniribeiro.admin.api.controller.customer.contracts.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping(APIPaths.CUSTOMER)
@CrossOrigin(origins = "*")
public class CustomerController {


    @PostMapping(value = "/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<CustomerDTO>> createClient(HttpServletRequest request, @RequestBody CustomerDTO custumer, BindingResult result) {
        Response<CustomerDTO> response = new Response<CustomerDTO>();
        try {
            CustomerDTO createdCustumer = Spring.bean(CustomerService.class).createClient(custumer);
            response.setData(createdCustumer);
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    //Card Informações Gerenciais 4 - Total de clientes cadastrados
    @GetMapping(value = "/totalClients")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<String>> getTotalClients() {
        Response<String> response = new Response<String>();
        try {
            Optional<String> serviceResponse = Spring.bean(CustomerService.class).getTotalClients();
            if (serviceResponse.isPresent()) {
                response.setData(serviceResponse.get());
            }
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
