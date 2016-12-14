package empresafxtotal.model;

import empresafxtotal.controller.classes.Produto;
import empresafxtotal.controller.classes.VendaItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Bárbara, Dione
 */
public class VendaItemDAO {

    private VendaItemDAO() {

    }

    public static int create(VendaItem vendaItem) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "insert into vendas_itens (qtd, valor_unitario, fk_produto, fk_venda) values('"
                + vendaItem.getQtd() + "','"
                + vendaItem.getValorUnitario() + "','"
                + vendaItem.getProduto() + "')";

        stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int key = rs.getInt(1);
        vendaItem.setPkVendaItem(key);

        return key;
    }

    public static VendaItem retreave(int pk_item) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "Select * from vendas_itens where pk_item = " + pk_item;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        Produto produto = ProdutoDAO.retreave(rs.getInt("fk_produto"));
        return new VendaItem(
                rs.getInt("qtd"),
                rs.getDouble("valor_unitario"),
                produto,
                rs.getInt("fk_venda"),
                rs.getInt("pk_item"));
    }

    public static ArrayList<VendaItem> retreaveByVenda(int fk_venda) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "SELECT * FROM vendas_itens where fk_venda = "
                + fk_venda;
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<VendaItem> vendaItem = new ArrayList<>();
        if (rs.next()) {
            Produto produto = ProdutoDAO.retreave(rs.getInt("fk_produto"));
            vendaItem.add(new VendaItem(
                    rs.getInt("qtd"),
                    rs.getDouble("valor_unitario"),
                    produto,
                    rs.getInt("fk_venda"),
                    rs.getInt("pk_item")));
        }
        return vendaItem;
    }

    public static ArrayList<VendaItem> retreaveAll() throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "Select * from vendas_itens";
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<VendaItem> vendaItem = new ArrayList<>();
        while (rs.next()) {
            Produto produto = ProdutoDAO.retreave(rs.getInt("fk_produto"));
            vendaItem.add(new VendaItem(
                    rs.getInt("qtd"),
                    rs.getDouble("valor_unitario"),
                    produto,
                    rs.getInt("fk_venda"),
                    rs.getInt("pk_item")));
        }
        return vendaItem;
    }

    public static void update(VendaItem vendaItem) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "update vendas_itens set "
                + " qtd = '" + vendaItem.getQtd()
                + "', valor_unitario = '" + vendaItem.getValorUnitario()
                + "' where pk_item = " + vendaItem.getPkVendaItem();
        stm.execute(sql);
    }

    public static void delete(VendaItem vendaItem) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "delete from vendas_itens where pk_item = "
                + vendaItem.getPkVendaItem();
        stm.execute(sql);
    }

}
