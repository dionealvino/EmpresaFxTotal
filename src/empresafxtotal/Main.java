package empresafxtotal;

import empresafxtotal.controller.classes.Cliente;
import empresafxtotal.controller.classes.Funcionario;
import empresafxtotal.controller.classes.Produto;
import empresafxtotal.controller.classes.Venda;
import empresafxtotal.controller.classes.VendaItem;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Usuario-PC
 */
public class Main extends Application {
    

    

    
    @Override
    public void start(Stage stage) throws Exception {
        
//        Cliente c = new Cliente();
//        Funcionario f = new Funcionario();
//        Venda v = new Venda(44, new Date(), c, f);
//        
//        ArrayList<VendaItem> vis = new ArrayList<>();
//        vis.add(new VendaItem(5, 3, new Produto()));
//        
        
        
        
        
        
        
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLTelaPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        stage.setTitle("EmpresaFX-TOTAL 1.0");
        Image icone = new Image(getClass().getResourceAsStream("resources/icon.png"));
        stage.getIcons().add(icone);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
    
    
    
    
}
