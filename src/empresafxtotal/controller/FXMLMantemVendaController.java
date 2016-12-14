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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private Funcionario f;
    private Cliente c;
    private int pkFuncionario;
    private int pkCliente;
    
    Venda v;
    VendaItem vdItem;
    
    @FXML
    private TableView tabelaVendas;
    @FXML
    private TextField textFieldValor;
    @FXML
    private TextField textFieldQtd;
    @FXML
    private TextField textFieldValorTotal;
    @FXML
    private ComboBox<Cliente> comboboxClientes;
    @FXML
    private ComboBox<Funcionario> comboboxVendedor;
    @FXML
    private ComboBox<Produto> comboboxProduto;
    @FXML
    private TableColumn<VendaItem, Double> columnVrUnitario;
//    @FXML
//    private TableColumn<Venda,Double> columnVrTotal;
    @FXML
    private TableColumn<VendaItem, String> columnProduto;
    @FXML
    private TableColumn<VendaItem, Integer> columnqtd;

    final ObservableList<VendaItem> obsList = FXCollections.observableArrayList();
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            columnProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
            columnqtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
            columnVrUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
          // columnVrTotal.setCellValueFactory(new PropertyValueFactory<>("vrTotal"));
            
            tabelaVendas.setItems(null);
            
            tabelaVendas.setItems(obsList);
            
            List<Cliente> clientes = ClienteDAO.retreaveAll();
            comboboxClientes.getItems().addAll(clientes);
            

            List<Funcionario> f = FuncionarioDAO.retreaveByCargo(8);
            comboboxVendedor.getItems().addAll(f);
            
            List<Produto> produtoLista = ProdutoDAO.retreaveAll();
            comboboxProduto.getItems().addAll(produtoLista);
            

        } catch (SQLException ex) {
            Logger.getLogger(FXMLMantemVendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    public void adicionar(){
       vdItem = new VendaItem(Integer.parseInt(textFieldQtd.getText()), Float.parseFloat(textFieldValor.getText()), comboboxProduto.getValue());
       obsList.add(vdItem);
       
       calcularTotal();
        
    }
    
    public void limpaTela() {
        textFieldQtd.clear();
        textFieldValor.clear();
        comboboxClientes.getSelectionModel().clearSelection();
        comboboxProduto.getSelectionModel().clearSelection();
        comboboxVendedor.getSelectionModel().clearSelection();
        obsList.clear();
        textFieldValorTotal.clear();

    }


    public void salvar() throws SQLException, Exception {
        v = new Venda();
        
        Date data = new Date();
        SimpleDateFormat tipoData = new SimpleDateFormat("yyyy-MM-dd");
        String dataOk = tipoData.format(data);
        
        
        ArrayList<VendaItem> vi = new ArrayList<>(obsList);
                v.setCliente(comboboxClientes.getValue());
                v.setData(dataOk);
                v.setNumero(1);
                v.setItens(vi);
                v.setVendedor(comboboxVendedor.getValue());
                VendaDAO.create(v);
                
                limpaTela();

    }
    
    public void calcularColumnValorTotal(){
        double valor = 0;
        
        for(VendaItem vendaItem : obsList){
            valor = valor + vendaItem.getQtd() * vendaItem.getValorUnitario();
            
        }
        
    }
    
    public void calcularTotal(){
        double valor = 0;
        
        for (VendaItem vendaItem : obsList) {
            valor = valor + vendaItem.getQtd() * vendaItem.getValorUnitario();       
    }
        textFieldValorTotal.setText(Double.toString(valor));
    
    
    }

}