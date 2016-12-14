package empresafxtotal.model;

import empresafxtotal.controller.classes.Cliente;
import empresafxtotal.controller.classes.Funcionario;
import empresafxtotal.controller.classes.Venda;
import empresafxtotal.controller.classes.VendaItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Bárbara, Dione
 */
public class VendaDAO {

    private VendaDAO() {

    }

    public static int create(Venda venda) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql
                = "insert into vendas (numero, datas, fk_cliente, fk_vendedor) values('"
                + venda.getNumero() + "','"
                + venda.getData() + "')"
                + venda.getCliente().getPk_cliente() + "','"
                + venda.getVendedor().getPk_funcionario() + "')";

        stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int key = rs.getInt(1);
        venda.setPkVenda(key);

        for (VendaItem vendaItem : venda.getItens()) {
            vendaItem.setFkVenda(key);
            VendaItemDAO.create(vendaItem);
        }

        return key;
    }

    public static Venda retreave(int pk_venda) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();

        String sql = "Select * from vendas where pk_venda=" + pk_venda;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        Cliente cliente = ClienteDAO.retreave(rs.getInt("fk_cliente"));
        Funcionario vendedor = FuncionarioDAO.retreave(rs.getInt("fk_vendedor"));
        ArrayList<VendaItem> todosOsItens = VendaItemDAO.retreaveByVenda(pk_venda);
        return new Venda(
                rs.getInt("numero"),
                rs.getDate("datas"),
                cliente,
                vendedor,
                todosOsItens,
                pk_venda);
    }

    public static ArrayList<Venda> retreaveAll() throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "Select * from Vendas";
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Venda> vendas = new ArrayList<>();
        while (rs.next()) {
            Cliente cliente = ClienteDAO.retreave(rs.getInt("fk_cliente"));
            Funcionario vendedor = FuncionarioDAO.retreave(rs.getInt("fk_vendedor"));
            ArrayList<VendaItem> todosOsItens = VendaItemDAO.retreaveByVenda(rs.getInt("pk_venda"));
            vendas.add(new Venda(
                    rs.getInt("numero"),
                    rs.getDate("datas"),
                    cliente,
                    vendedor,
                    todosOsItens,
                    rs.getInt("pk_venda")));
        }
        rs.next();
        return vendas;
    }

    public static void update(Venda venda) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "update  vendas set "
                + "numero = '" + venda.getNumero()
                + "', datas = '" + venda.getData()
                + "'where pk_venda=" + venda.getPkVenda();

        stm.execute(sql);

        for (VendaItem vendaItem : venda.getItens()) {
            if (vendaItem.getPkVendaItem() != 0) {
                VendaItemDAO.update(vendaItem);
            } else {
                vendaItem.setFkVenda(venda.getPkVenda());
                VendaItemDAO.create(vendaItem);
            }
        }
    }

    public static void delete(Venda venda) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "delete from vendas where pk_venda=" + venda.getPkVenda();
        stm.execute(sql);
    }

}
