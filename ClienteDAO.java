/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representante.comercial.dao;

import representante.comercial.model.Cliente;
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
public class ClienteDAO {

    private ClienteDAO() {
    }


    public static int create(Cliente c) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();

            String sql =
                    "insert into clientes (nome, cpf) values ('" +
                            c.getNome() + "','" +
                            c.getCpf() + "')";

            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            c.setPk_cliente(key);

            EnderecoDAO.create(c.getEndereco());

            return key;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static Cliente retreave(int pk_cliente) {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "select * from clientes where pk_cliente =" + pk_cliente;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            Endereco e = EnderecoDAO.retreaveByCliente(pk_cliente);
            return new Cliente(pk_cliente,
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    e);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    public static ArrayList<Cliente> retreaveAll() {
        try {
            Statement stm =
                    BancoDados.createConnection().
                            createStatement();

            String sql = "SELECT * FROM clientes";

            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Cliente> cs = new ArrayList<>();
            while (rs.next()) {
                Endereco e = EnderecoDAO.retreaveByCliente(rs.getInt("pk_cliente"));
                cs.add(new Cliente(
                        rs.getInt("pk_cliente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        e));
            }

            return cs;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

    public static boolean update(Cliente c) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();

            String sql =
                    "update clientes set " +
                            "nome='" + c.getNome() + "'," +
                            " cpf='" + c.getCpf() + "' " +
                            "where pk_cliente = " + c.getPk_cliente();
            stm.execute(sql);
            if (c.getEndereco().getFk_cliente() != 0) {
                EnderecoDAO.create(c.getEndereco());
            } else {
                EnderecoDAO.update(c.getEndereco());
            }

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean delete(Cliente c) {
        try {
            Statement stm = BancoDados.createConnection().createStatement();

            String sql =
                    "delete from clientes "+
                            " where pk_cliente = " + c.getPk_cliente();
            stm.execute(sql);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            if (c.getEndereco().getFk_cliente() != 0) {
                EnderecoDAO.create(c.getEndereco());
            } else {
                EnderecoDAO.update(c.getEndereco());
            }

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
