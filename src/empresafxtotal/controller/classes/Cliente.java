package empresafxtotal.controller.classes;

import empresafxtotal.model.ClienteDAO;
import java.sql.SQLException;

/**
 *
 * @author Bárbara, Dione
 */
public class Cliente {

    private int pk_cliente;
    private String nome;
    private String cpf;
    private Endereco endereco; // Representa a relação 1 para 1 entre Cliente e Endereco.

    public Cliente(int pk_cliente, String nome, String cpf, Endereco endereco) {
        this.pk_cliente = pk_cliente;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public Cliente(String nome, String cpf, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public Cliente() {

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
        if (nome.length() <= 80) {
            this.nome = nome;
        } else {
            throw new RuntimeException("Tamanho máximo do nome é de 80 caracteres.");
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
        return nome;
    }

    public void save() throws SQLException {
        ClienteDAO.create(this);
    }

    public void update() throws SQLException {
        ClienteDAO.update(this);
    }

}
