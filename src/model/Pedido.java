package model;

import observer.Observer;

public interface Pedido {
    String getItem();
    double getPreco();

    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();

    void setStatus(String status);
    String getStatus();
}