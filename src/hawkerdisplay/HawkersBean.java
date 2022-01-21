package hawkerdisplay;

public class HawkersBean {
    String name;
	String contact;
	String address;
	String adhaar;
	String areas_assigned;
	String doj;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAdhaar() {
		return adhaar;
	}
	public void setAdhaar(String adhaar) {
		this.adhaar = adhaar;
	}
	public String getAreas_assigned() {
		return areas_assigned;
	}
	public void setAreas_assigned(String areas_assigned) {
		this.areas_assigned = areas_assigned;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	
	public HawkersBean(String name, String contact, String address, String adhaar, String areas_assigned,
			String doj) {
		super();
		this.name = name;
		this.contact = contact;
		this.address = address;
		this.adhaar = adhaar;
		this.areas_assigned = areas_assigned;
		this.doj = doj;
	}
	
}
