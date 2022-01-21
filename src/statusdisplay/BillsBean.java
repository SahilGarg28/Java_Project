package statusdisplay;

public class BillsBean {
	String mobile;
	String dos;
	String dateupto;
	Float amount;
	int status;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getDateupto() {
		return dateupto;
	}
	public void setDateupto(String dateupto) {
		this.dateupto = dateupto;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public BillsBean(String mobile, String dos, String dateupto, Float amount, int status) {
		super();
		this.mobile = mobile;
		this.dos = dos;
		this.dateupto = dateupto;
		this.amount = amount;
		this.status = status;
	}
	
}
