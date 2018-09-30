package mock;

import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProvidersEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private ProviderRepository providerRepository;

    @Autowired
    public ProvidersEndpoint(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProvidersByIdRequest")
    @ResponsePayload
    public GetProvidersByIdResponse getProviderByName(@RequestPayload GetProvidersByIdRequest request) {
        GetProvidersByIdResponse response = new GetProvidersByIdResponse();
        response.setProvider(providerRepository.findProviderById(request.getId()));
        System.err.println(">>>>>>>>>>>>>>  Enviando fornecedor solicitado");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProvidersRequest")
    @ResponsePayload
    public GetAllProvidersResponse getProviders(@RequestPayload GetAllProvidersRequest request) {
        GetAllProvidersResponse response = new GetAllProvidersResponse();
        response.setProviders(providerRepository.getAll());
        System.err.println(">>>>>>>>>>>>>>  Enviando todos os fornecedores");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "disableProviderRequest")
    @ResponsePayload
    public DisableProviderResponse disableProvider(@RequestPayload DisableProviderRequest request) {
        DisableProviderResponse response = new DisableProviderResponse();
        String message =String.format(">>>>>>>>>>>>>> O fornecedor com o ID %s foi desabilitado para a empresa %s", request.getId(), request.getCompanyname());
        response.setMessage(message);
        System.err.println(message);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "activateProviderRequest")
    @ResponsePayload
    public ActivateProviderResponse activateProvider(@RequestPayload ActivateProviderRequest request) {
        ActivateProviderResponse response = new ActivateProviderResponse();
        String message = String.format(">>>>>>>>>>>>>> O fornecedor com o ID %s foi habilitado para a empresa %s", request.getId(), request.getCompanyname());
        response.setMessage(message);
        System.err.println(message);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMyProvidersRequest")
    @ResponsePayload
    public GetMyProvidersResponse getMyProvidersRequest(@RequestPayload GetMyProvidersRequest request) {
        GetMyProvidersResponse response = new GetMyProvidersResponse();
        response.setProviders(providerRepository.getAll());
        System.err.println(String.format(">>>>>>>>>>>>>> Enviando todos os fornecedores da empresa %s", request.getCompanyId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getItemsByIdRequest")
    @ResponsePayload
    public GetItemsByIdResponse getItemsById(@RequestPayload GetItemsByIdRequest request) {
        GetItemsByIdResponse response = new GetItemsByIdResponse();
        response.setItem(providerRepository.findItemById(request.getId()));
        System.err.println(">>>>>>>>>>>>>>  Enviando fornecedor solicitado");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllItemsRequest")
    @ResponsePayload
    public GetAllItemsResponse getItems(@RequestPayload GetAllItemsRequest request) {
        GetAllItemsResponse response = new GetAllItemsResponse();
        response.setItems(providerRepository.getAllItems());
        System.err.println(">>>>>>>>>>>>>>  Enviando todos os fornecedores");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createOrderRequest")
    @ResponsePayload
    public CreateOrderResponse createOrder(@RequestPayload CreateOrderRequest request) {
        CreateOrderResponse response = new CreateOrderResponse();
        System.err.println(">>>>>>>>>>>>>  Itens recebidos" + request.getOrder().getItems());
        response.setItems(providerRepository.getAllItems());
        response.setOrder("1234567888");
        System.err.println(">>>>>>>>>>>>>  Pedido realizado com sucesso, itens do pedido foram enviados para o solicitante!");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderStatusRequest")
    @ResponsePayload
    public GetOrderStatusResponse getOrderByStatus(@RequestPayload GetOrderStatusRequest request) {
        GetOrderStatusResponse response = new GetOrderStatusResponse();
        response.setStatus("SEPARATION_ORDER");
        System.err.println(">>>>>>>>>>>>>>  Enviando o status dos fornecedores solicitados");
        return response;
    }

}
