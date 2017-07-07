package data;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Material
 *
 */

@Entity
@NamedQueries({
    @NamedQuery(name="Material.findAll",
                query="SELECT m FROM Material m"),
    @NamedQuery(name="Material.getNames",
    	query="SELECT m.name FROM Material m"),
    @NamedQuery(name="Material.findByName",
                query="SELECT m FROM Material m WHERE m.name = :m"),
}) 
public class Material implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Material() {
		super();
	}
	
	
	public Material(String name,String location, Course course){
		super();
		this.name=name;
		this.location=location;
		this.course=course;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String location;
	
	@ManyToOne
	private Course course;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Override
	public String toString() {
		return name+"("+course.getName()+")";
	}
}
