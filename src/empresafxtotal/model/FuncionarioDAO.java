package empresafxtotal.model;

import empresafxtotal.controller.classes.Cargo;

import empresafxtotal.controller.classes.Funcionario;
import empresafxtotal.controller.classes.FuncionarioEndereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Barbara, Dione
 */
public class FuncionarioDAO {

    public static int create(Funcionario func) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "insert into funcionarios (nome, cpf, fk_cargo) values('"
                + func.getNome() + "','"
                + func.getCpf() + "','"
                + func.getFk_cargo() + "')";

        stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int key = rs.getInt(1);
        func.setPk_funcionario(key);

        FuncionarioEnderecoDAO.create(func.getFuncEndereco());

        return key;
    }

    public static Funcionario retreave(int pk_funcionario) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "Select * from funcionarios where pk_funcionario=" + pk_funcionario;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();

        Cargo c = CargoDAO.retreave(rs.getInt("fk_cargo"));
        FuncionarioEndereco fe = FuncionarioEnderecoDAO.retreaveByFuncionario(pk_funcionario);

        return new Funcionario(
                rs.getInt("pk_funcionario"),
                c, rs.getString("nome"),
                rs.getString("cpf"),
                fe);
    }


    public static ArrayList<Funcionario> retreaveByCargo(int fk_cargo) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "Select * from funcionarios where fk_cargo = " + fk_cargo;
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        while (rs.next()) {
            Cargo c = CargoDAO.retreave(rs.getInt("fk_cargo"));
            FuncionarioEndereco e = FuncionarioEnderecoDAO.retreaveByFuncionario(rs.getInt("pk_funcionario"));
            funcionarios.add(new Funcionario(
                    rs.getInt("pk_funcionario"),
                    c, rs.getString("nome"),
                    rs.getString("cpf"),
                    e));
        }
        return funcionarios;
    }

    
    public static ArrayList<Funcionario> retreaveAll() throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "Select * from funcionarios";
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        while (rs.next()) {
            Cargo c = CargoDAO.retreave(rs.getInt("fk_cargo"));
            FuncionarioEndereco e = FuncionarioEnderecoDAO.retreaveByFuncionario(rs.getInt("pk_funcionario"));
            funcionarios.add(new Funcionario(
                    rs.getInt("pk_funcionario"),
                    c, rs.getString("nome"),
                    rs.getString("cpf"),
                    e));
        }
        return funcionarios;
    }

    public static void update(Funcionario func) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "update  funcionarios set "
                + "nome = '" + func.getNome()
                + "', cpf = '" + func.getCpf()
                + "' where pk_funcionario = " + func.getPk_funcionario();
        FuncionarioEnderecoDAO.update(func.getFuncEndereco());
        stm.execute(sql);
    }

    public static void delete(Funcionario func) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "delete from funcionarios where pk_funcionario=" + func.getPk_funcionario();
        stm.execute(sql);
    }

}
