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
	        this.primaryStage.getIcons().add(new Image("file:Images/Dragon.png"));//icone de l'appli  
	        // chargement du root layout
	        initRootLayout();
	        // chargement des fichiers xml de sauvegarde
	        File file = getClientFilePath();
	        if (file != null) {
	            loadClientDataFromFile(file);}
	        File file2 = getProspectFilePath();
	        if (file2 != null) {
	            loadProspectDataFromFile(file2);}
	        File file3 = getRepresentantFilePath();
	        if (file3 != null) {
	            loadRepresentantDataFromFile(file3);}
	        // chargement pour affichage du menu principal
	        showMenuPrincipale();
	    }
	    public void initRootLayout() {
	    	try {
	            // charge le root layout a partir du fichier fxml
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
	            rootLayout = (BorderPane) loader.load();
	            // affichage de la scene contenant le root layout.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            // Donne l'accès au contrôleur main app.
	            RootLayoutController controller = loader.getController();
	            controller.setMainApp(this);
	            // affichage du primaryStage
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    //Fenetre principale
	    public void showMenuPrincipale() {
	        try {
	        	// charge le menu principale a partir du fichier fxml
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("/view/MenuPrincipal.fxml"));
	            AnchorPane MenuPrincipal = (AnchorPane) loader.load();
                // affichage du menu principale dans le root layout
	            rootLayout.setCenter(MenuPrincipal);
	            // Donne l'accès au contrôleur
	            MenuPrincipaleController controller = loader.getController();
	            controller.setMainApp(this);   
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    //Fenetre client
	    public void showMenuClient() {
	        try {
	        	// charge le menu client a partir du fichier fxml
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("/view/ClientFenetre.fxml"));
	            AnchorPane ClientFenetre = (AnchorPane) loader.load();
	            // affichage du menu client dans le root layout
	            rootLayout.setCenter(ClientFenetre);
	            // Donne l'accès au contrôleur
	            ClientOverviewController controller = loader.getController();
	            controller.setMainApp(this);    
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        // charge le fichier xml client
	        File file = getClientFilePath();
	        if (file != null) {
	            loadClientDataFromFile(file);
	        }
	    }  
	    //Fenetre représentant
	    public void showMenuRepresentant() {
	        try {
	        	// charge le menu representant a partir du fichier fxml
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("/view/RepresentantFenetre.fxml"));
	            AnchorPane RepresentantFenetre = (AnchorPane) loader.load();
	            // affichage du menu representant dans le root layout
	            rootLayout.setCenter(RepresentantFenetre);
	            // Donne l'accès au contrôleur
	            RepresentantOverviewController controller = loader.getController();
	            controller.setMainApp(this); 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        // charge le fichier xml representant
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
	            // Donne l'accès au contrôleur
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
	        	// Charge le fichier fxml du formulaire prospect
	            FXMLLoader loaderP = new FXMLLoader();
	            loaderP.setLocation(MainApp.class.getResource("/view/ProspectFenetre.fxml"));
	            AnchorPane ProspectFenetre = (AnchorPane) loaderP.load();
	            // affichage du menu representant dans le root layout
	            rootLayout.setCenter(ProspectFenetre);
	            // Donne l'accès au contrôleur
	            ProspectOverviewController controllerP = loaderP.getController();
	            controllerP.setMainApp(this);    
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        // charge le fichier xml representant
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
	            // Donne l'accès au contrôleur
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
	            // Donne l'accès au contrôleur
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
	    //  Définit le chemin du fichier actuellement chargé. Le chemin est persévéré dans le registre spécifique au système d'exploitation.
	    public void setClientFilePath(File file) {
	        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	        if (file != null) {
	            prefs.put("client", file.getPath());
	            // Met à jour le titre de la scène.
	            primaryStage.setTitle("Toutbois - " + file.getName());
	        } else {
	            prefs.remove("client");
	            // Met à jour le titre de la scène.
	            primaryStage.setTitle("Toutbois");
	        }
	    }  
	    public void setProspectFilePath(File file2) {
	        Preferences prefs2 = Preferences.userNodeForPackage(MainApp.class);
	        if (file2 != null) {
	            prefs2.put("prospect", file2.getPath());
	            // Met à jour le titre de la scène.
	            primaryStage.setTitle("Toutbois - " + file2.getName());
	        } else {
	            prefs2.remove("prospect");
	            // Met à jour le titre de la scène.
	            primaryStage.setTitle("Toutbois");
	        }
	    }  
	    public void setRepresentantFilePath(File file3) {
	        Preferences prefs3 = Preferences.userNodeForPackage(MainApp.class);
	        if (file3 != null) {
	            prefs3.put("representant", file3.getPath());
	            // Met à jour le titre de la scène.
	            primaryStage.setTitle("Toutbois - " + file3.getName());
	        } else {
	            prefs3.remove("representant");
	            // Met à jour le titre de la scène..
	            primaryStage.setTitle("Toutbois");
	        }
	    }  
	    // methode pour le chargement du fichier client
	    public void loadClientDataFromFile(File file) {
	        try {
	            JAXBContext context = JAXBContext.newInstance(ClientListeEnregistrement.class);
	            Unmarshaller um = context.createUnmarshaller();
	            // Lecture du XML à partir du fichier
	            ClientListeEnregistrement wrapper = (ClientListeEnregistrement) um.unmarshal(file);
	            //effacement du fichier
	            clientData.clear();
	            //enregistrement du fichier
	            clientData.addAll(wrapper.getClients());
	            // Enregistre le chemin du fichier dans le registre.
	            setClientFilePath(file);
	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Impossible de charger des données");
	            alert.setContentText("Impossible de charger les données à partir de :\n" + file.getPath());
	            alert.showAndWait();
	        }
	    }
	    // methode pour le chargement du fichier prospect
	    public void loadProspectDataFromFile(File file2) {
	        try {
	            JAXBContext context2 = JAXBContext.newInstance(ProspectListeEnregistrement.class);
	            Unmarshaller um2 = context2.createUnmarshaller();
	            // Lecture du XML à partir du fichier
	            ProspectListeEnregistrement wrapper2 = (ProspectListeEnregistrement) um2.unmarshal(file2);
	            //effacement du fichier
	            prospectData.clear();
	            //enregistrement du fichier
	            prospectData.addAll(wrapper2.getProspects());
	            // Enregistre le chemin du fichier dans le registre.
	            setProspectFilePath(file2);
	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Impossible de charger des données");
	            alert.setContentText("Impossible de charger les données à partir de :\n" + file2.getPath());
	            alert.showAndWait();
	        }
	    }
	    // methode pour le chargement du fichier representant
	    public void loadRepresentantDataFromFile(File file3) {
	        try {
	            JAXBContext context3 = JAXBContext.newInstance(RepresentantListeEnregistrement.class);
	            Unmarshaller um3 = context3.createUnmarshaller();
	            // Lecture du XML à partir du fichier
	            RepresentantListeEnregistrement wrapper3 = (RepresentantListeEnregistrement) um3.unmarshal(file3);
	            //effacement du fichier
	            representantData.clear();
	            //enregistrement du fichier
	            representantData.addAll(wrapper3.getRepresentants());
	            // Enregistre le chemin du fichier dans le registre.
	            setRepresentantFilePath(file3);
	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Impossible de charger des données");
	            alert.setContentText("Impossible de charger les données à partir de :\n" + file3.getPath());
	            alert.showAndWait();
	        }
	    }
	    //Enregistre les données dans le fichier spécifié.
	    public void saveClientDataToFile(File file) {
	        try {
	            JAXBContext context = JAXBContext.newInstance(ClientListeEnregistrement.class);
	            Marshaller m = context.createMarshaller();
	            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	            // enregistrement des données
	            ClientListeEnregistrement wrapper = new ClientListeEnregistrement();
	            wrapper.setClients(clientData);
	            // enregistrer XML dans le fichier.
	            m.marshal(wrapper, file);
	            // Enregistre le chemin du fichier dans le registre.
	            setClientFilePath(file);
	        } catch (Exception e) { 
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Impossible d'enregistrer les données");
	            alert.setContentText("Impossible d'enregistrer les données à partir de :\n" + file.getPath());
	            alert.showAndWait();
	        }
	    }
	    public void saveProspectDataToFile(File file2) {
	        try {
	            JAXBContext context2 = JAXBContext.newInstance(ProspectListeEnregistrement.class);
	            Marshaller m2 = context2.createMarshaller();
	            m2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	            // enregistrement des données
	            ProspectListeEnregistrement wrapper2 = new ProspectListeEnregistrement();
	            wrapper2.setProspects(prospectData);
	            // enregistrer XML dans le fichier.
	            m2.marshal(wrapper2, file2);
	            // Enregistre le chemin du fichier dans le registre.
	            setProspectFilePath(file2);
	        } catch (Exception e) { // catches ANY exception
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Impossible d'enregistrer les données");
	            alert.setContentText("Impossible d'enregistrer les données à partir de :\n" + file2.getPath());
	            alert.showAndWait();
	        }
	    }
	    public void saveRepresentantDataToFile(File file3) {
	        try {
	            JAXBContext context3 = JAXBContext.newInstance(RepresentantListeEnregistrement.class);
	            Marshaller m3 = context3.createMarshaller();
	            m3.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	            // enregistrement des données
	            RepresentantListeEnregistrement wrapper3 = new RepresentantListeEnregistrement();
	            wrapper3.setRepresentants(representantData);
	            // enregistrer XML dans le fichier.
	            m3.marshal(wrapper3, file3);
	            // Enregistre le chemin du fichier dans le registre.
	            setRepresentantFilePath(file3);
	        } catch (Exception e) { 
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur");
	            alert.setHeaderText("Impossible d'enregistrer les données");
	            alert.setContentText("Impossible d'enregistrer les données à partir de :\n" + file3.getPath());
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
