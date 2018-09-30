package com.aliniribeiro.admin.api.controller.item;

import com.aliniribeiro.admin.api.controller.APIPaths;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIPaths.ITEM)
@CrossOrigin(origins = "*")
public class ItemController {

    /**
     * Busca de itens para o catálogo
     * O DTO de item deve trazer informações do fornecedor, como a categoria, o nome do mesmo
     * para todos os itens retornados do catálogo, deve ser realizada uma consulta na base de dados dos fornecedores para buscar os itens disponíveis.
     * Como o sistema de fornecedores disponibiliza seus serviços para muitos E-commerces, a informação sobre os itens disponíveis.
     */
}
