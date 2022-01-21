/**
 * Sample Skeleton for 'StatusDisplay.fxml' Controller Class
 */

package statusdisplay;

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
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import billcollection.DataBaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class StatusDisplayController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="radiopend"
    private RadioButton radiopend; // Value injected by FXMLLoader

    @FXML // fx:id="abc"
    private ToggleGroup abc; // Value injected by FXMLLoader

    @FXML // fx:id="radiodone"
    private RadioButton radiodone; // Value injected by FXMLLoader

    @FXML // fx:id="tblveiw"
    private TableView<BillsBean> tblveiw; // Value injected by FXMLLoader

    @FXML // fx:id="combocontact"
    private ComboBox<String> combocontact; // Value injected by FXMLLoader
    
    PreparedStatement pst;
    Connection con;
    ObservableList<BillsBean> bill;//creation of object
    
    @FXML
    void doexport(ActionEvent event){
    	Writer writer = null;
    	try {
        	File file = new File("StatusDisplay.csv");
            writer=new BufferedWriter(new FileWriter(file));
            String text="Mobile,Date of Start,Date Upto,Amount,Status\n";
            writer.write(text);
            for (BillsBean p : bill)
            {
                text = p.getMobile()+ "," + p.getDos()+ "," + p.getDateupto()+ "," + p.getAmount()+"," + p.getStatus()+"\n";
                writer.write(text);
                
            }
            Desktop desktop = Desktop.getDesktop(); 
            if(file.exists())
            {
            	desktop.open(file);
            }
            System.out.println("Exported Successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            try {
				writer.flush();
	             writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
    }

    @FXML
    void dohistory(ActionEvent event) throws SQLException {
    	
    	bill=FXCollections.observableArrayList();//creation of object
    	tblveiw.getColumns().clear();
   	    addCols();
   	 
    	 
    	pst=con.prepareStatement("select * from bills where mobile=? order by dos");
  		pst.setString(1,combocontact.getSelectionModel().getSelectedItem());
  		
  		ResultSet table=pst.executeQuery();
	
  		while(table.next())
  		{
  			String mobile=table.getString("mobile");
  			Date dos=table.getDate("dos");
  			Date dateupto=table.getDate("dateupto");
  			Float amount=table.getFloat("amount");
  			int status=table.getInt("status");
  			
  			BillsBean obj=new BillsBean (mobile,dos.toString(),dateupto.toString(),amount,status);
      		bill.add(obj);
  		}
  		tblveiw.setItems(bill); 
    	
    }
    void veiwdata(String x) throws SQLException
    {
    	pst=con.prepareStatement("select * from bills where status=? order by mobile");
  		pst.setString(1,x);
  		
  		ResultSet table=pst.executeQuery();
  		
      	  		while(table.next())
      	  		{
      	  			String mobile=table.getString("mobile");
      	  			Date dos=table.getDate("dos");
      	  			Date dateupto=table.getDate("dateupto");
      	  			Float amount=table.getFloat("amount");
      	  			int status=table.getInt("status");
      	  			
      	  			BillsBean obj=new BillsBean (mobile,dos.toString(),dateupto.toString(),amount,status);
      	      		bill.add(obj);
      	  		}
      		tblveiw.setItems(bill); 
		}
    @FXML
    void doshow(ActionEvent event) throws SQLException {
    	bill=FXCollections.observableArrayList();//creation of object
    	tblveiw.getColumns().clear();
    	 addCols();
    	if(radiopend.isSelected())
    	{	
    		veiwdata("0");
        }
                      	
    	if(radiodone.isSelected())
    	{
    		veiwdata("1");
    	}
    }
    
    
    @SuppressWarnings("unchecked")
   	void addCols()
       {  
     	   	
          	TableColumn<BillsBean, String> mobileCol=new TableColumn<BillsBean, String>("Mobile");
          	mobileCol.setCellValueFactory(new PropertyValueFactory<BillsBean, String>("mobile"));//field name in bean
          	mobileCol.setMinWidth(100);
          	
          	TableColumn<BillsBean, String> dosCol=new TableColumn<BillsBean, String>("Date of Start");
          	dosCol.setCellValueFactory(new PropertyValueFactory<BillsBean, String>("dos"));//field name in bean
          	dosCol.setMinWidth(100);
          	
          	TableColumn<BillsBean, String> doeCol=new TableColumn<BillsBean, String>("Date of End");
          	doeCol.setCellValueFactory(new PropertyValueFactory<BillsBean, String>("dateupto"));//field name in bean
          	doeCol.setMinWidth(100);
          	
          	TableColumn<BillsBean, Float> amountCol=new TableColumn<BillsBean, Float>("Amount");
          	amountCol.setCellValueFactory(new PropertyValueFactory<BillsBean, Float>("amount"));//field name in bean
          	amountCol.setMinWidth(100);
          	
          	TableColumn<BillsBean, Integer> StatusCol=new TableColumn<BillsBean,  Integer>("Status");
          	StatusCol.setCellValueFactory(new PropertyValueFactory<BillsBean,  Integer>("status"));//field name in bean
          	StatusCol.setMinWidth(100);

          	
          	
          	tblveiw.getColumns().addAll(mobileCol,dosCol,doeCol, amountCol,StatusCol);
       }
void dofill()
{
  	 try
  	 {
  			
      	 pst=con.prepareStatement("select distinct mobile from bills order by mobile ");
      	 ResultSet table=pst.executeQuery();
  		
  		while(table.next())
  		{	
  		String mobile=table.getString("mobile");
  		
  		combocontact.getItems().add(mobile);
  		}	
  	 }
      	catch (SQLException e) 
      	{
  	       // TODO Auto-generated catch block
  	       e.printStackTrace();
      	}
}
    
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        con=DataBaseConnector.getConnection();
   	 	dofill();

   }

}

