package main;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Client;
import model.ClientListeEnregistrement;
import model.Prospect;
import model.ProspectListeEnregistrement;
import view.ClientFormulaireController;
import view.ClientOverviewController;
import view.MenuPrincipaleController;
import view.ProspectFormulaireController;
import view.ProspectOverviewController;
import model.RepresentantListeEnregistrement;
import model.Representant;
import view.RepresentantFormulaireController;
import view.RepresentantOverviewController;
import view.RootLayoutController;



	public class MainApp extends Application {

	    private Stage primaryStage;
	    private BorderPane rootLayout;  
	    private ObservableList<Client> clientData = FXCollections.observableArrayList();//Liste clients
	    private ObservableList<Prospect> prospectData = FXCollections.observableArrayList();//Liste prospects
	    private ObservableList<Representant> representantData = FXCollections.observableArrayList();//Liste représentants
	    
	    public MainApp() {
	    }
	    public ObservableList<Client> getClientData() {
	        return clientData;
	    }
	    public ObservableList<Prospect> getProspectData() {
	        return prospectData;
	    }
	    public ObservableList<Representant> getRepresentantData() {
	        return representantData;
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

	            // Give the controller access to the main app.
	            RootLayoutController controller = loader.getController();
	            controller.setMainApp(this);

	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    //Fenetre principale
	    public void showMenuPrincipale() {
	        try {
	            
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("/view/MenuPrincipal.fxml"));
	            HBox MenuPrincipal = (HBox) loader.load();

	            
	            rootLayout.setCenter(MenuPrincipal);
	            //rootLayout.hide();
	            MenuPrincipaleController controller = loader.getController();
	            controller.setMainApp(this);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    //Fenetre client
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
	        
	     // Try to load last opened person file.
	        File file = getClientFilePath();
	        if (file != null) {
	            loadClientDataFromFile(file);
	        }
	    }
	    
	    //Fenetre représentant
	    public void showMenuRepresentant() {
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("/view/RepresentantFenetre.fxml"));
	            AnchorPane RepresentantFenetre = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(RepresentantFenetre);
	            RepresentantOverviewController controller = loader.getController();
	            controller.setMainApp(this);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	     // Try to load last opened person file.
	        File file = getRepresentantFilePath();
	        if (file != null) {
	            loadClientDataFromFile(file);
	        }
	    }
	    //Fenetre formulaire client
	    public boolean showClientFormulaire(Client client) {
	        try {
	            // Charge le fichier fxml du formulaire client.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("/view/ClientFormulaire.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Creation du stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Formulaire client");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            ClientFormulaireController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setClient(client);

	            // Afficher la boîte de dialogue et attendre que l'utilisateur la ferme
	            dialogStage.showAndWait();
	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }  
	    }
	    //Fenetre prospect
	    public void showMenuProspect() {
	        try {
	            // Load person overview.
	            FXMLLoader loaderP = new FXMLLoader();
	            loaderP.setLocation(MainApp.class.getResource("/view/ProspectFenetre.fxml"));
	            AnchorPane ProspectFenetre = (AnchorPane) loaderP.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(ProspectFenetre);
	            ProspectOverviewController controllerP = loaderP.getController();
	            controllerP.setMainApp(this);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	     // Try to load last opened person file.
	        File file = getClientFilePath();
	        if (file != null) {
	            loadProspectDataFromFile(file);
	        }
	    }
	  //Fenetre formulaire prospect
	    public boolean showProspectFormulaire(Prospect prospect) {
	        try {
	            // Charge le fichier fxml du formulaire client.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("/view/ProspectFormulaire.fxml"));
	            AnchorPane pageP = (AnchorPane) loader.load();

	            // Creation du stage.
	            Stage dialogStageP = new Stage();
	            dialogStageP.setTitle("Formulaire prospect");
	            dialogStageP.initModality(Modality.WINDOW_MODAL);
	            dialogStageP.initOwner(primaryStage);
	            Scene sceneP = new Scene(pageP);
	            dialogStageP.setScene(sceneP);

	            ProspectFormulaireController controllerP = loader.getController();
	            controllerP.setDialogStage(dialogStageP);
	            controllerP.setProspect(prospect);

	            // Afficher la boîte de dialogue et attendre que l'utilisateur la ferme
	            dialogStageP.showAndWait();
	            return controllerP.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }  
	    }
	  //Fenetre formulaire representant
	    public boolean showRepresentantFormulaire(Representant representant) {
	        try {
	            // Charge le fichier fxml du formulaire Representant.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("/view/RepresentantFormulaire.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Creation du stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Formulaire Representant");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene             = new Scene(page);
	            dialogStage.setScene(scene);

	            RepresentantFormulaireController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setRepresentant(representant);

	            // Afficher la boîte de dialogue et attendre que l'utilisateur la ferme
	            dialogStage.showAndWait();
	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	         
	}
	    public File getClientFilePath() {
	        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	        String filePath = prefs.get("client", null);
	        if (filePath != null) {
	            return new File(filePath);
	        } else {
	            return null;
	        }
	    }
	    public File getProspectFilePath() {
	        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	        String filePath = prefs.get("prospect", null);
	        if (filePath != null) {
	            return new File(filePath);
	        } else {
	            return null;
	        }
	    }
	    public File getRepresentantFilePath() {
	        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	        String filePath = prefs.get("filePath", null);
	        if (filePath != null) {
	            return new File(filePath);
	        } else {
	            return null;
	        }
	    }
	    /**
	     * Sets the file path of the currently loaded file. The path is persisted in
	     * the OS specific registry.
	     * 
	     * @param file the file or null to remove the path
	     */
	    public void setClientFilePath(File file) {
	        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	        if (file != null) {
	            prefs.put("client", file.getPath());
	            // Update the stage title.
	            primaryStage.setTitle("Toutbois - " + file.getName());
	        } else {
	            prefs.remove("client");
	            // Update the stage title.
	            primaryStage.setTitle("Toutbois");
	        }
	    }  
	    public void setProspectFilePath(File file) {
	        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	        if (file != null) {
	            prefs.put("prospect", file.getPath());
	            // Update the stage title.
	            primaryStage.setTitle("Toutbois - " + file.getName());
	        } else {
	            prefs.remove("prospect");
	            // Update the stage title.
	            primaryStage.setTitle("Toutbois");
	        }
	    }  
	    
	    public void setRepresentantFilePath(File file) {
	        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	        if (file != null) {
	            prefs.put("filePath", file.getPath());
	            // Update the stage title.
	            primaryStage.setTitle("Toutbois - " + file.getName());
	        } else {
	            prefs.remove("filePath");
	            // Update the stage title.
	            primaryStage.setTitle("Toutbois");
	        }
	    }  
	    public void loadClientDataFromFile(File file) {
	        try {
	            JAXBContext context = JAXBContext.newInstance(ClientListeEnregistrement.class);
	            Unmarshaller um = context.createUnmarshaller();

	            // Reading XML from the file and unmarshalling.
	            ClientListeEnregistrement wrapper = (ClientListeEnregistrement) um.unmarshal(file);

	            clientData.clear();
	            clientData.addAll(wrapper.getClients());

	            // Save the file path to the registry.
	            //setClientFilePath(file);

	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Could not load data");
	            alert.setContentText("Could not load data from file(client):\n" + file.getPath());

	            alert.showAndWait();
	        }
	    }
	    public void loadProspectDataFromFile(File file) {
	        try {
	            JAXBContext context = JAXBContext.newInstance(ProspectListeEnregistrement.class);
	            Unmarshaller um = context.createUnmarshaller();

	            // Reading XML from the file and unmarshalling.
	            ProspectListeEnregistrement wrapper = (ProspectListeEnregistrement) um.unmarshal(file);

	            prospectData.clear();
	            prospectData.addAll(wrapper.getProspects());
	         // Save the file path to the registry.
	            setClientFilePath(file);

	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Could not load data");
	            alert.setContentText("Could not load data from file:\n" + file.getPath());

	            alert.showAndWait();
	        }
	    }
	    public void loadRepresentantDataFromFile(File file) {
	        try {
	            JAXBContext context = JAXBContext.newInstance(RepresentantListeEnregistrement.class);
	            Unmarshaller um = context.createUnmarshaller();

	            // Reading XML from the file and unmarshalling.
	            RepresentantListeEnregistrement wrapper = (RepresentantListeEnregistrement) um.unmarshal(file);

	            representantData.clear();
	            representantData.addAll(wrapper.getRepresentants());

	            // Save the file path to the registry.
	            setClientFilePath(file);

	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Could not load data");
	            alert.setContentText("Could not load data from file:\n" + file.getPath());

	            alert.showAndWait();
	        }
	    }
	    /**
	     * Saves the current person data to the specified file.
	     * 
	     * @param file
	     */
	    public void saveClientDataToFile(File file) {
	        try {
	            JAXBContext context = JAXBContext.newInstance(ClientListeEnregistrement.class);
	            Marshaller m = context.createMarshaller();
	            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	            // Wrapping our person data.
	            ClientListeEnregistrement wrapper = new ClientListeEnregistrement();
	            wrapper.setClients(clientData);

	            // Marshalling and saving XML to the file.
	            m.marshal(wrapper, file);

	            // Save the file path to the registry.
	            setClientFilePath(file);
	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Could not save data");
	            alert.setContentText("Could not save data to file:\n" + file.getPath());

	            alert.showAndWait();
	        }
	    }
	    public void saveProspectDataToFile(File file) {
	        try {
	            JAXBContext context = JAXBContext.newInstance(ProspectListeEnregistrement.class);
	            Marshaller m = context.createMarshaller();
	            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	            // Wrapping our person data.
	            ProspectListeEnregistrement wrapper = new ProspectListeEnregistrement();
	            wrapper.setProspects(prospectData);

	            // Marshalling and saving XML to the file.
	            m.marshal(wrapper, file);

	            // Save the file path to the registry.
	            setProspectFilePath(file);
	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Could not save data");
	            alert.setContentText("Could not save data to file:\n" + file.getPath());

	            alert.showAndWait();
	        }
	    }
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
