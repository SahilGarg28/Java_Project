/**
 * Sample Skeleton for 'CoustmerEntryView.fxml' Controller Class
 */

package coustmerEntry;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class CoustmerEntryViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtname"
    private TextField txtname; // Value injected by FXMLLoader

    @FXML // fx:id="txtaddress"
    private TextArea txtaddress; // Value injected by FXMLLoader

    @FXML // fx:id="comboarea"
    private ComboBox<String> comboarea; // Value injected by FXMLLoader

    @FXML // fx:id="combohawkers"
    private ComboBox<String> combohawkers; // Value injected by FXMLLoader

    @FXML // fx:id="listpaperavailable"
    private ListView<String> listpaperavailable; // Value injected by FXMLLoader

    @FXML // fx:id="listprices"
    private ListView<String> listprices; // Value injected by FXMLLoader

    @FXML // fx:id="listselectedpapers"
    private ListView<String> listselectedpapers; // Value injected by FXMLLoader

    @FXML // fx:id="txtdos"
    private DatePicker txtdos; // Value injected by FXMLLoader

    @FXML // fx:id="txttotalprice"
    private TextField txttotalprice; // Value injected by FXMLLoader

    @FXML // fx:id="txtmob"
    private TextField txtmob; // Value injected by FXMLLoader

    PreparedStatement pst;
    @FXML
    void docomboarea(ActionEvent event) {
    	
    	String ar=comboarea.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("select name from hawkers where areas_assigned LIKE '%"+ar+"%'");
			ResultSet table= pst.executeQuery();
			while(table.next())
			{
				String name=table.getString("name");
				combohawkers.getItems().clear();
				combohawkers.getItems().add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void docombohawkers(ActionEvent event) {
    	
    }
    String papers1;
    String papers1minus;

    @FXML
    void dofetch(ActionEvent event) {
    	try {
			pst=con.prepareStatement("select * from customers where mobile=?");
			pst.setString(1, txtmob.getText());
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String mobile=table.getString("mobile");
				String name=table.getString("name");
				String address=table.getString("address");
				String area=table.getString("area");
				String hawker=table.getString("hawker");
				String spaper=table.getString("spapers");
				String tprice=table.getString("tprice");
				java.sql.Date dos=table.getDate("dos");
				papers1=spaper;
				txtmob.setText(mobile);
				txtname.setText(name);
				txtaddress.setText(address);
				comboarea.setValue(area);
				combohawkers.setValue(hawker);
				txttotalprice.setText(tprice);
				txtdos.setValue(dos.toLocalDate());
				String sp[]=spaper.split(",");
				for(String obj:sp)
				{
					listselectedpapers.getItems().add(obj);
				}
				ar=spaper;
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void domodify(ActionEvent event) {
    	try {
			pst=con.prepareStatement("update customers set name=?,address=?,area=?,hawker=?,spapers=?,tprice=?,dos=? where mobile=?");
			pst.setString(8, txtmob.getText());
			pst.setString(1,txtname.getText());
			pst.setString(2, txtaddress.getText());
			pst.setString(3, comboarea.getSelectionModel().getSelectedItem());
			pst.setString(4, combohawkers.getSelectionModel().getSelectedItem());
			pst.setString(5, ar);
			pst.setString(6, txttotalprice.getText());
			LocalDate date = txtdos.getValue();
			pst.setDate(7,java.sql.Date.valueOf(date));
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    double pr;
    double pr1;
    double prminus=0;
    double pr1minus;
    double pricefinal;
    int n=1;
   
    String papers=papers1+"";
    String paperminus=papers1minus+"";
    ArrayList <String> list = new ArrayList<String>();
    ArrayList<String> listpaper=new ArrayList<String>();
	
    @FXML
    void dopapersavailable(MouseEvent event) {
    	String.format("%.2f", pr);
    	String.format("%.2f", pr1);
    	String.format("%.2f", prminus);
    	String.format("%.2f", pr1minus);
    	String.format("%.2f", pricefinal);

//    	if(event.getClickCount()==1)
//    	{
//    		paperminus=listpaperavailable.getSelectionModel().getSelectedItems();
//    		listselectedpapers.getItems().remove(paperminus);
//    		int index=listpaperavailable.getSelectionModel().getSelectedIndex();
//    		
//    		prminus=Float.valueOf(listprices.getItems().get(index));
//    		prminus=prminus+pr1minus;
//    		pr1minus=prminus;
//    		listprices.getSelectionModel().select(index);
//    		txttotalprice.setText(pr-prminus+"");
    	if(event.getClickCount()==1)
    		
    	{
    			
    		papers=listpaperavailable.getSelectionModel().getSelectedItem();
    		papers1=papers;
    		listselectedpapers.getItems().add(papers);
    		int index=listpaperavailable.getSelectionModel().getSelectedIndex();
    		
    		pr=Double.valueOf(listprices.getItems().get(index));
    		list.add(pr+"");
    		pricefinal=pr+pricefinal-prminus;
    		pr1=pricefinal;
    		prminus=0;
    		listprices.getSelectionModel().select(index);
    		if(pricefinal<0)
    		{
    			pricefinal=0;
    		}
    		txttotalprice.setText(pricefinal+"");
    		selectpapers();
    		
    	}
//    	if(event.getClickCount()==1)
//		{	prminus=0;
//    		list.add(papers);
//			paperminus=listpaperavailable.getSelectionModel().getSelectedItem();
//			for(String element:list) 
//			{
//				if(element.contains(paperminus))
//				{
//			n=listselectedpapers.getItems().size();
//			listselectedpapers.getItems().removeAll(paperminus);
//			int indexm=listpaperavailable.getSelectionModel().getSelectedIndex();
//			prminus=Float.valueOf(listprices.getItems().get(indexm));
//			prminus=prminus+pr1minus;
//			pr1minus=prminus;
////			pricefinal=pr-n*prminus;
////			listprices.getSelectionModel().select(indexm);
////			txttotalprice.setText(pricefinal+"");
//			selectpapers();
//				}
//			}
//		}
//    		price();
//    		
//    	}
//    void price()
//    {
//    	pricefinal=-pricefinal-n*prminus;
//    	txttotalprice.setText(pricefinal+"");
    }
    @FXML
    void doselectedpapers(MouseEvent event) {
    	String.format("%.2f", pr);
    	String.format("%.2f", pr1);
    	String.format("%.2f", prminus);
    	String.format("%.2f", pr1minus);
    	String.format("%.2f", pricefinal);
    	
    	if(event.getClickCount()==1)
    		
    	{
    		
    		listpaper.add(papers);	
    		paperminus=listselectedpapers.getSelectionModel().getSelectedItem();
    		n=listselectedpapers.getItems().size();
    		listselectedpapers.getItems().remove(paperminus);
    		int indexm=listselectedpapers.getSelectionModel().getSelectedIndex();
    		prminus=Double.valueOf(list.get(indexm+1));
    		//prminus=prminus;
    		pr1minus=prminus;
    		//list.get(indexm+1);
    		list.remove(indexm+"");
    		double totprice;

    		totprice=pricefinal-prminus;	
    		prminus=0;
    		pricefinal=totprice;
    		txttotalprice.setText(totprice+"");
    		selectpapers();
    		
    		double p=Double.parseDouble(txttotalprice.getText());
    		pricefinal=p;
    		String.format("%.2f", p);
    		String.format("%.2f",totprice);
    		
    	}
    }
    @FXML
    void doremove(ActionEvent event) {
    	try
    	{
			pst=con.prepareStatement("delete from customers where mobile=?");
			
			pst.setString(1,txtmob.getText());
			pst.executeUpdate();
			txtmob.setText("");
			txtname.setText("");
			txtaddress.setText("");
			comboarea.getItems().clear();
			dofillareas();
			combohawkers.getItems().clear();
			listselectedpapers.getItems().clear();
			txtdos.getEditor().clear();
			txttotalprice.setText("");
			showmsgdeleted();
		}
    	
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void dosave(ActionEvent event) {
    	try {
			pst=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?)");
			pst.setString(1, txtmob.getText());
			pst.setString(2, txtname.getText());
			pst.setString(3, txtaddress.getText());
			pst.setString(4,comboarea.getSelectionModel().getSelectedItem());
			pst.setString(5,combohawkers.getSelectionModel().getSelectedItem());
			pst.setString(7, txttotalprice.getText());
			LocalDate date = txtdos.getValue();
			pst.setDate(8,java.sql.Date.valueOf(date));
			pst.setString(6,ar);
			pst.executeUpdate();
			System.out.println("*********************");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    String ar=" ";
    void selectpapers() {
		
		ar=ar+papers+" , ";
        ar=ar.substring(0,ar.length()-2);
   	 	ar+=" / ";
    }
    void dofillareas(){
    	try {
			pst=con.prepareStatement("select distinct area from areas");
			ResultSet table=pst.executeQuery();
			while(table.next()) {
				String area=table.getString("area");
				comboarea.getItems().add(area);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void dofillpapersavailable() {
    	try {
			pst=con.prepareStatement("select * from newspaper");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String paper=table.getString("paper");
				listpaperavailable.getItems().add(paper);
				String price=table.getString("price");
				listprices.getItems().add(price);
				
				
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
        dofillareas();
        dofillpapersavailable();
        listpaperavailable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listselectedpapers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }
}
