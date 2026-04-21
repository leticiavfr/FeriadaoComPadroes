package decorator;

import model.Pedido;

public class Desconto10 extends PedidoDecorator{
    public Desconto10(Pedido pedido){
        super(pedido);
    }

    public String getItem(){
        return pedido.getItem() + " + desconto de 10%";
    }

    public double getPreco(){
        double preco = pedido.getPreco();
        return preco - ((preco*10)/100);
    }
}