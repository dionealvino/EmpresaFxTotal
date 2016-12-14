package empresafxtotal.controller;

import empresafxtotal.controller.classes.Cliente;
import empresafxtotal.controller.classes.Funcionario;
import empresafxtotal.controller.classes.Produto;
import empresafxtotal.controller.classes.Venda;
import empresafxtotal.controller.classes.VendaItem;
import empresafxtotal.model.ClienteDAO;
import empresafxtotal.model.FuncionarioDAO;
import empresafxtotal.model.ProdutoDAO;
import empresafxtotal.model.VendaDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Barbara, Dione
 */
public class FXMLMantemVendaController implements Initializable {
    
//    private Funcionario f;
//    private Cliente c;
//    private int pkFuncionario;
//    private int pkCliente;
    
    Venda v;
    
    @FXML
    private TableView tabelaVendas;
    @FXML
    private TextField textFieldValor;
    @FXML
    private TextField textFieldQtd;
    @FXML
    private ComboBox<Cliente> comboboxClientes;
    @FXML
    private ComboBox<Funcionario> comboboxVendedor;
    @FXML
    private ComboBox<Produto> comboboxProduto;
    @FXML
    private TableColumn<VendaItem, Double> columnVrUnitario;
    @FXML
    private TableColumn<Venda,Double> columnVrTotal;
    @FXML
    private TableColumn<VendaItem, String> columnProduto;
    @FXML
    private TableColumn<VendaItem, Integer> columnqtd;

    final ObservableList<VendaItem> obsList = FXCollections.observableArrayList();
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        columnProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnqtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        columnVrUnitario.setCellValueFactory(new PropertyValueFactory<>("vrUnitario"));
        columnVrTotal.setCellValueFactory(new PropertyValueFactory<>("vrTotal"));
        
        tabelaVendas.setItems(null);
        
        tabelaVendas.setItems(obsList);
        
        List<Cliente> c = ClienteDAO.retreaveAll();
        comboboxClientes.getItems().addAll(c);
        
        List<Funcionario> f = FuncionarioDAO.retreaveVendedor();
        comboboxVendedor.getItems().addAll(f);
        
        List<Produto> p = ProdutoDAO.retreaveAll();
        comboboxProduto.getItems().addAll(p);
        

    }

    public void gravar(){
       VendaItem vdItem = new VendaItem(Integer.parseInt(textFieldQtd.getText()), Float.parseFloat(textFieldValor.getText()), comboboxProduto.getValue());
       obsList.add(vdItem);
        
    }
    
    public void limpaTela() {

    }


    public void salvar() throws SQLException {
                ArrayList<VendaItem> vi = new ArrayList<>(obsList);
                v.setCliente(comboboxClientes.getValue());
                v.setData(new Date());
                //v.setNumero(v.getNumerodao());
                v.setItens(vi);
                v.setVendedor(comboboxVendedor.getValue());
                VendaDAO.create(v);
        
        

    }
    
    public void load(){
    }
    
}
