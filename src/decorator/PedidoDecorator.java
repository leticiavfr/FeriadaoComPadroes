package decorator;

import model.Pedido;
import observer.Observer;

abstract class PedidoDecorator implements Pedido{
    protected Pedido pedido;

    public PedidoDecorator(Pedido pedido){
        this.pedido = pedido;
    }

    public void addObserver(Observer observer){
        pedido.addObserver(observer);
    }

    public void removeObserver(Observer observer){
        pedido.removeObserver(observer);
    }

    public void notifyObservers(){
        pedido.notifyObservers();
    }

    public void setStatus(String status){
        pedido.setStatus(status);
    }

    public String getStatus(){
        return pedido.getStatus();
    }
}
