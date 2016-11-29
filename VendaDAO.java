package representante.comercial.dao;


import representante.comercial.model.Venda;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marcio on 27/11/2016.
 */
public class VendaDAO {

    public static int create(Venda v) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();


            String sql = "insert into venda (nome_cliente, data_faturamento, valor_total) values ('" +
                    v.getNomeCliente() + "','" +
                    v.getDataFaturamento() + "'," +
                    v.getValorTotal() + ")";

            System.out.println(sql);
            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            v.setIdVenda(key);
            return key;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static ArrayList<Venda> retreave() {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "SELECT * FROM venda";
            ResultSet rs = stm.executeQuery(sql);


            ArrayList<Venda> v = new ArrayList<>();

            while (rs.next()) {
                v.add(new Venda(rs.getInt("idvenda"),
                        rs.getString("nome_cliente"),
                        rs.getString("data_faturamento"),
                        rs.getDouble("valor_total")));
            }


            return v;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<Venda> retreave(String condicao) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "SELECT * FROM venda WHERE " + condicao;
            ResultSet rs = stm.executeQuery(sql);


            ArrayList<Venda> v = new ArrayList<>();

            while (rs.next()) {
                v.add(new Venda(rs.getInt("idvenda"),
                        rs.getString("nome_cliente"),
                        rs.getString("data_faturamento"),
                        rs.getDouble("valor_total")));
            }


            return v;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<Venda> somaValores(String condicao) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "SELECT sum(valor_total) as soma_valores FROM venda WHERE " + condicao;
            ResultSet rs = stm.executeQuery(sql);


            ArrayList<Venda> v = new ArrayList<>();

            while (rs.next()) {
                v.add(new Venda(rs.getDouble("soma_valores")));
            }


            return v;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }


}
