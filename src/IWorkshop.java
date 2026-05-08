/**
	* Represents a workshop involving multiple students.
*/
public interface IWorkshop extends IEvent {

// Returns the list of workshop participants.
LinkedList<IStudent> getParticipants();

// Adds a student to the workshop.
boolean addParticipant(IStudent student);

// Removes a student from the workshop by ID.
boolean removeParticipantById(int studentId);

// Returns true if the workshop has no participants.
boolean isEmpty();
}