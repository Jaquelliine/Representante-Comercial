/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representante.comercial.dao;

import representante.comercial.model.Endereco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author L
 */
public class EnderecoDAO {

    public static int create(Endereco e) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();
            //INSERT INTO clientes_enderecos(
            //pk_enderenco, fk_cliente, logradouro, bairro, cidade, estado, 
            //pais, cep)VALUES (?, ?, ?, ?, ?, ?, ?, ?);


            String sql =
                    "insert into clientes_enderecos (fk_cliente, logradouro, bairro, cidade, estado, pais, cep) values (" +
                            e.getFk_cliente() + ",'" +
                            e.getLogradouro() + "','" +
                            e.getBairro() + "','" +
                            e.getCidade() + "','" +
                            e.getEstado() + "','" +
                            e.getPais() + "','" +
                            e.getCep() + "')";
            System.out.println(sql);
            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            e.setPk_endereco(key);

            return key;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static Endereco retreave(int pkEndereco) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();
            String sql = "Select * from clientes_enderecos where pk_enderenco =" + pkEndereco;
            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            return new Endereco(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_enderenco"),
                    rs.getInt("fk_cliente"));
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Endereco retreaveByCliente(int fkCliente) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();
            String sql = "Select * from clientes_enderecos where fk_cliente =" + fkCliente;
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {

                return new Endereco(
                        rs.getString("logradouro"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getInt("pk_enderenco"),
                        rs.getInt("fk_cliente"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Endereco> retreaveAll() {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();
            String sql = "SELECT * FROM clientes_enderecos";
            ResultSet rs = stm.executeQuery(sql);

            ArrayList<Endereco> e = new ArrayList<>();

            while (rs.next()) {
                e.add(new Endereco(
                        rs.getString("logradouro"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getInt("pk_enderenco"),
                        rs.getInt("fk_cliente")));
            }


            return e;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static boolean update(Endereco e) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();
            String sql =
                    "update clientes_enderecos set " +
                            "logradouro = '" + e.getLogradouro() + "'," +
                            "bairro = '" + e.getBairro() + "'," +
                            "estado = '" + e.getEstado() + "'," +
                            "pais = '" + e.getPais() + "'," +
                            "cep = '" + e.getCep() + "', " +
                            "cidade ='" + e.getCidade() + "' where fk_cliente = " + e.getFk_cliente();
            System.out.println(sql);
            stm.execute(sql);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean delete(Endereco e) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();
            String sql = "delete from clientes_endereco where fk_cliente = " + e.getFk_cliente();
            stm.execute(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
