public class Student implements IStudent {

    private final int studentId; 
    private String name;
    private String email;
    private String major;
    private int yearLevel;
    private String notes;
    private LinkedList<IEvent> schedule; 
    
    public Student(int studentId, String name, String email, String major, int yearLevel, String notes) {
        this.studentId = studentId; 
        this.name = name;
        this.email = email;
        this.major = major;
        this.yearLevel = yearLevel;
        this.notes = notes; 
        
        this.schedule = new LinkedList<IEvent>(); 
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name; 
    }

    @Override
    public int getStudentId() {
        return studentId;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getMajor() {
        return major;
    }

    @Override
    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public int getYearLevel() {
        return yearLevel;
    }

    @Override
    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String getFirstName() {
        if (name == null || name.isEmpty())
            return ""; 
            
        int space = name.indexOf(" ");
        if (space == -1)
            return name;
        else
            return name.substring(0, space); 
    }

    @Override
    public String toString() {
        String str = "ID: " + studentId + " | Name: " + name + " | Major: " + major + "\n";
        str += "Events: ";
        
        if (schedule.empty()) {
            str += "No events scheduled.";  
        } 
        else {
            schedule.findFirst();
            while (!schedule.last()) {
                str += schedule.retrieve().getTitle() + ", ";
                schedule.findNext();
            }
            str += schedule.retrieve().getTitle();
        }
        return str;
    }
    
    @Override
    public LinkedList<IEvent> getSchedule() {
        return schedule;
    }

    @Override
    public int compareTo(IStudent other) {
        if (this.studentId > other.getStudentId()) return 1;
        if (this.studentId < other.getStudentId()) return -1;
        return 0;
    }
}