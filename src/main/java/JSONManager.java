import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Scanner;

public class JSONManager {

    private String doctorsFileName;
    private String patientsFileName;
    private JSONParser jsonparser;
    private ObjectMapper mapper;
    private ArrayList<Patient> patients = new ArrayList();
    private ArrayList<Doctor> doctors = new ArrayList();
    InputReceiver inputReceiver;

    public JSONManager(String doctorsFileName, String patientsFileName, InputReceiver inputReceiver, JSONParser jsonparser, ObjectMapper mapper){
        this.doctorsFileName = doctorsFileName;
        this.patientsFileName = patientsFileName;
        this.inputReceiver = inputReceiver;
        this.mapper = mapper;
    }

    public ArrayList<Patient> getPatients(){
        return patients;
    }

    public void addPatients(Patient p){
        patients.add(p);
    }

    public ArrayList<Doctor> getDoctors(){
        return doctors;
    }

    private void deleteHumanFromJSON() throws WrongInputException {
        String human = inputReceiver.getRole();
        String[] fullName = inputReceiver.getNameSurname(human);
        if (human.toLowerCase().equals("doctor")) {
            String specialization = inputReceiver.getSpecialization();
            for (int i = 0; i < doctors.size(); i++){
                if (doctors.get(i).getSurname().equals(fullName[1]) && doctors.get(i).getName().equals(fullName[0]) &&
                        doctors.get(i).getSpecialization().equals(specialization)){
                    doctors.remove(i);
                    i--;
                }
            }
            jsonparser.addDoctorToFile(doctorsFileName, doctors,  mapper);
        } else if (human.toLowerCase().equals("patient")) {
            String illness = inputReceiver.getIllness();
            for (int i = 0; i < patients.size(); i++){
                if (patients.get(i).getSurname().equals(fullName[1]) && patients.get(i).getName().equals(fullName[0]) &&
                        patients.get(i).getIllness().equals(illness)){
                    patients.remove(i);
                    i--;
                }
            }
            jsonparser.addPatientToFile(patientsFileName, patients , mapper);
        }
    }

    private void print() {
        for(Patient p: patients)
            System.out.println(p);
        for(Doctor d: doctors)
            System.out.println(d);
    }

    private void readFromJSON(){
        doctors = jsonparser.readDoctorsFromFile(doctorsFileName, mapper);
        patients = jsonparser.readPatientsFromFile(patientsFileName, mapper);
        print();
    }

    private void insertHumanIntoJSON() throws WrongInputException {
        String human = inputReceiver.getRole();
        String[] fullName = inputReceiver.getNameSurname(human);
        if (human.toLowerCase().equals("doctor")) {
            String specialization = inputReceiver.getSpecialization();
            String passportNumber = inputReceiver.getPassID(human);
            doctors.add(new Doctor(fullName[0], fullName[1], specialization, passportNumber));
            jsonparser.addDoctorToFile(doctorsFileName, doctors,  mapper);
        } else if (human.toLowerCase().equals("patient")) {
            String illness = inputReceiver.getIllness();
            String passportNumber = inputReceiver.getPassID(human);
            patients.add(new Patient(fullName[0], fullName[1], illness, passportNumber));
            jsonparser.addPatientToFile(patientsFileName, patients , mapper);
        }
    }

    public boolean readCommandForJSON() throws WrongInputException {
        Command command = inputReceiver.getCommand();
        if(command == Command.insert) {
            insertHumanIntoJSON();
            return true;
        }
        if(command == Command.delete) {
            deleteHumanFromJSON();
            return true;
        }
        if (command == Command.print) {
            readFromJSON();
            return true;
        }
        return false;
    }

}
