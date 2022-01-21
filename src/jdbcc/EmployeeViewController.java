/**
 * Sample Skeleton for 'EmployeeView.fxml' Controller Class
 */

package jdbcc;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;



public class EmployeeViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // fx:id="txtimg"
    private TextField txtimg; // Value injected by FXMLLoader
    
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="setimg"
    private ImageView setimg; // Value injected by FXMLLoader

    
    @FXML
    private DatePicker txtdate;
    
    @FXML // fx:id="comboId"
    private ComboBox<String> comboId; // Value injected by FXMLLoader

    @FXML // fx:id="txtName"
    private TextField txtName; // Value injected by FXMLLoader

    @FXML // fx:id="txtSal"
    private TextField txtSal; // Value injected by FXMLLoader

    @FXML // fx:id="imagebutton"
    private Button imagebutton; // Value injected by FXMLLoader

    
    @FXML
    void showdate(ActionEvent event) 
    {
    	
    }
    
    
    File fil;
   
    @FXML
    void dopic(ActionEvent event) throws FileNotFoundException 
    {
    	JFileChooser filechoose = new JFileChooser();
    	int res=filechoose.showOpenDialog(null);
    	if(res==0)
    	{
    		fil=new File(filechoose.getSelectedFile().getAbsolutePath());
    		
    		InputStream stream = new FileInputStream(fil);          	   	
    	    Image image = new Image(stream);
    	     setimg.setImage(image);
    	     txtimg.setText(filechoose.getSelectedFile().getAbsolutePath());
        }
    }
    
    

  
    @FXML
    void doDelete(ActionEvent event) 
    {

    	try
    	{
			pst=con.prepareStatement("delete from office where empid=?");
			
			pst.setInt(1,Integer.parseInt(comboId.getEditor().getText()));
			int c=pst.executeUpdate();
			if(c>0)
			System.out.println("Deleted");
			else
				System.out.println("Not Deleted");
		}
    	
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    }
    
    @FXML
    void doshow(ActionEvent event) throws FileNotFoundException 
    {
    	
    	try
    	{		
    	pst=con.prepareStatement("select * from office where empid=?");
		
			pst.setInt(1,Integer.parseInt(comboId.getEditor().getText()));
			ResultSet table=pst.executeQuery();
			
			while(table.next())
			{
				int empid=table.getInt("empid");
			String name=table.getString("empname");
			float sal=table.getFloat("salary");
			java.sql.Date doj=table.getDate("doj");
			String image=table.getString("imgpath");
			
			System.out.println(empid+"   "+name+"   "+sal+" "+doj+"    "+image);
			txtName.setText(name);
			txtSal.setText(String.valueOf(sal));
			txtdate.setValue(doj.toLocalDate()); 
			txtimg.setText(image);
			
			InputStream stream = new FileInputStream(image);          	   	
    	    Image imag = new Image(stream);
    	     setimg.setImage(imag);
    	     
			
			}
	
		
		}
    	
    	catch (SQLException e) 
    	{
    		e.printStackTrace();
		}


    }

    @FXML
    void doshowall(ActionEvent event)
    {
    	
    	try
    	{
			
    		
    		pst=con.prepareStatement("select * from office ");
		
			ResultSet table=pst.executeQuery();
			
			while(table.next())
			{
				int empid=table.getInt("empid");
			String name=table.getString("empname");
			float sal=table.getFloat("salary");
			java.sql.Date doj=table.getDate("doj");
			String image=table.getString("imgpath");
			
			System.out.println(empid+"   "+name+"   "+sal+" "+doj+"    "+image);
			
			}
				
		}
    	
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

    
    PreparedStatement pst;
    @FXML
    void doSave(ActionEvent event) 
    {
    	try
    	{
    	
			pst=con.prepareStatement("insert into office values(?,?,?,?,?)");
			pst.setInt(1,Integer.parseInt(comboId.getEditor().getText()));
			pst.setString(2, txtName.getText());
			
			pst.setFloat(3,Float.parseFloat(txtSal.getText()));		
			
			LocalDate date = txtdate.getValue();//2021-08-10
			
			pst.setDate(4,java.sql.Date.valueOf(date));
			pst.setString(5, txtimg.getText());
			
			pst.executeUpdate();
			System.out.println("Saved");
			
		}
    	
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doUpdate(ActionEvent event) 
    {
    	
    	try
    	{
			pst=con.prepareStatement("update office set empname=?,salary=?,doj=?,imgpath=? where empid=?");
			
			pst.setString(1, txtName.getText());
			pst.setFloat(2,Float.parseFloat(txtSal.getText()));

			LocalDate date = txtdate.getValue();//2021-08-10
			
			pst.setDate(3,java.sql.Date.valueOf(date));
			pst.setString(4, txtimg.getText());
			
			pst.setInt(5,Integer.parseInt(comboId.getEditor().getText()));
			
			int c=pst.executeUpdate();
			
			if(c>0)
			System.out.println("Updated");
			else
				System.out.println("Not Updated");
		}
    	
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    }
    
    void dofill()
    {
    	try{
    		pst=con.prepareStatement("select distinct empid from office");
    	ResultSet table=pst.executeQuery();
    	while(table.next())
    		{
    		int empid=table.getInt("empid");
    		comboId.getItems().add(String.valueOf(empid));
    		System.out.println(empid);
    		
    		}
    	}
    	catch(Exception e)
    	{ 
    		e.printStackTrace();
    	}
    }
Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
      con=DataBaseConnector.getConnection();  
      dofill();
    }
}
