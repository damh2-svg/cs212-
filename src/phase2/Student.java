package phase2;//

public class Student extends Person implements IStudent {

        String studentMajor;

        public Student(int id, String name, String email, ISchedule schedule, String major) {
            super(id, name, email, schedule);
            studentMajor = major;
        }

       
        @Override
        public String getMajor()
        {
            return studentMajor;
        }
}
