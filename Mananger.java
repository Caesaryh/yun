package 职工信息管理;

public class Mananger {
	private int id;
	private String pwd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Mananger(int id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	

}
