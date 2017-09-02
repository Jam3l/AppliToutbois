package main;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
	        this.primaryStage.getIcons().add(new Image("file:Images/Dragon.png"));
	        

	        
	        initRootLayout();
	        showMenuProspect();
	        showMenuRepresentant();
	        showMenuClient();
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
	            AnchorPane MenuPrincipal = (AnchorPane) loader.load();

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
	        File file3 = getRepresentantFilePath();
	        if (file3 != null) {
	            loadRepresentantDataFromFile(file3);
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
	            controller.setMainApp(this);
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
	        File file2 = getProspectFilePath();
	        if (file2 != null) {
	            loadProspectDataFromFile(file2);
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
	            controllerP.setMainApp(this);
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
	            Scene scene = new Scene(page);
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
	        Preferences prefs2 = Preferences.userNodeForPackage(MainApp.class);
	        String filePath2 = prefs2.get("prospect", null);
	        if (filePath2 != null) {
	            return new File(filePath2);
	        } else {
	            return null;
	        }
	    }
	    public File getRepresentantFilePath() {
	        Preferences prefs3 = Preferences.userNodeForPackage(MainApp.class);
	        String filePath3 = prefs3.get("representant", null);
	        if (filePath3 != null) {
	            return new File(filePath3);
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
	    public void setProspectFilePath(File file2) {
	        Preferences prefs2 = Preferences.userNodeForPackage(MainApp.class);
	        if (file2 != null) {
	            prefs2.put("prospect", file2.getPath());
	            // Update the stage title.
	            primaryStage.setTitle("Toutbois - " + file2.getName());
	        } else {
	            prefs2.remove("prospect");
	            // Update the stage title.
	            primaryStage.setTitle("Toutbois");
	        }
	    }  
	    public void setRepresentantFilePath(File file3) {
	        Preferences prefs3 = Preferences.userNodeForPackage(MainApp.class);
	        if (file3 != null) {
	            prefs3.put("representant", file3.getPath());
	            // Update the stage title.
	            primaryStage.setTitle("Toutbois - " + file3.getName());
	        } else {
	            prefs3.remove("representant");
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
	            setClientFilePath(file);

	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Could not load data");
	            alert.setContentText("Could not load data from file(client):\n" + file.getPath());

	            alert.showAndWait();
	        }
	    }
	    public void loadProspectDataFromFile(File file2) {
	        try {
	            JAXBContext context2 = JAXBContext.newInstance(ProspectListeEnregistrement.class);
	            Unmarshaller um2 = context2.createUnmarshaller();

	            // Reading XML from the file and unmarshalling.
	            ProspectListeEnregistrement wrapper2 = (ProspectListeEnregistrement) um2.unmarshal(file2);

	            prospectData.clear();
	            prospectData.addAll(wrapper2.getProspects());
	         // Save the file path to the registry.
	            setProspectFilePath(file2);

	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Could not load data");
	            alert.setContentText("Could not load data from file:\n" + file2.getPath());

	            alert.showAndWait();
	        }
	    }
	    public void loadRepresentantDataFromFile(File file3) {
	        try {
	            JAXBContext context3 = JAXBContext.newInstance(RepresentantListeEnregistrement.class);
	            Unmarshaller um3 = context3.createUnmarshaller();

	            // Reading XML from the file and unmarshalling.
	            RepresentantListeEnregistrement wrapper3 = (RepresentantListeEnregistrement) um3.unmarshal(file3);

	            representantData.clear();
	            representantData.addAll(wrapper3.getRepresentants());
	            
	            // Save the file path to the registry.
	            setRepresentantFilePath(file3);

	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Could not load data");
	            alert.setContentText("Could not load data from file:\n" + file3.getPath());

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
	    public void saveProspectDataToFile(File file2) {
	        try {
	            JAXBContext context2 = JAXBContext.newInstance(ProspectListeEnregistrement.class);
	            Marshaller m2 = context2.createMarshaller();
	            m2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	            // Wrapping our person data.
	            ProspectListeEnregistrement wrapper2 = new ProspectListeEnregistrement();
	            wrapper2.setProspects(prospectData);

	            // Marshalling and saving XML to the file.
	            m2.marshal(wrapper2, file2);

	            // Save the file path to the registry.
	            setProspectFilePath(file2);
	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Could not save data");
	            alert.setContentText("Could not save data to file:\n" + file2.getPath());

	            alert.showAndWait();
	        }
	    }
	    public void saveRepresentantDataToFile(File file3) {
	        try {
	            JAXBContext context3 = JAXBContext.newInstance(RepresentantListeEnregistrement.class);
	            Marshaller m3 = context3.createMarshaller();
	            m3.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	            // Wrapping our person data.
	            RepresentantListeEnregistrement wrapper3 = new RepresentantListeEnregistrement();
	            wrapper3.setRepresentants(representantData);

	            // Marshalling and saving XML to the file.
	            m3.marshal(wrapper3, file3);

	            // Save the file path to the registry.
	            setRepresentantFilePath(file3);
	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Could not save data");
	            alert.setContentText("Could not save data to file:\n" + file3.getPath());

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
