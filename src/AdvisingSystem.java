
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdvisingSystem implements IAdvisingSystemPhase1 {

    private StudentList students;
    private EventList events;

    public AdvisingSystem() {
        students = new StudentList();
        events = new EventList();
    }

    @Override
    public boolean loadStudentsFromCSV(String studentsFilePath) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(studentsFilePath));

            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String email = data[2].trim();
                String major = data[3].trim();
                int yearLevel = Integer.parseInt(data[4].trim());
                String notes = data[5].trim();

                Student s = new Student(id, name, email, major, yearLevel, notes);

                addStudent(s);
            }

            br.close();
            return true;
        }

        catch (IOException e) {
            return false;
        }

        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean loadEventsFromCSV(String eventsFilePath) {
        return false;
    }

    @Override
    public boolean addStudent(IStudent student) {
        if (student == null)
            return false;

        return students.add(student);
    }

    @Override
    public IStudent searchStudentById(int studentId) {
        return students.findById(studentId);
    }

    @Override
    public IStudent searchStudentByEmail(String email) {
        return students.findByEmail(email);
    }

    @Override
    public void printStudentsByMajor(String major) {
        printStudentList(students.findByMajor(major));
    }

    @Override
    public void printStudentsByYearLevel(int yearLevel) {
        printStudentList(students.findByYearLevel(yearLevel));
    }

    @Override
    public void printStudentsByName(String fullName) {
        printStudentList(students.findByName(fullName));
    }

    @Override
    public void printStudentsByPartialName(String partialName) {
        printStudentList(students.findByNameContains(partialName));
    }

    @Override
    public void printAllStudents() {
        printStudentList(students.getAll());
    }

    @Override
    public boolean deleteStudent(int studentId) {
        IStudent student = students.findById(studentId);

        if (student == null)
            return false;

        return students.deleteById(studentId);
    }

    @Override
    public boolean scheduleMeeting(String title,
                                   IDateTime startDateTime,
                                   IDateTime endDateTime,
                                   String location,
                                   int studentId) {

        IStudent student = students.findById(studentId);

        if (student == null)
            return false;

        int id = events.size() + 1;

        Meeting meeting = new Meeting(id, title, startDateTime,
                                      endDateTime, location, student);

        if (hasConflict(student, meeting))
            return false;

        events.addEvent(meeting);
        student.getSchedule().insert(meeting);

        return true;
    }

    @Override
    public boolean scheduleWorkshop(String title,
                                    IDateTime startDateTime,
                                    IDateTime endDateTime,
                                    String location,
                                    int[] studentIds) {

        int id = events.size() + 1;

        Workshop workshop = new Workshop(id, title, startDateTime,
                                         endDateTime, location);

        for (int i = 0; i < studentIds.length; i++) {

            IStudent student = students.findById(studentIds[i]);

            if (student == null)
                return false;

            if (hasConflict(student, workshop))
                return false;
        }

        for (int i = 0; i < studentIds.length; i++) {

            IStudent student = students.findById(studentIds[i]);

            workshop.addParticipant(student);
            student.getSchedule().insert(workshop);
        }

        events.addEvent(workshop);

        return true;
    }

    private boolean hasConflict(IStudent student, IEvent newEvent) {
        LinkedList<IEvent> schedule = student.getSchedule();

        if (schedule.empty())
            return false;

        schedule.findFirst();

        while (true) {
            IEvent current = schedule.retrieve();

            if (((Event) newEvent).conflictsWith(current))
                return true;

            if (schedule.last())
                break;

            schedule.findNext();
        }

        return false;
    }

    @Override
    public void printEventDetailsByTitle(String title) {
        printEventList(events.findByTitle(title));
    }

    @Override
    public void printEventDetailsByStudentName(String studentName) {
        printEventList(events.findByStudentName(studentName));
    }

    @Override
    public void printWorkshopStudents(String workshopTitle) {
        LinkedList<IEvent> result = events.findByTitle(workshopTitle);

        if (result.empty()) {
            System.out.println("Workshop not found.");
            return;
        }

        result.findFirst();

        while (true) {
            IEvent current = result.retrieve();

            if (current instanceof Workshop) {
                Workshop w = (Workshop) current;
                LinkedList<IStudent> participants = w.getParticipants();

                System.out.println("Workshop: " + w.getTitle());

                if (participants.empty()) {
                    System.out.println("No students.");
                } else {
                    participants.findFirst();

                    while (true) {
                        System.out.println(participants.retrieve());

                        if (participants.last())
                            break;

                        participants.findNext();
                    }
                }
            }

            if (result.last())
                break;

            result.findNext();
        }
    }

    @Override
    public void printAllEventsAlphabetically() {
        printEventList(events.getAllAlphabetically());
    }

    private void printStudentList(LinkedList<IStudent> list) {
        if (list.empty()) {
            System.out.println("No students found.");
            return;
        }

        list.findFirst();

        while (true) {
            System.out.println(list.retrieve());

            if (list.last())
                break;

            System.out.println("----------------");
            list.findNext();
        }
    }

    private void printEventList(LinkedList<IEvent> list) {
        if (list.empty()) {
            System.out.println("No events found.");
            return;
        }

        list.findFirst();

        while (true) {
            System.out.println(list.retrieve());

            if (list.last())
                break;

            System.out.println("----------------");
            list.findNext();
        }
    }
}
