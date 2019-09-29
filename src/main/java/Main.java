import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DBManager manager = new SqlServerManager("jdbc:sqlserver://localhost:1433;database=hospitalDB","lg","1234");
        Scanner scan = new Scanner(System.in);
        Printer printer = new Printer();
        manager.connect();
        DoctorDAO doctorDAO = new DoctorDAO(manager.getConnection(), printer);
        PatientDAO patientDAO = new PatientDAO(manager.getConnection(), printer);
        JSONParser jsonParser = new JSONParser();
        ObjectMapper objectMapper = new ObjectMapper();
        Hospital hospital = new Hospital(scan, doctorDAO, patientDAO, jsonParser, objectMapper);
        while(true){
            try {
                if (!hospital.readCommandForDB()){ break;}
            } catch (WrongInputException e) {
                e.printStackTrace();
            }
        }
        manager.disconnect();
    }

}


