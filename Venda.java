package representante.comercial.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Marcio on 27/11/2016.
 */
public class Venda {

    private int idVenda;
    private String nomeCliente;
    private String dataFaturamento;
    private double valorTotal;

    public Venda() {
    }

    public Venda(String nomeCliente, String dataFaturamento, double valorTotal) {
        this.nomeCliente = nomeCliente;
        this.dataFaturamento = dataFaturamento;
        this.valorTotal = valorTotal;
    }

    public Venda(int idVenda, String nomeCliente, String dataFaturamento, double valorTotal) {
        this.idVenda = idVenda;
        this.nomeCliente = nomeCliente;
        this.dataFaturamento = dataFaturamento;
        this.valorTotal = valorTotal;
    }

    public Venda(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDataFaturamento() {
        return dataFaturamento;
    }

    public void setDataFaturamento(String dataFaturamento) {
        this.dataFaturamento = dataFaturamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public double divideNf(double valorTotal) {
        return valorTotal / 3;
    }

    public String[] retornaDatasPagamentoParcelaNf(String data) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date dataFormatada = formato.parse(data);

        Calendar c = Calendar.getInstance();
        c.setTime(dataFormatada);

        int diasParcela = 45;
        String[] arrDatasVencimentoParcela = new String[3];
        for (int i = 0; i < 3; i++) {
            c.add(Calendar.DATE, diasParcela);
            arrDatasVencimentoParcela[i] = formato.format(c.getTime());
            diasParcela = 15;
        }
        return arrDatasVencimentoParcela;
    }

    public double retornaComissaoRepresentantePorParcela(double valorTotalNF) {
        double valTotalPorParcela = this.divideNf(valorTotalNF);
        return valTotalPorParcela * 0.05;
    }

    public double retornaComissaoRepresentanteTotal(double valorTotalNF) {
        return valorTotalNF * 0.05;
    }

    public String[] retornaDiaPagamento(String[] datasVencimento) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        String[] arrDataPagamentos = new String[3];

        for (int i = 0; i < datasVencimento.length; i++) {
            Date dataFormatada = formato.parse(datasVencimento[i]);
            c.setTime(dataFormatada);
            int diaMes = c.get(Calendar.DAY_OF_MONTH);
            if (diaMes >= 1 && diaMes <= 10) {
                c.set(Calendar.DAY_OF_MONTH, 20);
                arrDataPagamentos[i] = formato.format(c.getTime());
            } else if (diaMes >= 11 && diaMes <= 20) {
                if (c.get(Calendar.MONTH) != 12) {
                    c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
                    c.set(Calendar.DAY_OF_MONTH, 1);
                    arrDataPagamentos[i] = formato.format(c.getTime());
                } else {
                    c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
                    c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
                    c.set(Calendar.DAY_OF_MONTH, 1);

                    arrDataPagamentos[i] = formato.format(c.getTime());
                }
            } else if ((diaMes >= 21 && diaMes <= 30) || (diaMes <= 31)) {

                if (c.get(Calendar.MONTH) != 12) {
                    c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
                    c.set(Calendar.DAY_OF_MONTH, 10);

                    arrDataPagamentos[i] = formato.format(c.getTime());
                } else {
                    c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
                    c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
                    c.set(Calendar.DAY_OF_MONTH, 10);

                    arrDataPagamentos[i] = formato.format(c.getTime());
                }
            }
        }
        return arrDataPagamentos;
    }
}
