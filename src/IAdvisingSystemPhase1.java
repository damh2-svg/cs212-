

/**
 * The interface of the Student Advising System.
 */
public interface IAdvisingSystemPhase1 {
	// Loads students from a CSV file.
	// Returns true if loading succeeds; false otherwise.
	boolean loadStudentsFromCSV(String studentsFilePath);
	
	// Loads events from a CSV file. All referenced students must already exist. It must enforce conflict rules
	// Returns true if loading succeeds; false otherwise.
	boolean loadEventsFromCSV(String eventsFilePath);

    //Adds a student to the system after enforcing uniqueness by student ID. Returns true if added; false if duplicate ID or invalid
     boolean addStudent(IStudent student);

    //Searches for a student by ID.
    IStudent searchStudentById(int studentId);

    //Searches for a student by email.
    IStudent searchStudentByEmail(String email);

    //Prints students filtered by major.
     void printStudentsByMajor(String major);

    //Prints students filtered by year level.
    void printStudentsByYearLevel(int yearLevel);
    
	// Prints all students whose full name exactly matches the given name.
	void printStudentsByName(String fullName);
	
	// Prints all students whose full name partially matches the given string.
	void printStudentsByPartialName(String partialName);

    //Prints all students stored in the system.
    void printAllStudents();

    /**
     * Deletes a student and performs cascade deletion of associated events:
     * - All meetings involving the student are deleted.
     * - The student is removed from any workshops.
     * - Workshops with no remaining participants are deleted.
     * It returns true if deletion occurred; false if student not found
     */
    boolean deleteStudent(int studentId);

    /**
     * Schedules a meeting for one student.
     * Requirements:
     * - The student must exist.
     * - The meeting must not conflict with the student's current schedule.
     * It returns true if scheduled; false otherwise
     */
    boolean scheduleMeeting(String title, IDateTime startDateTime, IDateTime endDateTime, String location, int studentId);

    /**
     * Schedules a workshop for multiple students.
     * Requirements:
     * - All students must exist.
     * - The workshop must not conflict with any listed student's schedule.
     * It returns true if scheduled; false otherwise
     */
    boolean scheduleWorkshop(String title, IDateTime startDateTime, IDateTime endDateTime, String location, int[] studentIds);

    //Prints meeting/workshop details by title (may print multiple if titles repeat).
    void printEventDetailsByTitle(String title);

    //Prints meeting/workshop details involving a student with the given name.
     void printEventDetailsByStudentName(String studentName);

    //Prints all students registered in a workshop identified by its title.
    void printWorkshopStudents(String workshopTitle);

    //Prints all meetings/workshops alphabetically by title.
    void printAllEventsAlphabetically();
}