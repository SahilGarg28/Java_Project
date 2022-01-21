/**
 * Sample Skeleton for 'HawkerControlPannelView.fxml' Controller Class
 */

package hawkerControlPannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HawkerControlPannelViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboname"
    private ComboBox<String> comboname; // Value injected by FXMLLoader

    @FXML // fx:id="txtcontact"
    private TextField txtcontact; // Value injected by FXMLLoader

    @FXML // fx:id="imgview"
    private ImageView imgview; // Value injected by FXMLLoader

    @FXML // fx:id="txtaddress"
    private TextArea txtaddress; // Value injected by FXMLLoader

    @FXML // fx:id="txtaadhaar"
    private TextArea txtaadhaar; // Value injected by FXMLLoader

    @FXML // fx:id="comboareaavailable"
    private ComboBox<String> comboareaavailable; // Value injected by FXMLLoader

    @FXML // fx:id="txtdoj"
    private DatePicker txtdoj; // Value injected by FXMLLoader

    @FXML // fx:id="txtareaassigned"
    private TextArea txtareaassigned; // Value injected by FXMLLoader
    PreparedStatement pst;
    @FXML
    void doclear(ActionEvent event) {
    	comboname.getEditor().clear();
    	txtcontact.setText("");
    	txtaddress.setText("");
    	txtaadhaar.setText("");
    	txtareaassigned.setText("");
    	ar="";
    	txtdoj.getEditor().clear();
    	comboareaavailable.getEditor().clear();
    	fil=new File("C:\\Users\\Sahil\\eclipse-workspace\\MarksCard\\src\\hawkerControlPannel\\person-placeholder-portrait.png");
		InputStream stream;
		try {
			stream = new FileInputStream(fil);
    	    Image image = new Image(stream);
    	     imgview.setImage(image);
 			comboname.getEditor().clear();
 			dofillname();
    	     
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
    	
    }
    String ar="";
    @FXML
    void docomboareaavailable(ActionEvent event) {
    	ar=ar+comboareaavailable.getSelectionModel().getSelectedItem()+" , ";
        ar=ar.substring(0,ar.length()-2);
   	 txtareaassigned.setText(ar);
   	 ar+=" / ";
    }

    @FXML
    void docomboname(ActionEvent event) {
    	try {
			pst=con.prepareStatement("select * from hawkers where name=?");
			pst.setString(1,comboname.getEditor().getText());
			ResultSet table=pst.executeQuery();
			while(table.next()) {
				String name=table.getString("name");
				String contact=table.getString("contact");
				String picpath=table.getString("picpath");
				String address=table.getString("address");
				String adhaar=table.getString("adhaar");
				java.sql.Date doj=table.getDate("doj");
				String areas_assigned=table.getString("areas_assigned");
				
				comboname.setValue(name);
				txtcontact.setText(contact);
				txtaddress.setText(address);
				txtaadhaar.setText(adhaar);
				txtdoj.setValue(doj.toLocalDate());
				txtareaassigned.setText(areas_assigned);
				fil=new File(picpath);
				InputStream stream;
				try {
					stream = new FileInputStream(fil);
		    	    Image image = new Image(stream);
		    	     imgview.setImage(image);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}          	   	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    File fil;
    JFileChooser filechoose;
    @FXML
    void doimgview(MouseEvent event) {
    	filechoose = new JFileChooser();
    	filechoose.showOpenDialog(null);
    	//if(res==0)
    	{	
    		System.out.println("*******");
    		fil=new File(filechoose.getSelectedFile().getAbsolutePath());
    		
    		InputStream stream;
			try {
				stream = new FileInputStream(fil);
	    	    Image image = new Image(stream);
	    	     imgview.setImage(image);
	    	     System.out.println("**********************\n************************");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}          	   	

        }
    }

    @FXML
    void doleft(ActionEvent event) {
    	try
    	{
			pst=con.prepareStatement("delete from hawkers where name=?");
			
			pst.setString(1,comboname.getEditor().getText());
			pst.executeUpdate();
			doclear(null);
			showmsgdeleted();
			dofillname();
		}
    	
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }
    

    @FXML
    void domodify(ActionEvent event) {
    	try {
			pst=con.prepareStatement("update hawkers set contact=?,picpath=?,address=?,adhaar=?,areas_assigned=?,doj=? where name=?");
			
            pst.setString(7, comboname.getEditor().getText());
			
			pst.setString(1, txtcontact.getText());
			
			System.out.println(fil.getAbsolutePath());
			
			pst.setString(2, fil.getAbsolutePath());
			pst.setString(3, txtaddress.getText());
			pst.setString(4, txtaadhaar.getText());
			pst.setString(5, txtareaassigned.getText());
			LocalDate date = txtdoj.getValue();
			pst.setDate(6,java.sql.Date.valueOf(date));
			pst.executeUpdate();
			
			showmsgupdated();
			dofillname();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doregister(ActionEvent event) {
    	try
    	{
    	
			pst=con.prepareStatement("insert into hawkers values(?,?,?,?,?,?,?)");
			pst.setString(1,comboname.getEditor().getText());
			pst.setString(2, txtcontact.getText());
			pst.setString(3, filechoose.getSelectedFile().getPath());
			pst.setString(4, txtaddress.getText());
			pst.setString(5, txtaadhaar.getText());
			pst.setString(6, txtareaassigned.getText());
					
			LocalDate date = txtdoj.getValue();
			pst.setDate(7,java.sql.Date.valueOf(date));
			
			pst.executeUpdate();
			showmsgsaved();	
			dofillname();
    	}
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    void dofillname()
    {
    	try {
    		comboname.getItems().clear();
			pst=con.prepareStatement("select distinct name from hawkers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String name=table.getString("name");
				comboname.getItems().add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void dofillarea()
    {
    	try {
    		comboname.getEditor().clear();
			pst=con.prepareStatement("select distinct area from areas");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String name=table.getString("area");
				comboareaavailable.getItems().add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void showmsgupdated()
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("Hawker's data successfully Modified");
    			alert.show();
    }
    void showmsgsaved()
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("New Hawker successfully Registered");
    			alert.show();
    }
    void showmsgdeleted()
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("Hawker successfully Left");
    			alert.show();
    }

    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=DataBaseConnector.getConnection();
    	dofillname();
    	dofillarea();

    }
}
