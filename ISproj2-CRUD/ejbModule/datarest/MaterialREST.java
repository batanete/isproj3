package datarest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;


@XmlAccessorType(XmlAccessType.FIELD)
public class MaterialREST {
	@XmlAttribute
	int id;
	
	private String title;
	private String location;
	
	public MaterialREST() {}
	
	public MaterialREST(int id, String name, String location2) {
		this.id = id;
		this.title = name;
		this.location = location2;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public void update(String name, String location2) {
		this.title = name;
		this.location = location2;
	}

}
