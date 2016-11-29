package representante.comercial;

import representante.comercial.dao.VendaDAO;
import representante.comercial.model.Venda;
import java.text.ParseException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author L
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {

        Venda v = new Venda();

        v.setNomeCliente("Marcio Lucas");
        v.setDataFaturamento("2016-11-27");
        v.setValorTotal(450.00);
        VendaDAO.create(v);

        ArrayList<Venda> vendas = VendaDAO.retreave();
        for (Venda vds : vendas) {
//            System.out.println(
//                    "Data faturamento: " + vds.getDataFaturamento() +
//                            ", Valor Total: " + vds.getValorTotal() + ", " +
//                            "Comissão total representante: " +
//                            vds.retornaComissaoRepresentanteTotal(vds.getValorTotal())
//            );

            System.out.println("-----------------------------------------------");
            System.out.println("Valor total da nota: R$ " + vds.getValorTotal());
            System.out.println("Data do faturamento: " + vds.getDataFaturamento());
            System.out.println("3 x de R$ " + vds.divideNf(vds.getValorTotal()));
            System.out.println("Comissão do representante por parcela: " +
                    vds.retornaComissaoRepresentantePorParcela(vds.getValorTotal()));
            System.out.println("Comissão do representante total: " +
                    vds.retornaComissaoRepresentanteTotal(vds.getValorTotal()));
            String[] datas = vds.retornaDatasPagamentoParcelaNf(vds.getDataFaturamento());
            System.out.println(" > Vencimentos");
            for (int i = 0; i < datas.length; i++) {
                System.out.println("   + Vencimentos nas datas " + vds.retornaDatasPagamentoParcelaNf(vds.getDataFaturamento())[i] + " ");
            }
            System.out.println(" > Pagamentos");
            for (int i = 0; i < datas.length; i++) {
                System.out.println("   + Pagamentos nas datas " + vds.retornaDiaPagamento(vds.retornaDatasPagamentoParcelaNf(vds.getDataFaturamento()))[i] + " ");
            }
            System.out.println("-----------------------------------------------");
        }
        ArrayList<Venda> totais = VendaDAO.somaValores("data_faturamento >= '2016-11-27' && data_faturamento <= '2016-11-28'");
        for (Venda total: totais) {
            System.out.println("TOTAL LIQUIDO DO PERÍODO: "+total.getValorTotal());
            System.out.println("TOTAL COMISSÃO DO PERÍODO: "+total.retornaComissaoRepresentanteTotal(total.getValorTotal()));
        }
    }

}

