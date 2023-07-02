package board;

import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MembersDTO {
	
	private int num;
	private String userid;
	private String userpass;
	private String username;
	private String useremail;
	private int postcode;
	private String addr;
	private String detailaddr;
	private String tel;
	private int level;
	private String uip;
	private String wdate;
	private String photo;
	
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDetailaddr() {
		return detailaddr;
	}
	public void setDetailaddr(String detailaddr) {
		this.detailaddr = detailaddr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getUip() {
		return uip;
	}
	public void setUip() {
		String uip = null;
		try
		{
			uip = Inet4Address.getLocalHost().getHostAddress();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		this.uip = uip;
	}
	public void setUip(String uip){
		this.uip = uip;
	}
	public String getWdate() {
		return wdate;
	}
	
	public void setWdate()
	{
		Date today = new Date();
		Locale currentLocale = new Locale("KOREAN", "KOREA");
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
		
		this.wdate = formatter.format(today);
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

}
