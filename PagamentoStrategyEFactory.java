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
            return new PagamentoCartao();
        else if (type.equalsIgnoreCase("pix"))
            return new PagamentoPix();
        else if (type.equalsIgnoreCase("dinheiro"))
            return new PagamentoDinheiro();
        else
            return null;
    }
}

public class Main{
    public static void main(String[] args){
        PagamentoFactory factory = new PagamentoFactory();

        PagamentoStrategy metodo = factory.getMetodo("pix");
        metodo.pagar(100.0);
        
        metodo = factory.getMetodo("dinheiro");
        metodo.pagar(80.0);
    }
}