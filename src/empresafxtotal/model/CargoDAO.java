package empresafxtotal.model;

import empresafxtotal.controller.classes.Cargo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Barbara
 */
public class CargoDAO {

    private CargoDAO() {

    }

    public static int create(Cargo cargo) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "insert into cargos (nome, descricao) values('"
                + cargo.getNome() + "','"
                + cargo.getDescricao() + "')";
        stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int key = rs.getInt(1);
        cargo.setPk_cargo(key);

        return key;
    }

    public static Cargo retreave(int pk_cargo) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "Select * from cargos where pk_cargo = "
                + pk_cargo;
        ResultSet rs = stm.executeQuery(sql);

        rs.next();
        return new Cargo(
                rs.getInt("pk_cargo"),
                rs.getString("nome"),
                rs.getString("descricao"));

    }

    public static ArrayList<Cargo> retreaveAll() throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "Select * from cargos";
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Cargo> cargos = new ArrayList<>();

        while (rs.next()) {
            cargos.add(new Cargo(
                    rs.getInt("pk_cargo"),
                    rs.getString("nome"),
                    rs.getString("descricao")));
        }
        return cargos;
    }

    public static void update(Cargo c) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "update cargos set "
                + "nome = '" + c.getNome()
                + "', descricao = '"
                + c.getDescricao()
                + "'where pk_cargo = " + c.getPk_cargo();
        stm.execute(sql);
    }

    public static void delete(Cargo cargo) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "delete from cargos where pk_cargo = "
                + cargo.getPk_cargo();
        stm.execute(sql);
    }

}
