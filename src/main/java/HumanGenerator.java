import java.util.*;

public class HumanGenerator {

    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
    private String[] names =new String[]{"Emma", "Gohar", "Lusine", "Ani", "Sara", "Elen", "Vahan", "Gor", "Arsen","Vahag", "Narek", "Edgar","Alyona","Khazhak",
            "Hakob", "Sargis"};
    private String[] lastnames =new String[]{"Sargsyan", "Muradyan", "Arakelyan", "Ghazartyan", "Grigoryan", "Manukyan", "Harutyunyan", "Manukyan"
            ,"Ayvazyan", "Ter-Antonyan"};
    private String[] specialization=new String[]{"Addiction psychiatrist",
            "Adolescent medicine specialist",
            "Allergist",
            "Anesthesiologist",
            "Cardiac electrophysiologist",
            "Cardiologist",
            "Cardiovascular surgeon",
            "Colon and rectal surgeon"};
    private String [] illness=new String[]{"Alcohol abuse and alcoholism",
            "Allergies",
            "Alopecia areata",
            "Amputation",
            "Anxiety disorders",
            "Arthritis",
            "Asperger syndrome",
            "Asthma",
            "Deafness and being hard of hearing",
            "Depression",
            "Diabetes",
            "Down syndrome",
            "Drug abuse and addiction",
            "Dwarfism"
    };

    public HumanGenerator(DoctorDAO doctorDAO, PatientDAO patientDAO){
        this.doctorDAO = doctorDAO;
        this.patientDAO = patientDAO;
    }

    public void generateDoctors(){
        Random rand = new Random();
        for(int i=0; i<20; i++) {
            int nameIndex = rand.nextInt(12); //0-11
            int lastNameIndex = rand.nextInt(10); //0-11
            int specializationIndex = rand.nextInt(8); // 0-7
            doctorDAO.insertDoctorToDB(new Doctor(names[nameIndex], lastnames[lastNameIndex], generatePassportNumber(), specialization[specializationIndex]));
        }
    }

    public void generatePatients(){
        Random rand = new Random();
        for(int i=0; i<100; i++) {
            int nameIndex = rand.nextInt(12); //0-11
            int lastNameIndex = rand.nextInt(10); //0-11
            int illnessIndex = rand.nextInt(14); //0-14
            patientDAO.insertPatientToDB(new Patient(names[nameIndex], lastnames[lastNameIndex], illness[illnessIndex]
                    ,generatePassportNumber(), 1 + rand.nextInt(20)));
        }
    }

    private String generatePassportNumber(){
        Random rand = new Random();
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        String passportNumber="";
        passportNumber+=""+alphabet[rand.nextInt(26)]+alphabet[rand.nextInt(26)]+(rand.nextInt(8999999)+1000000);
        return passportNumber;
    }

}
