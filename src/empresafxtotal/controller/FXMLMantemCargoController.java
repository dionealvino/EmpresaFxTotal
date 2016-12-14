package empresafxtotal.controller;

import empresafxtotal.controller.classes.Cargo;
import empresafxtotal.model.CargoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Bárbara, Dione
 */
public class FXMLMantemCargoController implements Initializable {
    private int pkCargo;
    private Cargo c;
    

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldDescricao;

    @FXML
    private ComboBox<Cargo> comboBoxCargos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Cargo> l = CargoDAO.retreaveAll();
            comboBoxCargos.getItems().addAll(l);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMantemCargoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void load(){
        c = comboBoxCargos.getValue();
        textFieldNome.setText(c.getNome());
        textFieldDescricao.setText(c.getDescricao());
        pkCargo = c.getPk_cargo();
    }
    public void limpaTela(){
        textFieldNome.clear();
        textFieldDescricao.clear();
    }
    
    public void salvar() throws SQLException{
       boolean insert = false;
       if(c==null){
           c = new Cargo();
           insert=true;
       }
       if(insert){
           c = new Cargo(textFieldNome.getText(), textFieldDescricao.getText());
           c.save();
       }
       else{
           c = new Cargo(pkCargo,textFieldNome.getText(),textFieldDescricao.getText());
           c.update();
       }
       limpaTela();
    }
    
}
