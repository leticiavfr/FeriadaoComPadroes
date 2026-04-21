package observer;

import model.Pedido;

public class Email implements Observer{
    public void update(Pedido pedido){
        System.out.println("Email: Status do pedido - " + pedido.getStatus());
    }
}