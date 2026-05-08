/**
	* Represents a generic event in the advising system. Set the start and end Date/Time values only in the constructor
*/
public interface IEvent extends Comparable<IEvent> {

	// Returns the unique internal event ID.
	int getEventId();
	
	// Returns the title of the event.
	String getTitle();
	
	// Sets the title of the event.
	void setTitle(String title);
	
	// Returns the start date/time of the event.
	IDateTime getStartDateTime();
	
	// Returns the end date/time of the event.
	IDateTime getEndDateTime();
	
	// Returns the location of the event.
	String getLocation();
	
	// Sets the location of the event.
	void setLocation(String location);
	
	// Checks whether a student participates in this event.
	boolean hasStudent(int studentId);
	
	// Returns a formatted string describing the event.
	@Override
	String toString();
	
	// Compares events alphabetically by title.
	@Override
	int compareTo(IEvent other);
}