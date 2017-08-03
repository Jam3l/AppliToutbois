package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Client;
import view.ClientFormulaireController;
import view.ClientOverviewController;
import view.MenuPrincipaleController;



	public class MainApp extends Application {

	    private Stage primaryStage;
	    private BorderPane rootLayout;    
	    private ObservableList<Client> clientData = FXCollections.observableArrayList();
	    
	    public MainApp() {
	    	//clientData.add(new Client("C235467", "Muster","12 rue du bois 59160 lomme"));
	        //clientData.add(new Client("C354673", "Muller","675 BV principale 59000 lille"));
	    }
	    public ObservableList<Client> getClientData() {
	        return clientData;
	    }    
	    @Override
	    public void start(Stage primaryStage) {
	        this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("Toutbois");

	        initRootLayout();
	        showMenuPrincipale();
	    }
	    public void initRootLayout() {
	        try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
	            rootLayout = (BorderPane) loader.load();

	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    public void showMenuPrincipale() {
	        try {
	            
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("/view/MenuPrincipal.fxml"));
	            HBox MenuPrincipal = (HBox) loader.load();

	            
	            rootLayout.setCenter(MenuPrincipal);
	            MenuPrincipaleController controller = loader.getController();
	            controller.setMainApp(this);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void showMenuClient() {
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("/view/ClientFenetre.fxml"));
	            AnchorPane ClientFenetre = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(ClientFenetre);
	            ClientOverviewController controller = loader.getController();
	            controller.setMainApp(this);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    public boolean showClientFormulaire(Client client) {
	        try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("/view/ClientFormulaire.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Edit Person");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            ClientFormulaireController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setClient(client);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    public Stage getPrimaryStage() {
	        return primaryStage;
	    }
	public static void main(String[] args) {
		launch(args);
	}
}
