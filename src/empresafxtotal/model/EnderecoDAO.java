package empresafxtotal.model;

import empresafxtotal.controller.classes.Endereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Barbara, Dione
 */
public class EnderecoDAO {

    public static int create(Endereco endereco) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "insert into clientes_enderecos (fk_cliente, logradouro, bairro, cidade, estado, pais, cep) values('"
                + endereco.getFk_cliente() + "','"
                + endereco.getLogradouro() + "','"
                + endereco.getBairro() + "','"
                + endereco.getCidade() + "','"
                + endereco.getEstado() + "','"
                + endereco.getPais() + "','"
                + endereco.getCep() + "')";

        stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int key = rs.getInt(1);
        endereco.setPk_endereco(key);

        return key;
    }

    public static Endereco retreave(int pkEndereco) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "Select * from clientes_enderecos where pk_endereco = "
                + pkEndereco;
        ResultSet rs = stm.executeQuery(sql);
        if (rs.next()) {
            return new Endereco(
                rs.getString("logradouro"),
                rs.getString("bairro"),
                rs.getString("cidade"),
                rs.getString("estado"),
                rs.getString("pais"),
                rs.getString("cep"),
                rs.getInt("pk_endereco"),
                rs.getInt("fk_cliente"));
            }
        return null;
}

    public static Endereco retreaveByCliente(int fkCliente) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
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
                rs.getInt("pk_endereco"),
                rs.getInt("fk_cliente"));
            }
        return null;
}

    public static ArrayList<Endereco> retreaveAll() throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "Select * from clientes_enderecos";
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
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_cliente")));
        }
        return e;
    }

    public static void update(Endereco endereco) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "update clientes_enderecos set "
                + "logradouro = '" + endereco.getLogradouro()
                + "', bairro = '" + endereco.getBairro()
                + "', cidade = '" + endereco.getCidade()
                + "', estado = '" + endereco.getEstado()
                + "', pais = '" + endereco.getPais()
                + "', cep = '" + endereco.getCep()
                + "' where pk_endereco = " + endereco.getPk_endereco();
        stm.execute(sql);
    }

    public static void delete(Endereco endereco) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "delete from clientes_enderecos where pk_endereco=" + endereco.getPk_endereco();
        stm.execute(sql);
    }

}
