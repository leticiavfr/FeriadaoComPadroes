package decorator;

import model.Pedido;

public class FreteExpresso extends PedidoDecorator{
    public FreteExpresso(Pedido pedido){
        super(pedido);
    }

    public String getItem(){
        return pedido.getItem() + " + frete expresso";
    }

    public double getPreco(){
        return pedido.getPreco() + 20.0;
    }
}