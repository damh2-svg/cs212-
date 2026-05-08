/**
 * Stores all events in a structure maintained in alphabetical order by title.
 */
public interface IEventList {

    //Adds an event and maintains alphabetical ordering by title.
     boolean addEvent(IEvent event);

    //Removes an event by its unique event ID.
     boolean removeEventById(int eventId);

    //Returns all events alphabetically ordered by title.
	LinkedList<IEvent> getAllAlphabetically();

    //Returns all events whose title matches the given title.
	LinkedList<IEvent> findByTitle(String title);

    //Returns all events that involve a student with the given full name.
	LinkedList<IEvent> findByStudentName(String studentFullName);

    //return number of events stored. 
    int size();
}