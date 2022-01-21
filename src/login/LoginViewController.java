/**
 * Sample Skeleton for 'LoginView.fxml' Controller Class
 */

package login;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

public class LoginViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtid"
    private TextField txtid; // Value injected by FXMLLoader


    @FXML // fx:id="txtpass"
    private PasswordField txtpass; // Value injected by FXMLLoader

    @FXML // fx:id="btnlogin"
    private Button btnlogin; // Value injected by FXMLLoader
    Calendar calendar=Calendar.getInstance();
    String currentDate=DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
    String Date="29-Sep-2021";
    
    

    @FXML
    void dologin(ActionEvent event) throws IOException, ParseException 
    {
    	
//			java.util.Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(currentDate);
//			java.util.Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(Date); 
//
//    	 
//    	if(date1.after(date2)&&date2.before(date1))
    //{
    	if(txtid.getText().equals("admin")&&txtpass.getText().equals("0000"))
    	{
    	    System.out.println(""+currentDate);
    	System.out.println("Succesfully Login");
    
    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dashboard/DashboardView.fxml")); 
		Scene scene = new Scene(root);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		Scene scene1=(Scene)btnlogin.getScene();
		   scene1.getWindow().hide();
    	}
    	
    	else	
    	System.err.println("\n   Enter Correct Login Details !!  \n");
    }
//    else
//    {
//    	System.err.println("Renew your account");
//    }
    //}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {
    }
}
