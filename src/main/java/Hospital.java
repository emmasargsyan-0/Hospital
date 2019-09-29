import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;

public class Hospital
{
    private  CommandParser commandParser;
    private  DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
    private  InputReceiver inputReceiver;
    private JSONManager jsonManager;
    final static private String doctorsFileName = "doctors.json";
    final static  private String patientsFileName = "patients.json";


    public Hospital(Scanner scanner, DoctorDAO doctorDAO, PatientDAO patientDAO, JSONParser jsonParser, ObjectMapper mapper){
        this.doctorDAO = doctorDAO;
        this.patientDAO = patientDAO;
        this.inputReceiver = new InputReceiver(scanner);
        this.commandParser = new CommandParser(scanner, this.doctorDAO, this.patientDAO);
        jsonManager= new JSONManager(doctorsFileName,  patientsFileName, inputReceiver, jsonParser, mapper);
    }

    public boolean readCommandForDB() throws WrongInputException {
        return commandParser.parseCommand();
    }

    public boolean readCommandForJSON() throws WrongInputException {
        return jsonManager.readCommandForJSON();
    }

}
