package com.aliniribeiro.admin.api.controller.monitoringreport;

import com.aliniribeiro.admin.api.common.Response;
import com.aliniribeiro.admin.api.common.util.Spring;
import com.aliniribeiro.admin.api.controller.APIPaths;
import com.aliniribeiro.admin.api.controller.monitoringreport.contracts.MonitoringReportDTO;
import com.aliniribeiro.admin.api.controller.monitoringreport.contracts.MonitoringReportResponseDTO;
import com.aliniribeiro.admin.api.model.customerorder.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(APIPaths.REPORT)
@CrossOrigin(origins = "*")
public class MonitoringReportController {


    @PostMapping(value = "/findData")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<List<MonitoringReportResponseDTO>>> findData(@Valid @RequestBody MonitoringReportDTO data) {
        Response<List<MonitoringReportResponseDTO>> response = new Response<List<MonitoringReportResponseDTO>>();
        try {
            Optional<List<MonitoringReportResponseDTO>> responseDto = Spring.bean(MonitoringReportService.class).findAllData(data);
            if (responseDto.isPresent()) {
                response.setData(responseDto.get());

            }
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
