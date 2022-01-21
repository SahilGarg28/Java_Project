/**
 * Sample Skeleton for 'CustomerDisplay.fxml' Controller Class
 */

package customerdisplay;

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

import coustmerEntry.DataBaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerDisplayController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tableview"
    private TableView<CustomersBean> tableview; // Value injected by FXMLLoader

    @FXML // fx:id="comboarea"
    private ComboBox<String> comboarea; // Value injected by FXMLLoader

    @FXML // fx:id="combopaper"
    private ComboBox<String> combopaper; // Value injected by FXMLLoader

    @FXML
    void doshow(ActionEvent event) {
    	
   	 list=FXCollections.observableArrayList();//creation of object
   	try{
   		pst=con.prepareStatement("select * from customers");
   	ResultSet table=pst.executeQuery();
 	while(table.next())
   	{
   		String mobile=table.getString("mobile");
   		String name=table.getString("name");
   		String address=table.getString("address");
   		String area=table.getString("area");
   		String hawker=table.getString("hawker");
   		String spapers=table.getString("spapers");
   		String tprice=table.getString("tprice");
   		Date dos=table.getDate("dos");
   		
   	
   		CustomersBean obj=new CustomersBean(mobile,name,address,area,hawker,spapers,tprice, dos.toString());
   		
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
    void doshowar(ActionEvent event) {
    	
   	 list=FXCollections.observableArrayList();//creation of object
   	try{
   		pst=con.prepareStatement("select * from customers where area=?");
   		pst.setString(1,comboarea.getSelectionModel().getSelectedItem());
   	ResultSet table=pst.executeQuery();
 	while(table.next())
   	{
   		String mobile=table.getString("mobile");
   		String name=table.getString("name");
   		String address=table.getString("address");
   		String area=table.getString("area");
   		String hawker=table.getString("hawker");
   		String spapers=table.getString("spapers");
   		String tprice=table.getString("tprice");
   		Date dos=table.getDate("dos");
   		
   	
   		CustomersBean obj=new CustomersBean(mobile,name,address,area,hawker,spapers,tprice, dos.toString());
   		
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
    void doshowpapr(ActionEvent event) {
    	
   	 list=FXCollections.observableArrayList();//creation of object
   	try{
   		pst=con.prepareStatement("select * from customers where spapers like ?");
   		pst.setString(1,"%"+combopaper.getSelectionModel().getSelectedItem()+"%");
   	ResultSet table=pst.executeQuery();
   	while(table.next())
   	{
   		String mobile=table.getString("mobile");
   		String name=table.getString("name");
   		String address=table.getString("address");
   		String area=table.getString("area");
   		String hawker=table.getString("hawker");
   		String spapers=table.getString("spapers");
   		String tprice=table.getString("tprice");
   		Date dos=table.getDate("dos");
   		
   	
   		CustomersBean obj=new CustomersBean(mobile,name,address,area,hawker,spapers,tprice, dos.toString());
   		
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
         	File file = new File("CustomersList.csv");
             writer = new BufferedWriter(new FileWriter(file));
             String text="Mobile,Name,Address,Area,Hawker Available,Selected Paper,Total Price, Date of Start\n";
             writer.write(text);
             for (CustomersBean p : list)
             {
                 text = p.getMobile()+ "," + p.getName()+ "," + p.getAddress()+ "," + p.getArea()+"," + p.getHawker()+ "," + p.getSpapers()+ "," + p.getTprice()+ "," + p.getDos()+"\n";
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
    
    
    //------------------------------------------------------------------------------------------------------
    
    
    @SuppressWarnings("unchecked")
   	void addCols()
       {  
    	
          	TableColumn<CustomersBean, String> mobileCol=new TableColumn<CustomersBean, String>("Mobile");
          	mobileCol.setCellValueFactory(new PropertyValueFactory<CustomersBean, String>("mobile"));//field name in bean
          	mobileCol.setMinWidth(100);
          	
          	TableColumn<CustomersBean, String> nameCol=new TableColumn<CustomersBean, String>("Name");
          	nameCol.setCellValueFactory(new PropertyValueFactory<CustomersBean, String>("name"));//field name in bean
          	nameCol.setMinWidth(100);
          	
          	TableColumn<CustomersBean, String>addressCol=new TableColumn<CustomersBean, String>("Adress");
          	addressCol.setCellValueFactory(new PropertyValueFactory<CustomersBean, String>("address"));//field name in bean
          	addressCol.setMinWidth(100);
          	
          	TableColumn<CustomersBean, String> areaCol=new TableColumn<CustomersBean, String>("Area");
          	areaCol.setCellValueFactory(new PropertyValueFactory<CustomersBean, String>("area"));//field name in bean
          	areaCol.setMinWidth(100);
          	
          	TableColumn<CustomersBean, String> hawkerCol=new TableColumn<CustomersBean, String>("Hawker Selected");
          	hawkerCol.setCellValueFactory(new PropertyValueFactory<CustomersBean, String>("hawker"));//field name in bean
          	hawkerCol.setMinWidth(100);
          	
          	TableColumn<CustomersBean, String> spaperCol=new TableColumn<CustomersBean, String>("Selected Paper");
          	spaperCol.setCellValueFactory(new PropertyValueFactory<CustomersBean, String>("spapers"));//field name in bean
          	spaperCol.setMinWidth(100);
          	
          	TableColumn<CustomersBean, String> tpriceCol=new TableColumn<CustomersBean, String>("Total Price");
          	tpriceCol.setCellValueFactory(new PropertyValueFactory<CustomersBean, String>("tprice"));//field name in bean
          	tpriceCol.setMinWidth(100);
       	
          
          	TableColumn<CustomersBean, String> dosCol=new TableColumn<CustomersBean, String>("Date of Starting");
          	dosCol.setCellValueFactory(new PropertyValueFactory<CustomersBean, String>("dos"));//field name in bean
          	dosCol.setMinWidth(100);
       	
          	tableview.getColumns().addAll(mobileCol,nameCol,addressCol,areaCol,hawkerCol,spaperCol,tpriceCol,dosCol);
       }
    
    PreparedStatement pst;
    Connection con;
    ObservableList<CustomersBean> list;
    
    
    void dofill()
    {
    	 try
 	  	{
 				
 	  		
 	  		pst=con.prepareStatement("select distinct area from customers ");
 			
 				ResultSet table=pst.executeQuery();
 				comboarea.getItems().add("Select");
 				combopaper.getItems().add("Select");
 				while(table.next())
 				{
 					
 				String ar=table.getString("area");
 				comboarea.getItems().add(ar);
 				
 				}
 				  		
  	  		pst=con.prepareStatement("select paper from newspaper ");
  			
  				table=pst.executeQuery();
  				while(table.next())
  				{
  				String papr=table.getString("paper");
  				combopaper.getItems().add(papr); 							
  				}
 	  	}
    	 catch(Exception exp)
      	{ 
      		exp.printStackTrace();
      	}
 }
    
    //--------------------------------------------------------------------------------------------------------
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    	con=DataBaseConnector.getConnection();
   	 	addCols();
  	    dofill();
  	}
   
   


    }

