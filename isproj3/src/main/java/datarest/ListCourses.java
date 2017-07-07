package datarest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="ListCourses")
public class ListCourses {
	private List<Course> courses;


	public ListCourses() {
		this.courses = new ArrayList<>();
	}
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course c) {
		this.courses.add(c);
	}

	

}
