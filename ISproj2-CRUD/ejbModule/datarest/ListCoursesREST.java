package datarest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="ListCourses")
public class ListCoursesREST {
	private List<CourseREST> courses;


	public ListCoursesREST() {
		this.courses = new ArrayList<>();
	}
	
	public List<CourseREST> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseREST> courses) {
		this.courses = courses;
	}
	
	public void addCourse(CourseREST c) {
		this.courses.add(c);
	}

	

}
