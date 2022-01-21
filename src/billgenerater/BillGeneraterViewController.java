/**
 * Sample Skeleton for 'BillGeneraterView.fxml' Controller Class
 */

package billgenerater;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class BillGeneraterViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="combomob"
    private ComboBox<String> combomob; // Value injected by FXMLLoader

    @FXML // fx:id="txtdos"
    private DatePicker txtdos; // Value injected by FXMLLoader

    @FXML // fx:id="txttotalprice"
    private TextField txttotalprice; // Value injected by FXMLLoader

    @FXML // fx:id="txtutd"
    private DatePicker txtutd; // Value injected by FXMLLoader

    @FXML // fx:id="txtbillamount"
    private TextField txtbillamount; // Value injected by FXMLLoader
    java.sql.PreparedStatement pst;
    @FXML
    void docombomob(ActionEvent event) {
    	try
      	 {
      			
          	 pst=con.prepareStatement("select tprice,dos from customers where mobile=? ");
          	 pst.setString(1,combomob.getSelectionModel().getSelectedItem());
          	 
          	 ResultSet table=pst.executeQuery();	
      		while(table.next())
      		{	
      		java.sql.Date doj=table.getDate("dos");
      		float price=table.getFloat("tprice");
      		
      		txtdos.setValue(doj.toLocalDate()); 
      		txttotalprice.setText(String.valueOf(price));
      		}
      		
      	 }
          	catch (SQLException e) 
          	{
      	       // TODO Auto-generated catch block
      	       e.printStackTrace();
          	}
    }

    @FXML
    void dogeneratebill(ActionEvent event) {
    	LocalDate dos =txtdos.getValue() ;
    	LocalDate doe = txtutd.getValue();
    	
    	String ds=String.valueOf(dos);
    	String de=String.valueOf(doe);
    
    	LocalDate d1 = LocalDate.parse(ds, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse(de, DateTimeFormatter.ISO_LOCAL_DATE);
       
    	Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        
    	long diffDays = diff.toDays();
    	if(diffDays>0)
    	{
    	Float df=Float.parseFloat(String.valueOf(diffDays));
    	
    //    System.out.println("Diffrence between dates is : "+diffDays + "days");
         
         Float t=Float.parseFloat(txttotalprice.getText());
       
         Float gen=df*t;
       
        txtbillamount.setText(String.valueOf(gen));
    	}
    	else
    		System.err.println("\n Enter Correct Date !! \n");
    }

    @FXML
    void dosave(ActionEvent event) {
    	try {
			pst=con.prepareStatement("insert into bills values(?,?,?,?,?,?)",(Statement.RETURN_GENERATED_KEYS));
			pst.setString(1, null);
			pst.setString(5,"0");
			pst.setString(6, combomob.getSelectionModel().getSelectedItem());
			LocalDate ds = txtdos.getValue();
			pst.setDate(2,java.sql.Date.valueOf(ds));
			
			LocalDate de = txtutd.getValue();
			pst.setDate(3,java.sql.Date.valueOf(de));
			
			pst.setString(4,txtbillamount.getText());
			pst.executeUpdate();
			
			//-----------------------------------//
			
			pst=con.prepareStatement("update customers set dos=? where mobile=?");
			LocalDate date = txtutd.getValue();
			date=date.plusDays(1);
			pst.setDate(1,java.sql.Date.valueOf(date));
			pst.setString(2,combomob.getSelectionModel().getSelectedItem());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    void dofillmobile()
    {
    	combomob.getEditor().clear();
    	try {
			pst=con.prepareStatement("select distinct mobile from customers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String mob=table.getString("mobile");
				combomob.getItems().add(mob);
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
    	dofillmobile();

    }
}
