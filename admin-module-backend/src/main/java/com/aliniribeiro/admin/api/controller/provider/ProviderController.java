package com.aliniribeiro.admin.api.controller.provider;

import com.aliniribeiro.admin.api.common.Response;
import com.aliniribeiro.admin.api.common.util.Spring;
import com.aliniribeiro.admin.api.controller.APIPaths;
import com.aliniribeiro.admin.api.controller.provider.client.ProvidersClient;
import com.aliniribeiro.admin.api.model.provider.ProviderEntity;
import com.aliniribeiro.admin.api.model.provider.ProviderRepository;
import com.aliniribeiro.admin.api.model.provider.enums.ProviderStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import providers.wsdl.ActivateProviderResponse;
import providers.wsdl.GetAllProvidersResponse;
import providers.wsdl.Provider;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(APIPaths.PROVIDER)
@CrossOrigin(origins = "*")
public class ProviderController {

    /**
     * API que busca todos os fornecedores vÍNCULADOS.
     * É realizada a comunicação com a API de fornecedores para buscar estas informações.
     *
     * @return
     */
    @GetMapping(value = "/my")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<List<ProviderDTO>>> listMyProviders() {
        Response<List<ProviderDTO>> response = new Response<List<ProviderDTO>>();
        try {
            List<ProviderEntity> providerList = Spring.bean(ProviderRepository.class).findByStatus(ProviderStatus.ACTIVE);
            response.setData(providerList.stream().map(p -> Spring.bean(ProviderMapper.class).entityToDTO(p)).collect(Collectors.toList()));
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    /**
     * API que busca todos os fornecedores disponíveis para habilitação dos mesmos.
     * É realizada a comunicação com a API de fornecedores para buscar estas informações.
     *
     * @return
     */
    @GetMapping(value = "/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<List<ProviderDTO>>> listAllProviders() {
        Response<List<ProviderDTO>> response = new Response<List<ProviderDTO>>();
        try {
            GetAllProvidersResponse providersResponse = Spring.bean(ProvidersClient.class).getAllproviders();
            List<Provider> providerList = providersResponse.getProviders().getData();
            //Tira da lista os fornecedores que já estão habilitados no sistema
            List<Provider> validatedProviders = validateProviders(providerList);
            response.setData(validatedProviders.stream().map(p -> Spring.bean(ProviderMapper.class).providerToDTO(p, ProviderStatus.INACTIVE)).collect(Collectors.toList()));
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
        return ResponseEntity.ok(response);
    }

    /**
     * API que busca  um fornecedor pelo seu ID.
     *
     * @return Dados do fornecedor de acordo com o ID solicitado.
     */
    @GetMapping(value = "/find/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<ProviderDTO>> findById(@PathVariable("id") UUID id) {
        Response<ProviderDTO> response = new Response<ProviderDTO>();
        try {
            ProviderEntity provider = Spring.bean(ProviderRepository.class).findById(id);
            response.setData(Spring.bean(ProviderMapper.class).entityToDTO(provider));
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Faz uma validação para não listar os fornecedores que já estão cadastrados no sistema.
     *
     * @param providerList Lista de fornecedores recebida da aplicação de Fornecedores.
     * @return Lista de fornecedores válidos.
     */
    private List<Provider> validateProviders(List<Provider> providerList) {
        List<Provider> validsproviders = new ArrayList<>();
        List<ProviderEntity> activeProviders = Spring.bean(ProviderRepository.class).findByStatus(ProviderStatus.ACTIVE);
        List<String> ids = activeProviders.stream().map(p -> p.getProvidersAPIId()).collect(Collectors.toList());
        providerList.forEach(p -> {
            if (!ids.contains(p.getId())) {
                validsproviders.add(p);
            }
        });
        return validsproviders;
    }

    /**
     * API que ativa um fornecedor.
     * É realizada a comunicação com a API de fornecedores para habilitar o vínculo do fornecedor
     *
     * @return
     */
    @PostMapping(value = "/activate")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<ProviderDTO>> activateProvider(@Valid @RequestBody ProviderDTO provider) {
        Response<ProviderDTO> response = new Response<ProviderDTO>();
        try {
            ActivateProviderResponse providersResponse = Spring.bean(ProvidersClient.class).activateProvider(provider.providerAPIID);
            if (providersResponse.getMessage() != null) {
                ProviderEntity providerEntity = Spring.bean(ProviderMapper.class).ProviderDTOToEntity(provider, ProviderStatus.ACTIVE);
                Spring.bean(ProviderRepository.class).save(providerEntity);
                response.setData(Spring.bean(ProviderMapper.class).entityToDTO(providerEntity));
                System.err.println(providersResponse.getMessage());
            }
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    /**
     * API que desativa um fornecedor.
     * É realizada a comunicação com a API de fornecedores para desabilitar o vínculo do fornecedor
     *
     * @return
     */
    @DeleteMapping(value = "disable/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<String>> delete(@PathVariable("id") UUID id) {
        Response<String> response = new Response<String>();
        ProviderEntity provider = Spring.bean(ProviderRepository.class).findById(id);
        if (provider == null) {
            response.getErrors().add("Registered id not found: " + id);
            return ResponseEntity.badRequest().body(response);
        }
        Spring.bean(ProvidersClient.class).disableProvider(provider.getProvidersAPIId());
        Spring.bean(ProviderRepository.class).delete(provider);
        return ResponseEntity.ok(new Response<>());
    }

    @GetMapping(value = "/totalProviders")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<String>> getTotalProviders() {
        Response<String> response = new Response<String>();
        try {
            Optional<String> serviceResponse = getTotalProvidersFromRepository();
            if (serviceResponse.isPresent()) {
                response.setData(serviceResponse.get());
            }
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Método que retorna o total de usuários cadastrados no sistema.
     *
     * @return Optional copm o total de usuários cadastrados no sistema.
     */
    public Optional<String> getTotalProvidersFromRepository() {
        Long total = Spring.bean(ProviderRepository.class).count();
        return Optional.ofNullable(total.toString());
    }
}
