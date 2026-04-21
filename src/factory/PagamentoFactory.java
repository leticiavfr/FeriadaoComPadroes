package factory;

import strategy.*;
import adapter.CartaoAPI;

public class PagamentoFactory{
    public PagamentoStrategy getMetodo(String type){
        if (type.equalsIgnoreCase("cartao"))
            return new PagamentoCartaoAdapter(new CartaoAPI());
        else if (type.equalsIgnoreCase("pix"))
            return new PagamentoPix();
        else if (type.equalsIgnoreCase("dinheiro"))
            return new PagamentoDinheiro();
        else
            return null;
    }
}