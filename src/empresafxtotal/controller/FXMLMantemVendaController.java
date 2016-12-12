/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresafxtotal.controller;

import empresafxtotal.controller.classes.Cliente;
import empresafxtotal.controller.classes.Funcionario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
    private AnchorPane anchorPane;
    @FXML
    private ComboBox<Funcionario> comboBoxCargo;
    @FXML
    private ComboBox<Cliente> comboBoxCliente;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
