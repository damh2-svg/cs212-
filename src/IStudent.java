

/**
 * Represents a single student in the advising system.
 * Students are compared by studentId.
 */
public interface IStudent extends Comparable<IStudent> {
    //return the full name of the student.
    String getName();

    //Sets the full name of the student.
    void setName(String name);

    //return the unique student ID.
    int getStudentId();

    //return the student's email address.
    String getEmail();

    //Sets the student's email address.
    void setEmail(String email);

    //return the student's major.
    String getMajor();

    //Sets the student's major.
    void setMajor(String major);

    //return the student's academic year level.
    int getYearLevel();

    //Sets the student's academic year level.
    void setYearLevel(int yearLevel);

    //return additional notes related to the student.
    String getNotes();

    // Replaces any existing notes with the provided text.
    void setNotes(String notes);

    //Extracts and returns the first name of the student.The first name is defined as the substring before the first space.
    String getFirstName();

    //return the list of events associated with this student.
    LinkedList<IEvent> getSchedule();
    
    // Returns a formatted string describing the student.
    @Override
    String toString();
    
    // Compares this student with another student based on studentId. 
    @Override
    int compareTo(IStudent other);
}