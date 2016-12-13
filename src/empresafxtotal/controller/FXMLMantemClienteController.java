package empresafxtotal.controller;

import empresafxtotal.controller.classes.Cliente;
import empresafxtotal.controller.classes.Endereco;
import empresafxtotal.model.ClienteDAO;
import empresafxtotal.model.EnderecoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Usuario-PC
 */
public class FXMLMantemClienteController implements Initializable {

    private Endereco e;
    private Cliente c;
    private int fkCliente = 0;
    private int pkEndereco = 0;

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
    private ComboBox<Cliente> comboBoxClientes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxEstado.getItems().addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO");
        comboBoxPais.getItems().addAll("Brasil", "EUA");

        List<Cliente> l = ClienteDAO.retreaveAll();
        comboBoxClientes.getItems().addAll(l);
    }

    public void load() {
        c = comboBoxClientes.getValue();

        textFieldNome.setText(c.getNome());
        textFieldCPF.setText(c.getCpf());
        textFieldEndereco.setText(c.getEndereco().getLogradouro());
        textFieldBairro.setText(c.getEndereco().getBairro());
        textFieldCEP.setText(c.getEndereco().getCep());
        textFieldCidade.setText(c.getEndereco().getCidade());
        comboBoxEstado.setValue(c.getEndereco().getEstado());
        comboBoxPais.setValue(c.getEndereco().getPais());
        fkCliente = c.getPk_cliente();
        pkEndereco = c.getEndereco().getPk_endereco();

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

        if (c == null) {
            c = new Cliente();
            e = new Endereco();
            insert = true;
        }
        //Controller.Endereco e = new Controller.Endereco(logradouro, bairro, cidade, estado, pais, cep)

        //c.setEndereco(e);
        //ClienteDAO.create(c);
        if (insert) {
            e = new Endereco(textFieldEndereco.getText(), textFieldBairro.getText(), textFieldCidade.getText(), comboBoxEstado.getValue(), comboBoxPais.getValue(), textFieldCEP.getText());
            c = new Cliente(textFieldNome.getText(), textFieldCPF.getText());
            c.setEndereco(e);
            c.save();
        } else {
            e = new Endereco(textFieldEndereco.getText(), textFieldBairro.getText(), textFieldCidade.getText(), comboBoxEstado.getValue(), comboBoxPais.getValue(), textFieldCEP.getText(), pkEndereco, fkCliente);
            c = new Cliente(fkCliente, textFieldNome.getText(), textFieldCPF.getText());
            c.setEndereco(e);
            c.update();
        }

        limpaTela();

    }

    public void cancelar() {
        limpaTela();
    }
}
