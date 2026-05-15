

public abstract class Event implements IEvent {

    protected final int eventId;

    protected String title;

    protected final IDateTime startDateTime;
    protected final IDateTime endDateTime;

    protected String location;

    public Event(int id, String title,
                 IDateTime startDateTime,
                 IDateTime endDateTime,
                 String location) {

        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Title cannot be null or empty");

        if (startDateTime == null || endDateTime == null)
            throw new IllegalArgumentException("Start and end date/time cannot be null");

        if (startDateTime.compareTo(endDateTime) >= 0)
            throw new IllegalArgumentException("Start time must be before end time");

        if (location == null)
            location = "";

        this.eventId = id;
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
    }

    @Override
    public int getEventId() {
        return eventId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {

        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException();

        this.title = title;
    }

    @Override
    public IDateTime getStartDateTime() {
        return startDateTime;
    }

    @Override
    public IDateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = (location == null) ? "" : location;
    }

    @Override
    public int compareTo(IEvent other) {
        return this.title.compareToIgnoreCase(other.getTitle());
    }

    public boolean conflictsWith(IEvent other) {

        return this.startDateTime.compareTo(other.getEndDateTime()) < 0 &&
               this.endDateTime.compareTo(other.getStartDateTime()) > 0;
    }
public abstract String getEventType();
    @Override
    public String toString() {

        return "ID: " + eventId +
               " | Title: " + title +
               " | Start: " + startDateTime.format() +
               " | End: " + endDateTime.format() +
               " | Location: " + location;
    }
}
