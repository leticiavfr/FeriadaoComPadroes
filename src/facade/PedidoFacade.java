package facade;

import model.*;
import decorator.*;
import observer.*;
import factory.PagamentoFactory;
import strategy.PagamentoStrategy;
import proxy.PagamentoProxy;

public class PedidoFacade{
    public void finalizarPedido(String tipoProduto, String tipoPagamento, boolean desconto, boolean frete){
        Pedido pedido;

        if (tipoProduto.equalsIgnoreCase("celular"))
            pedido = new Celular();
        else
            pedido = new Notebook();

        if (desconto)
            pedido = new Desconto10(pedido);
        if (frete)
            pedido = new FreteExpresso(pedido);

        pedido.addObserver(new Email());
        pedido.addObserver(new Sms()); 

        System.out.println("Pedido: " + pedido.getItem());
        double preco = pedido.getPreco();
        System.out.println("Valor final: " + preco);

        PagamentoFactory factory = new PagamentoFactory();
        PagamentoStrategy metodo = factory.getMetodo(tipoPagamento);

        metodo = new PagamentoProxy(metodo, tipoPagamento);

        boolean resultado = metodo.pagar(preco);

        if (resultado)
            pedido.setStatus("Pagamento aprovado");
        else
            pedido.setStatus("Pagamento recusado");
    }
}