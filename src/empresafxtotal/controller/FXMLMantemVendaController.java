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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author dione
 */
public class FXMLMantemVendaController implements Initializable {
    
    private Funcionario f;
    private Cliente c;
    private int pkFuncionario;
    private int pkCliente;
    

    @FXML
    private ComboBox<Cliente> comboboxClientes;

    @FXML
    private TextField textFieldValor;

    @FXML
    private TableColumn<Venda,Double> columnVrTotal;

    @FXML
    private ComboBox<Funcionario> comboboxVendedor;

    @FXML
    private TableColumn<VendaItem, Double> columnVrUnitario;

    @FXML
    private TextField textFieldQtd;

    @FXML
    private ComboBox<Produto> comboboxProduto;

    @FXML
    private TableColumn<VendaItem, String> columnProduto;

    @FXML
    private TableColumn<VendaItem, Integer> columnqtd;



    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void limpaTela() {

    }


    public void salvar() {

    }
    
    public void load(){
    }
    
}
