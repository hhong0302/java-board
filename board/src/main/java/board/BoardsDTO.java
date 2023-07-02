package board;

import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BoardsDTO {
	
	private int num;
	private String userid;
	private String username;
	private String wdate;
	private String uip;
	private int count;
	private String title;
	private String content;
	private String bbstitle;
	private String comment;
	private int ischecked;
	private int views;
	
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getIschecked() {
		return ischecked;
	}
	public void setIschecked(int ischecked) {
		this.ischecked = ischecked;
	}
	public String getBbstitle() {
		return bbstitle;
	}
	public void setBbstitle(String bbstitle) {
		this.bbstitle = bbstitle;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
