package datarest;

import java.util.ArrayList;
import java.util.List;

public class ListStudentsREST {
	private List<StudentREST> students;
	
	
	public ListStudentsREST(){
		this.students=new ArrayList<>();
	}
	
	public void addStudent(StudentREST st){
		this.students.add(st);
	}

	public List<StudentREST> getStudents() {
		return students;
	}

	public void setStudents(List<StudentREST> students) {
		this.students = students;
	}
	
	
	
}
