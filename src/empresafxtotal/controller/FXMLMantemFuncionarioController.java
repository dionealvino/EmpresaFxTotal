package empresafxtotal.controller;

import empresafxtotal.controller.classes.Cargo;
import empresafxtotal.controller.classes.Funcionario;
import empresafxtotal.controller.classes.FuncionarioEndereco;
import empresafxtotal.model.CargoDAO;
import empresafxtotal.model.FuncionarioDAO;
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
 * @author Barbara, Dione
 */
public class FXMLMantemFuncionarioController implements Initializable {

    private Funcionario f;
    private FuncionarioEndereco e;
    private int pkCargo;
    private int pkFuncionario;
    private int fkEndereco;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField textFieldnome;
    @FXML
    private TextField textFieldCpf;
    @FXML
    private ComboBox<Cargo> comboBoxCargo;
    @FXML
    private TextField textFieldEndereco;
    @FXML
    private TextField textFieldBairro;
    @FXML
    private TextField textFieldCidade;
    @FXML
    private TextField textFieldCep;
    @FXML
    private ComboBox<String> comboBoxEstado;
    @FXML
    private ComboBox<String> comboBoxPais;
    @FXML
    private ComboBox<Funcionario> comboBoxFuncionarios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            comboBoxEstado.getItems().addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO");
            comboBoxPais.getItems().addAll("Brasil", "Argentina", "EUA");

            List<Funcionario> l = FuncionarioDAO.retreaveAll();
            comboBoxFuncionarios.getItems().addAll(l);
            List<Cargo> carg = CargoDAO.retreaveAll();
            comboBoxCargo.getItems().addAll(carg);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMantemFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load() {
        f = comboBoxFuncionarios.getValue();

        textFieldnome.setText(f.getNome());
        textFieldCpf.setText(f.getCpf());
        comboBoxCargo.setValue(f.getCargo());
        textFieldEndereco.setText(f.getFuncEndereco().getLogradouro());
        textFieldBairro.setText(f.getFuncEndereco().getBairro());
        textFieldCep.setText(f.getFuncEndereco().getCep());
        textFieldCidade.setText(f.getFuncEndereco().getCidade());
        comboBoxEstado.setValue(f.getFuncEndereco().getEstado());
        comboBoxPais.setValue(f.getFuncEndereco().getPais());
        pkCargo = f.getFk_cargo();
        pkFuncionario = f.getPk_funcionario();
        fkEndereco = f.getFuncEndereco().getPk_endereco();
    }

    public void limpaTela() {
        textFieldBairro.clear();
        textFieldCep.clear();
        textFieldCpf.clear();
        textFieldCidade.clear();
        textFieldEndereco.clear();
        textFieldnome.clear();
        comboBoxEstado.getSelectionModel().clearSelection();
        comboBoxPais.getSelectionModel().clearSelection();
    }

    public void salvar() throws SQLException {
        boolean insert = false;

        if (f == null) {
            f = new Funcionario();
            e = new FuncionarioEndereco();
            insert = true;
        }
        if (insert) {
            e = new FuncionarioEndereco(textFieldEndereco.getText(), textFieldBairro.getText(), textFieldCidade.getText(), comboBoxEstado.getValue(), comboBoxPais.getValue(), textFieldCep.getText());
            f = new Funcionario(textFieldnome.getText(), textFieldCpf.getText(), comboBoxCargo.getValue().getPk_cargo());
            f.setFuncEndereco(e);
            f.save();
        } else {
            System.err.println("Edicao pkFuncionario+" + pkFuncionario);
            e = new FuncionarioEndereco(textFieldEndereco.getText(), textFieldBairro.getText(), textFieldCidade.getText(), comboBoxEstado.getValue(), comboBoxPais.getValue(), textFieldCep.getText(), fkEndereco, pkFuncionario);
            f = new Funcionario(pkFuncionario, textFieldnome.getText(), textFieldCpf.getText(), comboBoxCargo.getValue().getPk_cargo());
            f.setFuncEndereco(e);
            f.update();
        }
    }

    public void cancelar() {

    }
}
