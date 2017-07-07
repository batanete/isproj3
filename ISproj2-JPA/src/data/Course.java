package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Course
 *
 */
@Entity
@Table(name="Course")
@NamedQueries({
    @NamedQuery(name="Course.findAll",
                query="SELECT c FROM Course c"),
    @NamedQuery(name="Course.findByName",
                query="SELECT c FROM Course c WHERE c.name = :c"),
}) 
public class Course implements Serializable {
	
	private static final long serialVersionUID = 1L;
   
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="name",unique=true)
	private String name;
	
	@OneToMany(mappedBy="course",cascade=CascadeType.REMOVE)
	private List<Material> materials;
	
	@ManyToOne
	private Professor professor;
	
	

	

	@ManyToMany
	private List<Student> students;
	
	public Course() {
		super();
	}
	
	public Course(String name,Professor professor){
		super();
		this.name=name;
		this.professor=professor;
		this.professor.getCourses().add(this);
		this.students=new ArrayList<Student>();
	}
	
	public Course(String name,Professor professor, ArrayList<Student> students){
		super();
		this.name=name;
		this.professor=professor;
		this.professor.getCourses().add(this);
		this.students=students;
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

	public void setCourseName(String name) {
		this.name = name;
	}
	
	public void addStudent(Student s){
		this.students.add(s);
	}

	public void addMaterial(Material m){
		this.materials.add(m);
	}
	
	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public Professor getProfessor() {
		return professor;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", materials=" + materials + ", professor=" + professor
				+ ", students=" + students + "]";
	}

	public void setProfessor(Professor p) {
		this.professor=p;
		
	}
}
