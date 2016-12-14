package empresafxtotal.model;

import empresafxtotal.controller.classes.Produto;
import empresafxtotal.controller.classes.VendaItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Bárbara
 */
public class VendaItemDAO {

    private VendaItemDAO() {

    }

    public static int create(VendaItem vendaItem) throws SQLException {
        Statement stm = 
                BancoDados.createConnection().
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
        //EnderecoDAO.create(c.getEndereco()); //Aqui chamamos a DAO do enderenco e passamos os valores para ela gravar pelo getEnderenco.

        return key;

    }

    public static VendaItem retreave(int pk_item) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "Select * from vendas_itens where pk_item=" + pk_item;
        ResultSet rs = stm.executeQuery(sql);
        rs.next();
        Produto produto = ProdutoDAO.retreave(rs.getInt("pk_item"));
        return new VendaItem(
                rs.getInt("qtd"),
                rs.getDouble("nome"),
                produto,
                rs.getInt("cpf"),
                rs.getInt("pk_item"));

    }

    public static ArrayList<VendaItem> retreaveAll() throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "Select * from vendas_itens";
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<VendaItem> cs = new ArrayList<>();
        while (rs.next())
        {
            //Endereco e = EnderecoDAO.retreaveByCliente(rs.getInt("pk_cliente")); //Como já temos o retrave no
            cs.add(new VendaItem(
                    rs.getInt("pk_item"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    e));
        }

        return cs;
    }

    public static VendaItem retreaveByClienteEnde(int fk_cliente) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();

        String sql = "Select * from vendas_itens where pk_cliente=" + fk_cliente;

        ResultSet rs = stm.executeQuery(sql);
        rs.next();

        Endereco e = EnderecoDAO.retreaveByCliente(fk_cliente);
        return new VendaItem(
                rs.getInt("pk_cliente"),
                rs.getString("nome"),
                rs.getString("cpf"),
                e);

    }

    public static void delete(VendaItem c) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "delete from vendas_itens where pk_cliente=" + c.getPk_cliente();
        System.out.println(sql);
        stm.execute(sql);

    }

    public static void update(VendaItem c) throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "update  vendas_itens set "
                + "nome='" + c.getNome()
                + "',cpf='" + c.getCpf()
                + "'where pk_Cliente=" + c.getPk_cliente();
        //EnderecoDAO.update(c.getEndereco());
        System.out.println(sql);
        stm.execute(sql);
    }

}
