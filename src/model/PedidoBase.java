package model;

import java.util.ArrayList;
import java.util.List;
import observer.Observer;

public abstract class PedidoBase implements Pedido{
    protected List<Observer> observers = new ArrayList<>();
    protected String status;

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for (Observer observer : observers){
            observer.update(this);
        }
    }

    public void setStatus(String status){
        this.status = status;
        notifyObservers(); 
    }

    public String getStatus(){
        return status;
    }
}