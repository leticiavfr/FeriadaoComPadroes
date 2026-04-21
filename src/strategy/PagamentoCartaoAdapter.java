package strategy;

import adapter.CartaoAPI;

public class PagamentoCartaoAdapter implements PagamentoStrategy{
    private CartaoAPI api;

    public PagamentoCartaoAdapter(CartaoAPI api){
        this.api = api;
    }

    public boolean pagar(double valor){
        api.pagamentoCartao(valor);
        return true;
    }
}