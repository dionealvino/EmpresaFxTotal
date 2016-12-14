package empresafxtotal.controller;

import empresafxtotal.controller.classes.Fornecedor;
import empresafxtotal.controller.classes.FornecedorEndereco;
import empresafxtotal.model.FornecedorDAO;
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
public class FXMLMantemFornecedorController implements Initializable {

    private FornecedorEndereco e;
    private Fornecedor f;
    int pkEndereco;
    int fkFornecedor;

    @FXML
    private TextField textFieldNome;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField textFieldCPF;
    @FXML
    private TextField textFieldEndereco;
    @FXML
    private TextField textFieldBairro;
    @FXML
    private TextField textFieldCidade;
    @FXML
    private ComboBox<String> comboBoxEstado;
    @FXML
    private ComboBox<String> comboBoxPais;
    @FXML
    private TextField textFieldCEP;
    @FXML
    private ComboBox<Fornecedor> comboBoxClientes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            comboBoxEstado.getItems().addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO");
            comboBoxPais.getItems().addAll("Brasil", "Argentina", "USA");

            List<Fornecedor> l = FornecedorDAO.retreaveAll();
            comboBoxClientes.getItems().addAll(l);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMantemFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load() {
        f = comboBoxClientes.getValue();

        textFieldNome.setText(f.getNome());
        textFieldCPF.setText(f.getCpf());
        textFieldEndereco.setText(f.getForneEnd().getLogradouro());
        textFieldBairro.setText(f.getForneEnd().getBairro());
        textFieldCEP.setText(f.getForneEnd().getCep());
        textFieldCidade.setText(f.getForneEnd().getCidade());
        comboBoxEstado.setValue(f.getForneEnd().getEstado());
        comboBoxPais.setValue(f.getForneEnd().getPais());
        fkFornecedor = f.getPk_fornecedor();
        pkEndereco = f.getForneEnd().getPk_endereco();

    }

    public void limpaTela() {
        textFieldBairro.clear();
        textFieldCEP.clear();
        textFieldCPF.clear();
        textFieldCidade.clear();
        textFieldEndereco.clear();
        textFieldNome.clear();
        comboBoxEstado.getSelectionModel().clearSelection();
        comboBoxPais.getSelectionModel().clearSelection();
    }

    public void salvar() throws SQLException {
        boolean insert = false;

        if (f == null) {
            f = new Fornecedor();
            e = new FornecedorEndereco();
            insert = true;
        }

        if (insert) {
            e = new FornecedorEndereco(textFieldEndereco.getText(), textFieldBairro.getText(), textFieldCidade.getText(), comboBoxEstado.getValue(), comboBoxPais.getValue(), textFieldCEP.getText());
            f = new Fornecedor(textFieldNome.getText(), textFieldCPF.getText());
            f.setForneEnd(e);
            f.save();
        } else {
            e = new FornecedorEndereco(textFieldEndereco.getText(), textFieldBairro.getText(), textFieldCidade.getText(), comboBoxEstado.getValue(), comboBoxPais.getValue(), textFieldCEP.getText(), pkEndereco, fkFornecedor);
            f = new Fornecedor(fkFornecedor, textFieldNome.getText(), textFieldCPF.getText());
            f.setForneEnd(e);
            f.update();

        }

        limpaTela();
    }

    public void cancelar() {
        limpaTela();
    }
}
