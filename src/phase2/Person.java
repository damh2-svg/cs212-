package phase2;
public class Person implements IPerson {
    
        int personID;
        String personName;
        String personEmail;
        ISchedule personSchedule;

        Person(int id, String name, String email, ISchedule schedule)
        {
                personID = id;
                personName = name;
                personEmail = email;
                personSchedule = new Schedule ();
        }

        @Override
        public int getId()
        {
                return personID;
        }

        @Override
        public String getName()
        {
         return personName;
        }

        @Override
        public String getEmail()
        {
        return personEmail;
        }

        @Override
        public ISchedule getSchedule()
        {
                return personSchedule;
        }

        
        @Override
        public int compareTo(IPerson o) {
                return (this.personID -  o.getId());
        }
}
