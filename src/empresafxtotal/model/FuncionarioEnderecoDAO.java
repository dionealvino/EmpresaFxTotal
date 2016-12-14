package empresafxtotal.model;

import empresafxtotal.controller.classes.FuncionarioEndereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Barbara, Dione
 */
public class FuncionarioEnderecoDAO {

    public static int create(FuncionarioEndereco funcEnd) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "insert into funcionarios_enderecos (fk_funcionario, logradouro, bairro, cidade, estado, pais, cep) values('"
                + funcEnd.getFk_funcionario() + "','"
                + funcEnd.getLogradouro() + "','"
                + funcEnd.getBairro() + "','"
                + funcEnd.getCidade() + "','"
                + funcEnd.getEstado() + "','"
                + funcEnd.getPais() + "','"
                + funcEnd.getCep() + "')";

        stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int key = rs.getInt(1);
        funcEnd.setPk_endereco(key);

        return key;
    }

    public static FuncionarioEndereco retreave(int pkFuncionario) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "Select * from funcionarios_enderecos  where pk_endereco =" + pkFuncionario;
        ResultSet rs = stm.executeQuery(sql);

        if (rs.next()) {
            return new FuncionarioEndereco(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_funcionario"));
        }
        return null;
    }

    public static FuncionarioEndereco retreaveByFuncionario(int fkFuncionario) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "Select * from funcionarios_enderecos  where fk_funcionario = " + fkFuncionario;
        ResultSet rs = stm.executeQuery(sql);
        if (rs.next()) {
            return new FuncionarioEndereco(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_funcionario"));
        }
        return null;
    }

    public static ArrayList<FuncionarioEndereco> retreaveAll() throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "Select * from funcionarios_enderecos";
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<FuncionarioEndereco> e = new ArrayList<>();
        while (rs.next()) {
            e.add(new FuncionarioEndereco(
                    rs.getString("logradouro"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais"),
                    rs.getString("cep"),
                    rs.getInt("pk_endereco"),
                    rs.getInt("fk_funcionario")));
        }

        return e;
    }

    public static void update(FuncionarioEndereco fe) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "update  funcionarios_enderecos set "
                + "logradouro = '" + fe.getLogradouro()
                + "', bairro = '" + fe.getBairro()
                + "', cidade = '" + fe.getCidade()
                + "', estado = '" + fe.getEstado()
                + "', pais = '" + fe.getPais()
                + "', cep = '" + fe.getCep()
                + "' where pk_endereco=" + fe.getPk_endereco();
        stm.execute(sql);
    }

    public static void delete(FuncionarioEndereco fe) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "delete from funcionarios_enderecos where pk_endereco=" + fe.getPk_endereco();
        stm.execute(sql);
    }

}
