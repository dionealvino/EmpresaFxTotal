/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.controller.classes.Cliente;
import empresafxtotal.controller.classes.Funcionario;
import empresafxtotal.controller.classes.Produto;
import empresafxtotal.controller.classes.Venda;
import empresafxtotal.controller.classes.VendaItem;
import empresafxtotal.model.ClienteDAO;
import empresafxtotal.model.FuncionarioDAO;
import empresafxtotal.model.ProdutoDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author dione
 */
public class FXMLMantemVendaController implements Initializable {
    
//    private Funcionario f;
//    private Cliente c;
//    private int pkFuncionario;
//    private int pkCliente;
    
    
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


    public void salvar() {
        ArrayList<VendaItem> vi = new ArrayList<>(obsprodutos);
        
        

    }
    
    public void load(){
    }
    
}
