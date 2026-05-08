/**
	* Represents a meeting involving exactly one student.
*/
public interface IMeeting extends IEvent {

// Returns the student assigned to this meeting.
IStudent getStudent();

// Sets the student assigned to this meeting.
void setStudent(IStudent student);
}