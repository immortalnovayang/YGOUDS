package data;

public class DeckData {

	private String TCname = "";
	private String ENname = "";
	private String JPname = "";
	private int count = 0;
	private String attribute = "";
	private String race = "";
	
	public DeckData()
	{
		
	}

	public String getTCname() {
		return TCname;
	}

	public void setTCname(String tCname) {
		TCname = tCname;
	}

	public String getENname() {
		return ENname;
	}

	public void setENname(String eNname) {
		ENname = eNname;
	}

	public String getJPname() {
		return JPname;
	}

	public void setJPname(String jPname) {
		JPname = jPname;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

}
