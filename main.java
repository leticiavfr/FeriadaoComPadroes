interface PagamentoStrategy {
    void pagar(double valor);
}

class PagamentoCartao implements PagamentoStrategy{
    public void pagar(double valor){
        System.out.println("Pagamento de R$" + valor + " feito via Cartão");
    }
}

class PagamentoPix implements PagamentoStrategy{
    public void pagar(double valor){
        System.out.println("Pagamento de R$" + valor + " feito via Pix");
    }
}

class PagamentoDinheiro implements PagamentoStrategy{
    public void pagar(double valor){
        System.out.println("Pagamento de R$" + valor + " feito via Dinheiro");
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

    public void pagar(double valor){
        System.out.println("Tentando efetuar pagamento");

        if (tipo.equalsIgnoreCase("dinheiro")){
            System.out.println("Método de pagamento inválido");
            return;
        }
        else if (qtdTentativas >= Config.getInstance().getQtdTentativas()){
            System.out.println("Número de tentativas de pagamento excedido");
            return;
        }

        qtdTentativas++;

        pagamento.pagar(valor);

        System.out.println("Pagamento realizado com sucesso");
    }
}

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

    public void pagar(double valor){
        api.pagamentoCartao(valor);
    }
}

//Singleton
class Config{
    public String nome;
    private int qtdTentativas;

    private Config(){
        nome = "E-Commerce";
        qtdTentativas = 3;
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

public class Main{
    public static void main(String[] args){
        PagamentoFactory factory = new PagamentoFactory();

        PagamentoStrategy metodo = factory.getMetodo("pix");
        metodo = new PagamentoProxy(metodo, "pix");
        metodo.pagar(100.0);
        metodo.pagar(50.0);
        metodo.pagar(17.0);
        metodo.pagar(80.0);
        
        System.out.println();

        metodo = factory.getMetodo("dinheiro");
        metodo = new PagamentoProxy(metodo, "dinheiro");
        metodo.pagar(80.0);

        metodo = factory.getMetodo("cartao");
        metodo = new PagamentoProxy(metodo, "cartao");
        metodo.pagar(120.0);
    }
}