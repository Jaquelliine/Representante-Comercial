/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representante.comercial.model;

/**
 *
 * @author L
 */
public class Cliente {

    private int pk_cliente;
    private String nome;
    private String cpf;
    
    private Endereco endereco; //representa a associacao


    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente(int pk_cliente, String nome, String cpf, Endereco endereco) {
        this.pk_cliente = pk_cliente;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public int getPk_cliente() {
        return pk_cliente;
    }

    public void setPk_cliente(int pk_cliente) {
        this.pk_cliente = pk_cliente;
        this.endereco.setFk_cliente(pk_cliente);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length()<=80){
            this.nome = nome;
        } else {
            throw new RuntimeException("Tamanho máximo do nome é de 80 caracteres");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco + '}';
    }
}

