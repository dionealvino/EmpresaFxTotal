package empresafxtotal.model;

import empresafxtotal.controller.classes.FornecedorEndereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Barbara
 */
public class FornecedorEnderecoDAO {

    public static int create(FornecedorEndereco fornecedorEnd) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "insert into fornecedores_enderecos (fk_fornecedor, logradouro, bairro, cidade, estado, pais, cep) values('"
                + fornecedorEnd.getFk_fornecedor() + "','"
                + fornecedorEnd.getLogradouro() + "','"
                + fornecedorEnd.getBairro() + "','"
                + fornecedorEnd.getCidade() + "','"
                + fornecedorEnd.getEstado() + "','"
                + fornecedorEnd.getPais() + "','"
                + fornecedorEnd.getCep() + "')";
        stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int key = rs.getInt(1);
        fornecedorEnd.setPk_endereco(key);

        return key;
    }

    public static FornecedorEndereco retreave(int pkEndereco) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "Select * from fornecedores_enderecos where pk_endereco = " + pkEndereco;
        ResultSet rs = stm.executeQuery(sql);
        if (rs.next()) {
            return new FornecedorEndereco(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_fornecedor"));
        }
        return null;
    }

    public static FornecedorEndereco retreaveByFornecedor(int fkEndereco) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "Select * from fornecedores_enderecos  where fk_fornecedor = " + fkEndereco;
        ResultSet rs = stm.executeQuery(sql);
        if (rs.next()) {
            return new FornecedorEndereco(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_fornecedor"));
        }
        return null;
    }

    public static ArrayList<FornecedorEndereco> retreaveAll() throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "Select * from fornecedores_enderecos";
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<FornecedorEndereco> fe = new ArrayList<>();
        while (rs.next()) {
            fe.add(new FornecedorEndereco(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_fornecedor")));
        }
        return fe;
    }

    public static void update(FornecedorEndereco fe) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "update  fornecedores_enderecos set "
                + " logradouro='" + fe.getLogradouro()
                + "', bairro='" + fe.getBairro()
                + "', cidade='" + fe.getCidade()
                + "', estado='" + fe.getEstado()
                + "', pais='" + fe.getPais()
                + "', cep='" + fe.getCep()
                + "' where pk_endereco = " + fe.getPk_endereco();
        stm.execute(sql);
    }

    public static void delete(FornecedorEndereco fe) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "delete from fornecedores_enderecos where pk_endereco=" + fe.getPk_endereco();
        stm.execute(sql);
    }

}
