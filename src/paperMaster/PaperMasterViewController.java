
/**
 * Sample Skeleton for 'PaperMasterView.fxml' Controller Class
 */

package paperMaster;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PaperMasterViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="combopaper"
    private ComboBox<String> combopaper; // Value injected by FXMLLoader

    @FXML // fx:id="txtprice"
    private TextField txtprice; // Value injected by FXMLLoader
    
    PreparedStatement pst;
    @FXML
    void docombopaper(ActionEvent event) {
    	try {
			pst=con.prepareStatement("select * from newspaper where paper=?");
			pst.setString(1,combopaper.getEditor().getText());
			ResultSet table=pst.executeQuery();
			while(table.next()) {
				String paper=table.getString("paper");
				float price=table.getFloat("price");
				combopaper.setValue(paper);
				txtprice.setText(String.valueOf(price));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void dodelete(ActionEvent event) {
    	try {
			pst=con.prepareStatement("delete from newspaper where paper=?");
			pst.setString(1,combopaper.getEditor().getText());
			int c=pst.executeUpdate();
			dorefresh(null);
			showmsgdeleted();
			
			
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void dorefresh(ActionEvent event) {
    	combopaper.setValue("");
    	txtprice.setText("");
    	dofill();
    }

    @FXML
    void dosave(ActionEvent event) {
    	try {
			pst=con.prepareStatement("insert into newspaper values(?,?)");
			pst.setString(1, combopaper.getEditor().getText());
			pst.setFloat(2, Float.parseFloat(txtprice.getText()));
			pst.executeUpdate();
			dofill();
			showmsgsaved();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doupdate(ActionEvent event) {
    	try {
    		pst=con.prepareStatement("update newspaper set price=? where paper=?");
    		pst.setString(2, combopaper.getEditor().getText());
    		pst.setFloat(1, Float.parseFloat(txtprice.getText()));
    		int c=pst.executeUpdate();
    		showmsgupdated();
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }
    void dofill()
    {
    	try {
    		combopaper.getItems().clear();
			pst=con.prepareStatement("select distinct paper from newspaper");
			ResultSet table=pst.executeQuery();
			while(table.next()) {
				String paper=table.getString("paper");
				combopaper.getItems().add(paper);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    void showmsgupdated()
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("Successfully Updated");
    			alert.show();
    }
    void showmsgsaved()
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("Data Successfully Saved");
    			alert.show();
    }
    void showmsgdeleted()
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("Successfully Deleted");
    			alert.show();
    }
    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=DataBaseConnector.getConnection();
    	dofill();

    }
}

