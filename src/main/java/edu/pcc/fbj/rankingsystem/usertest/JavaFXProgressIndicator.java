package edu.pcc.fbj.rankingsystem.usertest;

/**
 * Created by BeeYean on 5/5/2016.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

/**
 *
 * @author nagashayan
 */
public class JavaFXProgressIndicator extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Wetechies Progress Indicator demo");
        stage.show();
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
        launch(args);
    }
}