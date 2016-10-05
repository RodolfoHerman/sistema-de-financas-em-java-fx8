package br.com.rodolfo.sisfinancias.main;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MainApp extends Application {

    private static Stage mainStage;
    private static BorderPane principalView;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        mainStage = stage;
        mainStage.setTitle("Sistema de Financias");
        mainStage.centerOnScreen();
        mainStage.setFullScreen(false);
        mainStage.setResizable(false);
        
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        
        mostrarPrincipalView();

        mostrarConteudoView();
        
    }
    
    private void mostrarPrincipalView () throws IOException {
        
        principalView = FXMLLoader.load(MainApp.class.getResource("/views/telaInicial.fxml"));
        
        Scene scene = new Scene(principalView);
        
        
        mainStage.setScene(scene);
        mainStage.show();        

    }
    
    private void mostrarConteudoView () throws IOException {
        
        BorderPane borderPane = FXMLLoader.load(MainApp.class.getResource("/views/telaTabelaItens.fxml"));
        principalView.setCenter(borderPane);
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //EntityManager em = Conexao.getEntityManager();
        //em = null;
        launch(args);
    }

}
