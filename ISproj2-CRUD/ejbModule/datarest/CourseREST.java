package datarest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


@XmlAccessorType(XmlAccessType.FIELD)
public class CourseREST implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlAttribute
	private int id;
	private String name;
	@XmlElementWrapper(name="materials")
	@XmlElement(name="material")
	private List<MaterialREST> materials;

	public CourseREST() {
		super();
	}

	public CourseREST(int id, String name2) {
		this.name = name2;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void addMaterial(MaterialREST material) {
		if (this.materials == null)
			this.materials = new ArrayList<>();
		this.materials.add(material);
	}
	
	public void deleteMaterial(MaterialREST material) {
		if (this.materials == null)
			return; //XXX: silent error?
		this.materials.remove(material);
	}

	public List<MaterialREST> getMaterials() {
		return this.materials;
	}
   
}
