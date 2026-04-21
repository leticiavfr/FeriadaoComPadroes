package strategy;

public class PagamentoDinheiro implements PagamentoStrategy{
    public boolean pagar(double valor){
        System.out.println("Pagamento de R$" + valor + " feito via Dinheiro");
        return true;
    }
}