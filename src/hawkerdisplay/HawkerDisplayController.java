/**
 * Sample Skeleton for 'HawkerDisplay.fxml' Controller Class
 */

package hawkerdisplay;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

import hawkerControlPannel.DataBaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HawkerDisplayController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tableview"
    private TableView<HawkersBean> tableview; // Value injected by FXMLLoader

    PreparedStatement  pst;
    Connection con;
    ObservableList<HawkersBean> list;
    
    @FXML
    void doshow(ActionEvent event)
    {
    	 list=FXCollections.observableArrayList();//creation of object
     	try{
     		pst=con.prepareStatement("select * from hawkers");
     	ResultSet table=pst.executeQuery();
     	while(table.next())
     	{
     		String name=table.getString("name");
     		String contact=table.getString("contact");
     		String address=table.getString("address");
     		String adhaar=table.getString("adhaar");
     		String areas_assigned=table.getString("areas_assigned");
     		Date doj=table.getDate("doj");
     		
     	
     		HawkersBean obj=new HawkersBean(name, contact, address, adhaar, areas_assigned, doj.toString());
     		
     		list.add(obj);
     		
     	}
     	tableview.setItems(list);
     	}
     	catch(Exception exp)
     	{ 
     		exp.printStackTrace();
     	}
    }
    
    @FXML
    void doexport(ActionEvent event) throws IOException 
    {
    	Writer writer = null;
        try {
        	File file = new File("HawkersList.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Name, Contact, Address, Adhaar No., Area Assigned, Date of Joining\n";
            writer.write(text);
            for (HawkersBean p : list)
            {
                text = p.getName()+ "," + p.getContact()+ "," + p.getAddress()+ "," + p.getAdhaar()+"," + p.getAreas_assigned()+ "," + p.getDoj()+"\n";
                writer.write(text);
              
            }
            System.out.println("Exported Successfully");
            Desktop desktop=Desktop.getDesktop();
            if(file.exists())
            {
            	desktop.open(file);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    }
    
    
    @SuppressWarnings("unchecked")
	void addCols()
    {    	
    	TableColumn<HawkersBean, String> nameCol=new TableColumn<HawkersBean, String>("Name");
    	nameCol.setCellValueFactory(new PropertyValueFactory<HawkersBean, String>("name"));//field name in bean
    	nameCol.setMinWidth(100);
    	
       	TableColumn<HawkersBean, String> contactCol=new TableColumn<HawkersBean, String>("Contact");
       	contactCol.setCellValueFactory(new PropertyValueFactory<HawkersBean, String>("contact"));//field name in bean
       	contactCol.setMinWidth(100);
    	
       	TableColumn<HawkersBean, String> addressCol=new TableColumn<HawkersBean, String>("Address");
       	addressCol.setCellValueFactory(new PropertyValueFactory<HawkersBean, String>("address"));//field name in bean
       	addressCol.setMinWidth(100);
    	
       	TableColumn<HawkersBean, String> adhaarCol=new TableColumn<HawkersBean, String>("Adhaar No.");
       	adhaarCol.setCellValueFactory(new PropertyValueFactory<HawkersBean, String>("adhaar"));//field name in bean
       	adhaarCol.setMinWidth(100);
    	
       	TableColumn<HawkersBean, String> area_assignedCol=new TableColumn<HawkersBean, String>("Area Assigned");
       	area_assignedCol.setCellValueFactory(new PropertyValueFactory<HawkersBean, String>("areas_assigned"));//field name in bean
       	area_assignedCol.setMinWidth(100);
    	
       	TableColumn<HawkersBean, String> dojCol=new TableColumn<HawkersBean, String>("Date of Joining");
       	dojCol.setCellValueFactory(new PropertyValueFactory<HawkersBean, String>("doj"));//field name in bean
       	dojCol.setMinWidth(100);
    	
    	
    	tableview.getColumns().addAll(nameCol,contactCol,addressCol,adhaarCol,area_assignedCol,dojCol);
    }
    
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	
   	 con=DataBaseConnector.getConnection();
	  addCols();

    }
}
