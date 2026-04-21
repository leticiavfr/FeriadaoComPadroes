package proxy;

import strategy.PagamentoStrategy;
import singleton.Config;

public class PagamentoProxy implements PagamentoStrategy{
    private PagamentoStrategy pagamento;
    private String tipo;
    private int qtdTentativas;

    public PagamentoProxy (PagamentoStrategy pagamento, String tipo){
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