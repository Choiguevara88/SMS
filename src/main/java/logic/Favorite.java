package logic;

public class Favorite {
	private int sNo;
	private String id;
	
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Favorite [sNo=" + sNo + ", id=" + id + "]";
	}
}
