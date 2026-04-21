package strategy;

public class PagamentoPix implements PagamentoStrategy{
    public boolean pagar(double valor){
        System.out.println("Pagamento de R$" + valor + " feito via Pix");
        return true;
    }
}