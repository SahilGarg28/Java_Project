/**
 * Sample Skeleton for 'DashboardView.fxml' Controller Class
 */

package dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DashboardViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    void doarea(MouseEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("areaMaster/AreaMasterView.fxml"));
	    	Scene scene = new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 


    }

    @FXML
    void dobillcoll(MouseEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("billcollection/BillCollectionView.fxml"));
	    	Scene scene = new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    @FXML
    void dobillgen(MouseEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("billgenerater/BillGeneraterView.fxml"));
	    	Scene scene = new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    @FXML
    void docustomer(MouseEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("coustmerEntry/CoustmerEntryView.fxml"));
	    	Scene scene = new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    @FXML
    void dohawker(MouseEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("hawkerControlPannel/HawkerControlPannelView.fxml"));
	    	Scene scene = new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    @FXML
    void dopaper(MouseEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("paperMaster/PaperMasterView.fxml"));
	    	Scene scene = new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    @FXML
    void showcustomer(MouseEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("customerdisplay/CustomerDisplay.fxml"));
	    	Scene scene = new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    @FXML
    void showhawker(MouseEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("hawkerdisplay/HawkerDisplay.fxml"));
	    	Scene scene = new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    @FXML
    void showstatus(MouseEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("statusdisplay/StatusDisplay.fxml"));
	    	Scene scene = new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
