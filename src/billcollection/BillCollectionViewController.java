/**
 * Sample Skeleton for 'BillCollectionView.fxml' Controller Class
 */

package billcollection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class BillCollectionViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="combomob"
    private ComboBox<String> combomob; // Value injected by FXMLLoader

    @FXML // fx:id="txtamount"
    private TextField txtamount; // Value injected by FXMLLoader

    @FXML // fx:id="txtfrom"
    private TextField txtfrom; // Value injected by FXMLLoader

    @FXML // fx:id="txtto"
    private TextField txtto; // Value injected by FXMLLoader

    @FXML
    void dogetbill(ActionEvent event) {
    	try
     	 {
     			
         	 pst=con.prepareStatement("select amount,dos,dateupto from bills where mobile=? and status=0 ");
         	 pst.setString(1,combomob.getSelectionModel().getSelectedItem());
         	 
         	 ResultSet table=pst.executeQuery();	
     		while(table.next())
     		{	
     		float amount=table.getFloat("amount");
     		java.sql.Date dos=table.getDate("dos");
     		java.sql.Date doupto=table.getDate("dateupto");
     		
     		txtamount.setText(String.valueOf(amount));
     		txtfrom.setText(String.valueOf(dos)); 
     		txtto.setText(String.valueOf(doupto));
     		}
     		
     	 }
         	catch (SQLException e) 
         	{
     	       // TODO Auto-generated catch block
     	       e.printStackTrace();
         	}
    }

    @FXML
    void dopaid(ActionEvent event) {
    	try
    	{pst=con.prepareStatement("update bills set status=1 where mobile=? and status=0 ");
		pst.setString(1,combomob.getSelectionModel().getSelectedItem());
		pst.executeUpdate();
		System.out.println("Updated");
	
	}
	catch (SQLException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    void dofillmobile()
    {
    	 try
    	 {
    			
        	 pst=con.prepareStatement("select distinct mobile from bills where status=0 order by mobile ");
        	 ResultSet table=pst.executeQuery();
    		
    		while(table.next())
    		{	
    		String mobile=table.getString("mobile");
    		
    		combomob.getItems().add(mobile);
    		}	
    	 }
        	catch (SQLException e) 
        	{
    	       // TODO Auto-generated catch block
    	       e.printStackTrace();
        	}
    }
    PreparedStatement pst;
    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=DataBaseConnector.getConnection();
    	dofillmobile();
    }
}

