package main;

import facade.PedidoFacade;

public class Main{
    public static void main(String[] args){
        PedidoFacade pedidoFacade = new PedidoFacade();

        pedidoFacade.finalizarPedido("Celular", "Pix", true, true);
        System.out.println();
        pedidoFacade.finalizarPedido("Notebook", "Dinheiro", false, true);
        System.out.println();
        pedidoFacade.finalizarPedido("Notebook", "Cartao", true, false);
    }
}