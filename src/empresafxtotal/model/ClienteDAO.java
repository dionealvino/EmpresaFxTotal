package empresafxtotal.model;

import empresafxtotal.controller.classes.Cliente;
import empresafxtotal.controller.classes.Endereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Barbara
 */
public class ClienteDAO {

    private ClienteDAO() {

    }

    public static int create(Cliente cliente) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "insert into clientes (nome, cpf) values('"
                + cliente.getNome() + "','"
                + cliente.getCpf() + "')";

        stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int key = rs.getInt(1);
        cliente.setPk_cliente(key);

        EnderecoDAO.create(cliente.getEndereco());

        return key;

    }

    public static Cliente retreave(int pk_cliente) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "Select * from clientes where pk_cliente = "
                + pk_cliente;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        Endereco e = EnderecoDAO.retreaveByCliente(pk_cliente);
        return new Cliente(
                rs.getInt("pk_cliente"),
                rs.getString("nome"),
                rs.getString("cpf"),
                e);

    }

    public static ArrayList<Cliente> retreaveAll() throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "Select * from clientes";
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Cliente> clientes = new ArrayList<>();
        while (rs.next()){
            Endereco e = EnderecoDAO.retreaveByCliente(rs.getInt("pk_cliente")); 
            clientes.add(new Cliente(
                    rs.getInt("pk_cliente"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    e));
        }
        return clientes;
    }

    public static void delete(Cliente cliente) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "delete from clientes where pk_cliente = "
                + cliente.getPk_cliente();
        stm.execute(sql);
    }

    public static void update(Cliente cliente) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "update  clientes set "
                + "nome='" + cliente.getNome()
                + "',cpf='" + cliente.getCpf()
                + "'where pk_Cliente = " + cliente.getPk_cliente();
        EnderecoDAO.update(cliente.getEndereco());
        stm.execute(sql);

    }

}
