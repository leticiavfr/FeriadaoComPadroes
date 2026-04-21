package observer;

import model.Pedido;

public class Sms implements Observer{
    public void update(Pedido pedido){
        System.out.println("SMS: Status do pedido - " + pedido.getStatus());
    }
}