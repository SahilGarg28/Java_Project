/**
 * Sample Skeleton for 'AreaMasterView.fxml' Controller Class
 */

package areaMaster;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import paperMaster.DataBaseConnector;

public class AreaMasterViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboarea"
    private ComboBox<String> comboarea; // Value injected by FXMLLoader
    PreparedStatement pst;


    @FXML
    void dodelete(ActionEvent event) {
    	try {
			pst=con.prepareStatement("delete from areas where area=?");
			pst.setString(1,comboarea.getEditor().getText());
			pst.executeUpdate();
			comboarea.setValue("");
			dofill();
			showmsgdeleted();
			
			
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    @FXML
    void dosave(ActionEvent event) {
    	try {
			pst=con.prepareStatement("insert into areas values(?)");
			pst.setString(1, comboarea.getEditor().getText());
			pst.executeUpdate();
			dofill();
			showmsgsaved();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    void showmsgdeleted()
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("Successfully Deleted");
    			alert.show();
    }
    void showmsgsaved()
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("Data Successfully Saved");
    			alert.show();
    }
    void dofill()
    {
    	
    	try {
    		comboarea.getItems().clear();
			pst=con.prepareStatement("select distinct area from areas");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String areas=table.getString("area");
				comboarea.getItems().add(areas);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=DataBaseConnector.getConnection();
    	dofill();
    }
}
