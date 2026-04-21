package observer;

import model.Pedido;

public interface Observer {
    void update(Pedido pedido);
}