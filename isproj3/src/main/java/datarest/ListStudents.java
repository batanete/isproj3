package datarest;

import java.util.ArrayList;
import java.util.List;

public class ListStudents {
	private List<Student> students;
	
	
	public ListStudents(){
		this.students=new ArrayList<>();
	}
	
	public void addStudent(Student st){
		this.students.add(st);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	
}
