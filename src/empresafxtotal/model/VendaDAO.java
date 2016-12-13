package empresafxtotal.model;

import empresafxtotal.controller.classes.Venda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author Bárbara
 */
public class VendaDAO {
    
    private VendaDAO() {

    }

    public static int create(Venda venda) throws SQLException {
            Statement stm = BancoDados.createConnection().createStatement();
            String sql = 
                    "insert into Vendas (nome,cpf) values('" 
                    + venda.getNumero()+ "','" 
                    + venda.getData() + "')";

            stm.execute(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1); 
            venda.setPkVenda(key);
            //EnderecoDAO.create(venda.getEndereco()); //Aqui chamamos a DAO do enderenco e passamos os valores para ela gravar pelo getEnderenco.
           return key;
    }

    public static Venda retreave(int pk_venda) throws SQLException {
            Statement stm
                    = BancoDados.createConnection().
                            createStatement();

            String sql = "Select * from Vendas where pk_Venda=" + pk_venda;
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            //Endereco e = EnderecoDAO.retreaveByVenda(pk_venda);
            return new Venda(
                    rs.getInt("pk_Venda"),
                    rs.getString("nome"),
                    rs.getString("cpf"), e);

    }

    public static ArrayList<Venda> retreaveAll() throws SQLException {
        Statement stm
                = BancoDados.createConnection().
                        createStatement();
        String sql = "Select * from Vendas";
        ResultSet rs = stm.executeQuery(sql);
        ArrayList<Venda> vendas = new ArrayList<>();
        while (rs.next())//vamos fazer uma condição para que o next vai andando na tabela ate o final
        {
            //Endereco e = EnderecoDAO.retreaveByVenda(rs.getInt("pk_Venda")); //Como já temos o retrave no
            //Endereco fazemo a consulta em Venda e pegamos a chave com o rs.getInt
            //Na parte de baixo vamos add a consulta na lista
            vendas.add(new Venda(
                    rs.getInt("pk_Venda"),
                    rs.getString("nome"),
                    rs.getString("cpf")));
        }

        return vendas;
    }

    public static void delete(Venda venda) throws SQLException {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "delete from Vendas where pk_Venda=" + venda.getPkVenda();
            System.out.println(sql);
            stm.execute(sql);
    }

  
    public static void update(Venda venda) throws SQLException {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "update  Vendas set " 
                    + "nome='" + venda.getNome() 
                    + "',cpf='" + venda.getCpf() 
                    + "'where pk_Venda=" + venda.getPkVenda();
            EnderecoDAO.update(venda.getEndereco());
            System.out.println(sql);
            stm.execute(sql);

    }

}
