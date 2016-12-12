package empresafxtotal.model;

<<<<<<< Updated upstream
import empresafxtotal.controller.classes.Venda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

=======
>>>>>>> Stashed changes
/**
 *
 * @author Bárbara
 */
public class VendaDAO {
    
<<<<<<< Updated upstream
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
            int key = rs.getInt(1); //retorna o id gravado no banco
            venda.setPkVenda(key);//guardamos o id salvo no banco na variavel setPk_Venda.
            System.out.println(key);
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

    public static Venda retreaveByVendaEnde(int fk_Venda) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();

            String sql = "Select * from Vendas where pk_Venda=" + fk_Venda;

            ResultSet rs = stm.executeQuery(sql);
            rs.next();

            Endereco e = EnderecoDAO.retreaveByVenda(fk_Venda);
            return new Venda(rs.getInt("pk_Venda"), rs.getString("nome"), rs.getString("cpf"), e);

        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void delete(Venda c) {

        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "delete from Vendas where pk_Venda=" + c.getPk_Venda();
            System.out.println(sql);
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  
    public static void update(Venda c) {
        try {
            Statement stm
                    = BancoDados.createConnection().
                    createStatement();
            String sql = "update  Vendas set " + "nome='" + c.getNome() + "',cpf='" + c.getCpf() + "'where pk_Venda=" + c.getPk_Venda();
            EnderecoDAO.update(c.getEndereco());
            System.out.println(sql);
            stm.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

=======
>>>>>>> Stashed changes
}
