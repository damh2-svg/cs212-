

/**
 * Stores all students in a structure maintained in sorted order by studentId.
 */
public interface IStudentList {

    // Inserts a student into the list in sorted order by studentId. If a student with the same ID already exists, insertion fails.
    boolean add(IStudent student);

    //Searches for a student by student ID.
     IStudent findById(int studentId);

	 // Searches for all students with the given full name.
	 LinkedList<IStudent> findByName(String fullName);

	// Searches for all students whose full name contains the given substring. The match should be case-insensitive.
	LinkedList<IStudent> findByNameContains(String partialName);

    //Searches for a student by email address.
     IStudent findByEmail(String email);

    //Returns all students with the specified major.
	LinkedList<IStudent> findByMajor(String major);

    //Returns all students in the specified year level.
	LinkedList<IStudent> findByYearLevel(int yearLevel);

    //Returns all students in the linked list.
	LinkedList<IStudent> getAll();
	
    //Deletes a student by student ID.
	boolean deleteById(int studentId);
	
    //return the total number of students stored.
    int size();
}