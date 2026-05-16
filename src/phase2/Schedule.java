package phase2;

public class Schedule implements ISchedule {

    private BSTMap<ITimeSlot, Integer> events;
    private BSTSet<Integer> eventIds;

    public Schedule() {
        events = new BSTMap<ITimeSlot, Integer>();
        eventIds = new BSTSet<Integer>();
    }

    @Override
    public int size() {
        return eventIds.size();
    }

    @Override
    public boolean empty() {
        return eventIds.empty();
    }

    @Override
    public void clear() {
        events.clear();
        eventIds.clear();
    }

    @Override
    public boolean add(int eventId, ITimeSlot timeSlot) {

        if (eventIds.contains(eventId))
            return false;

        if (conflicts(timeSlot))
            return false;

        eventIds.insert(eventId);
        events.insert(timeSlot, eventId);

        return true;
    }

    @Override
    public boolean remove(int eventId) {

        if (!eventIds.contains(eventId))
            return false;

        List<ITimeSlot> keys = events.getKeys();

        if (!keys.empty()) {
            keys.findFirst();

            while (true) {
                ITimeSlot slot = keys.retrieve();
                Integer id = events.get(slot);

                if (id.intValue() == eventId) {
                    events.remove(slot);
                    break;
                }

                if (keys.last())
                    break;

                keys.findNext();
            }
        }

        eventIds.remove(eventId);
        return true;
    }

    @Override
    public boolean contains(int eventId) {
        return eventIds.contains(eventId);
    }

    @Override
    public boolean conflicts(ITimeSlot timeSlot) {

        List<ITimeSlot> keys = events.getKeys();

        if (keys.empty())
            return false;

        keys.findFirst();

        while (true) {
            ITimeSlot current = keys.retrieve();

            if (current.compareTo(timeSlot) == 0)
                return true;

            if (keys.last())
                break;

            keys.findNext();
        }

        return false;
    }

    @Override
    public Set<Integer> getEventIds() {
        return eventIds;
    }

    @Override
    public Map<ITimeSlot, Integer> getEvents() {
        return events;
    }

    @Override
    public void addMeeting(IMeeting meeting) throws SchedulingException {

        boolean added = add(meeting.getId(), meeting.getTimeSlot());

        if (!added)
            throw new SchedulingException(ScheduleFailureReason.CONFLICT_STUDENT);
    }
}
