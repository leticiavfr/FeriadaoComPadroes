import java.util.List;
import java.util.ArrayList;

interface Pedido{
    String getItem();
    double getPreco();

    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();

    void setStatus(String status);
    String getStatus();
}

interface Observer{
    void update(Pedido pedido);
}

interface PagamentoStrategy{
    boolean pagar(double valor);
}

class PagamentoCartao implements PagamentoStrategy{
    public boolean pagar(double valor){
        System.out.println("Pagamento de R$" + valor + " feito via Cartão");
        return true;
    }
}

class PagamentoPix implements PagamentoStrategy{
    public boolean pagar(double valor){
        System.out.println("Pagamento de R$" + valor + " feito via Pix");
        return true;
    }
}

class PagamentoDinheiro implements PagamentoStrategy{
    public boolean pagar(double valor){
        System.out.println("Pagamento de R$" + valor + " feito via Dinheiro");
        return true;
    }
}

class PagamentoFactory{
    public PagamentoStrategy getMetodo(String type){
        if (type.equalsIgnoreCase("cartao"))
            return new PagamentoCartaoAdapter(new CartaoAPI());
        else if (type.equalsIgnoreCase("pix"))
            return new PagamentoPix();
        else if (type.equalsIgnoreCase("dinheiro"))
            return new PagamentoDinheiro();
        else
            return null;
    }
}

class PagamentoProxy implements PagamentoStrategy{
    private PagamentoStrategy pagamento;
    private String tipo;
    private int qtdTentativas;

    PagamentoProxy (PagamentoStrategy pagamento, String tipo){
        this.pagamento = pagamento;
        this.tipo = tipo;
        this.qtdTentativas = 0;
    }

    public boolean pagar(double valor){
        System.out.println("Tentando efetuar pagamento");

        if (tipo.equalsIgnoreCase("dinheiro")){
            System.out.println("Método de pagamento inválido");
            return false;
        }
        else if (qtdTentativas >= Config.getInstance().getQtdTentativas()){
            System.out.println("Número de pagamentos excedido");
            return false;
        }

        qtdTentativas++;

        pagamento.pagar(valor);

        return true;
    }
}

//Adapter
class CartaoAPI {
    public void pagamentoCartao(double valor){
        System.out.println("Pagamento de R$" + valor + " no cartão efetuado com sucesso");
    }
}

class PagamentoCartaoAdapter implements PagamentoStrategy{
    private CartaoAPI api;

    public PagamentoCartaoAdapter(CartaoAPI api){
        this.api = api;
    }

    public boolean pagar(double valor){
        api.pagamentoCartao(valor);
        return true;
    }
}

//Singleton
class Config{
    public String nome;
    private int qtdTentativas;

    private Config(){
        nome = "E-Commerce";
        qtdTentativas = 2;
    }

    private static Config instance;

    public int getQtdTentativas(){
        return qtdTentativas;
    }

    public static Config getInstance(){
        if (instance == null)
            instance = new Config();
    
        return instance;
    }
}

abstract class PedidoBase implements Pedido{
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

class Celular extends PedidoBase{
    public String getItem(){
        return "Celular";
    }

    public double getPreco(){
        return 2800.0;
    }
}

class Notebook extends PedidoBase{
    public String getItem(){
        return "Notebook";
    }

    public double getPreco(){
        return 3300.0;
    }
}

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

class Desconto10 extends PedidoDecorator{
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

class FreteExpresso extends PedidoDecorator{
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

class Email implements Observer{
    public void update(Pedido pedido){
        System.out.println("Email: Status do pedido - " + pedido.getStatus());
    }
}

class Sms implements Observer {
    public void update(Pedido pedido){
        System.out.println("SMS: Status do pedido - " + pedido.getStatus());
    }
}

class PedidoFacade{
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