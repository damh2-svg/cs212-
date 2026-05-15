

public class StudentList implements IStudentList {

	public LinkedList <IStudent> students = new LinkedList <IStudent> ();
	
	@Override
	public boolean add(IStudent student) {

		//duplicate check
	    if (findById(student.getStudentId()) != null) {
	        return false;
	    }

	    //empty check
	    if (students.empty()) {
	        students.insertAtFront((Student) student);
	        return true;
	    }

	    students.findFirst();
	    while (!students.last() && student.getStudentId() > students.retrieve().getStudentId()) {
	        students.findNext();
	    }
	    //if student id is bigger, insert before

	    if (student.getStudentId() < students.retrieve().getStudentId()) {
	        students.insertBefore((Student) student);
	    } 
	    //if student id is smaller, insert after
	    else {
	        students.insert((Student) student);
	    }

	    return true;
	}

	@Override
	 public IStudent findById(int studentId)
    {
        IStudent student = null;
        
        boolean found = false;
        if ( ! students.empty())
        {
            students.findFirst();
        
            while ( !students.last() && !found) {
            	if ( studentId == students.retrieve().getStudentId())
                {
                    found = true;
                    student = students.retrieve();
                }
                 else   
                    students.findNext();
            }
            if ( !found && (studentId == students.retrieve().getStudentId()))
            {
                found = true;
                student = students.retrieve();
            }
        }
        return student;
    }

	@Override
	public LinkedList<IStudent> findByName(String fullName) {
	    LinkedList<IStudent> data = new LinkedList<IStudent>();
	    
	    if (!students.empty()) {
	        students.findFirst();
	        
	        while (!students.last()) {
	            if (students.retrieve().getName().equalsIgnoreCase(fullName)) {
	                data.insert(students.retrieve()); 
	            }
	            students.findNext();
	        }
	        
	        if (students.retrieve().getName().equalsIgnoreCase(fullName)) {
	            data.insert(students.retrieve());
	        }
	    }
	    
	    return data;
	}

	@Override
	public LinkedList<IStudent> findByNameContains(String partialName) {
	    LinkedList<IStudent> data = new LinkedList<IStudent>();

	    if (!students.empty()) {
	        students.findFirst();
	        while (!students.last()) {
	            if (students.retrieve().getName().toLowerCase().contains(partialName.toLowerCase())) {
	                data.insert(students.retrieve());
	            }
	            students.findNext();
	        }
	        
	        if (students.retrieve().getName().toLowerCase().contains(partialName.toLowerCase())) {
	            data.insert(students.retrieve());
	        }
	    }
	    return data;
	}

	@Override
	public IStudent findByEmail(String email) {
	    IStudent student = null;
	    boolean found = false;

	    if (!students.empty()) {
	        students.findFirst();

	        while (!students.last() && !found) {
	        	
	            if (email.equalsIgnoreCase(students.retrieve().getEmail())) {
	                found = true;
	                student = students.retrieve();
	            } else {
	                students.findNext();
	            }
	        }

	        if (!found && email.equalsIgnoreCase(students.retrieve().getEmail())) {
	            found = true;
	            student = students.retrieve();
	        }
	    }

	    return student;
	}

	@Override
	public LinkedList<IStudent> findByMajor(String major) {
	    LinkedList<IStudent> data = new LinkedList<IStudent>();

	    if (!students.empty()) {
	        students.findFirst();
	        
	        while (!students.last()) {

	        	if (students.retrieve().getMajor().equalsIgnoreCase(major)) {
	                data.insert(students.retrieve());
	            }
	            students.findNext();
	        }
	        
	        if (students.retrieve().getMajor().equalsIgnoreCase(major)) {
	            data.insert(students.retrieve());
	        }
	    }
	    
	    return data;
	}

	@Override
	public LinkedList<IStudent> findByYearLevel(int yearLevel) {
	    LinkedList<IStudent> data = new LinkedList<IStudent>();

	    if (!students.empty()) {
	        students.findFirst();
	        
	        while (!students.last()) {
	            if (students.retrieve().getYearLevel() == yearLevel) {
	                data.insert(students.retrieve());
	            }
	            students.findNext();
	        }
	        
	        if (students.retrieve().getYearLevel() == yearLevel) {
	            data.insert(students.retrieve());
	        }
	    }
	    
	    return data;
	}

	@Override
	public LinkedList<IStudent> getAll() {
		return students;
	}

	@Override
    public boolean deleteById(int studentId)
    {
        boolean delete = false;
        if (!students.empty())
        {
            students.findFirst();
            while (!students.last() && !delete)
            {
                if (studentId == students.retrieve().getStudentId())
                {
                    delete = true;
                    students.remove();
                }
                 else   
                    students.findNext();
            }
            if (!delete && studentId == students.retrieve().getStudentId())
            {
                delete = true;
                students.remove();
            }
        }
        return delete;
    }

	@Override
	public int size() {
	    int count = 0;

	    if (!students.empty()) {
	        students.findFirst();
	        while (!students.last()) {
	            count++;
	            students.findNext();
	        }
	        count++;
	    }

	    return count;
	}

}
