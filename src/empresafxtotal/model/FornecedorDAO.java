package empresafxtotal.model;

import empresafxtotal.controller.classes.Fornecedor;
import empresafxtotal.controller.classes.FornecedorEndereco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Barbara
 */
public class FornecedorDAO {

    private FornecedorDAO() {

    }

    public static int create(Fornecedor fornecedor) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "insert into fornecedores (nome, cpf) values('"
                + fornecedor.getNome() + "','"
                + fornecedor.getCpf() + "')";

        stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int key = rs.getInt(1);
        fornecedor.setPk_fornecedor(key);
        FornecedorEnderecoDAO.create(fornecedor.getForneEnd());

        return key;
    }

    public static Fornecedor retreave(int pk_fornecedor) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "Select * from fornecedores where pk_fornecedor=" + pk_fornecedor;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        FornecedorEndereco e = FornecedorEnderecoDAO.retreaveByFornecedor(pk_fornecedor);
        return new Fornecedor(
                rs.getInt("pk_fornecedor"),
                rs.getString("nome"),
                rs.getString("cpf"), e);
    }

    public static ArrayList<Fornecedor> retreaveAll() throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "Select * from fornecedores";
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        while (rs.next()) {
            FornecedorEndereco e = FornecedorEnderecoDAO.retreaveByFornecedor(rs.getInt("pk_fornecedor"));
            fornecedores.add(new Fornecedor(
                    rs.getInt("pk_fornecedor"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    e));
        }
        return fornecedores;
    }

    public static void update(Fornecedor f) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "update  fornecedores set "
                + " nome = '" + f.getNome()
                + "', cpf = '" + f.getCpf()
                + "' where pk_fornecedor = " + f.getPk_fornecedor();
        FornecedorEnderecoDAO.update(f.getForneEnd());
        stm.execute(sql);
    }

    public static void delete(Fornecedor fornecedore) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "delete from fornecedores where pk_fornecedor=" + fornecedore.getPk_fornecedor();
        stm.execute(sql);
    }

}
