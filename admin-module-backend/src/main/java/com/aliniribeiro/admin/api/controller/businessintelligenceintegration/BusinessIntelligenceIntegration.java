package com.aliniribeiro.admin.api.controller.businessintelligenceintegration;

import com.aliniribeiro.admin.api.common.util.Spring;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Classe  que envia as informaçôes gerenciais para o sistema integrado de Business Intelligence
 */
@Component
public class BusinessIntelligenceIntegration {

    // Mock criada com Postman para simular uma API de Terceiros de Business Intelligence
    private final String URL = "https://77b9592d-154b-4a1f-9d80-a7652cdc55b7.mock.pstmn.io";

    public void sendBIData() {
        sendTotalOfClients();
        sendOrderEvolutionValue();
        sendAllByStatus();
        sendReceivedValueByMonth();
        sendReceivedValueCurentMonth();
    }

    /**
     * Método que busca as informações com o total de clientes cadastrados no sistema e
     * envia as informações para o sistema de terceiros.
     * <p>
     * É necessário realizar a implementação de:
     * - Chamar o serviço getTotalClients da classe CustomerService que retorna o total de clientes;
     * - Criar o DTO de envio para o sistema de terceiros de BI;
     * - Alterar o GET do serviço para POST, e adiciona o corpo da mensagem enviada.
     */
    private void sendTotalOfClients() {
        ResponseEntity<String> responseEntity = Spring.bean(RestTemplate.class).exchange(URL + "/totalOfClients", HttpMethod.POST, null, new ParameterizedTypeReference<String>() {
        });
        String response = responseEntity.getBody();
        System.out.println(response);
    }

    /**
     * Método que busca as informações da evolução de pedidos realizados no sistema e
     * envia as informações para o sistema de terceiros.
     * <p>
     * É necessário realizar a implementação de:
     * - Chamar o serviço getOrderEvolutionValue da classe CustomerOrderService que retorna as informações;
     * - Criar o DTO de envio para o sistema de terceiros de BI;
     * - Alterar o GET do serviço para POST, e adiciona o corpo da mensagem enviada.
     */
    private void sendOrderEvolutionValue() {
        ResponseEntity<String> responseEntity = Spring.bean(RestTemplate.class).exchange(URL + "/orderEvolutionValue", HttpMethod.POST, null, new ParameterizedTypeReference<String>() {
        });
        String response = responseEntity.getBody();
        System.out.println(response);
    }

    /**
     * Método que busca as informações dos realizados no sistema e separados pelo seu status e
     * envia as informações para o sistema de terceiros.
     * <p>
     * É necessário realizar a implementação de:
     * - Chamar o serviço findAllByStatus da classe CustomerOrderService que retorna as informações;
     * - Criar o DTO de envio para o sistema de terceiros de BI;
     * - Alterar o GET do serviço para POST, e adiciona o corpo da mensagem enviada.
     */
    private void sendAllByStatus() {
        ResponseEntity<String> responseEntity = Spring.bean(RestTemplate.class).exchange(URL + "/allByStatus", HttpMethod.POST, null, new ParameterizedTypeReference<String>() {
        });
        String response = responseEntity.getBody();
        System.out.println(response);
    }

    /**
     * Método que busca as informações dos do montante recebido dos fornecedores por mês e
     * envia as informações para o sistema de terceiros.
     * <p>
     * É necessário realizar a implementação de:
     * - Chamar o serviço getReceivedValueByMonth da classe CustomerOrderService que retorna as informações;
     * - Criar o DTO de envio para o sistema de terceiros de BI;
     * - Alterar o GET do serviço para POST, e adiciona o corpo da mensagem enviada.
     */
    private void sendReceivedValueByMonth() {
        ResponseEntity<String> responseEntity = Spring.bean(RestTemplate.class).exchange(URL + "/receivedValueByMonth", HttpMethod.POST, null, new ParameterizedTypeReference<String>() {
        });
        String response = responseEntity.getBody();
        System.out.println(response);
    }

    /**
     * Método que busca as informações dos do montante recebido dos fornecedores no mês atual e
     * envia as informações para o sistema de terceiros.
     * <p>
     * É necessário realizar a implementação de:
     * - Chamar o serviço getReceivedValueCurentMonth da classe CustomerOrderService  que retorna as informações;
     * - Criar o DTO de envio para o sistema de terceiros de BI;
     * - Alterar o GET do serviço para POST, e adiciona o corpo da mensagem enviada.
     */
    private void sendReceivedValueCurentMonth() {
        ResponseEntity<String> responseEntity = Spring.bean(RestTemplate.class).exchange(URL + "/receivedValueCurentMonth", HttpMethod.POST, null, new ParameterizedTypeReference<String>() {
        });
        String response = responseEntity.getBody();
        System.out.println(response);
    }
}
