package customerdisplay;

public class CustomersBean {
	String mobile;
	String name;
	String address;
	String area;
	String hawker;
	String spapers;
	String tprice;
	String dos;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getHawker() {
		return hawker;
	}
	public void setHawker(String hawker) {
		this.hawker = hawker;
	}
	public String getSpapers() {
		return spapers;
	}
	public void setSpapers(String spapers) {
		this.spapers = spapers;
	}
	public String getTprice() {
		return tprice;
	}
	public void setTprice(String tprice) {
		this.tprice = tprice;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public CustomersBean(String mobile, String name, String address, String area, String hawker, String spapers,
			String tprice, String dos) {
		super();
		this.mobile = mobile;
		this.name = name;
		this.address = address;
		this.area = area;
		this.hawker = hawker;
		this.spapers = spapers;
		this.tprice = tprice;
		this.dos = dos;
	}
	
	
	
}
