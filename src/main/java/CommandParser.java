import java.util.Scanner;

public class CommandParser {

    private  DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
    private  InputReceiver inputReceiver;

    public CommandParser(Scanner scanner, DoctorDAO doctorDAO, PatientDAO patientDAO ){
        this.doctorDAO = doctorDAO;
        this.patientDAO = patientDAO;
        inputReceiver = new InputReceiver(scanner);
    }

    private boolean insertPatientCommand( ) throws WrongInputException {
        String[] fullName = inputReceiver.getNameSurname("patient");
        String passportNumber = inputReceiver.getPassID("patient");
        int tmp = 0;
        if(patientDAO.checkIfIsAnyPatientByPassport(passportNumber) != 0) {
            System.out.println("We have patient with same passport number");
            return false;
        }
        String illness = inputReceiver.getIllness();
        if(illness.equals("null"))
            illness = "";
        int doctorId = inputReceiver.getDoctorID();
        if(doctorId == 0)
            doctorId = 1;
        tmp = patientDAO.insertPatientToDB(new Patient(fullName[0], fullName[1], illness, passportNumber, doctorId));
        return tmp != 0;
    }

    private boolean insertDoctorCommand( ) throws WrongInputException {
        String[] fullName = inputReceiver.getNameSurname("doctor");
        String passportNumber = inputReceiver.getPassID("doctor");
        int tmp = 0;
        if(doctorDAO.checkIfIsAnyDoctorByPassport(passportNumber) != 0) {
            System.out.println("We have doctor with same passport number");
            return false;
        }
        String specialization = inputReceiver.getSpecialization();
        tmp = doctorDAO.insertDoctorToDB(new Doctor(fullName[0], fullName[1], passportNumber, specialization));
        return tmp != 0;
    }

    private boolean deletePatientCommand() throws WrongInputException {
        String[] fullName = inputReceiver.getNameSurname("patient");
        int tmp = patientDAO.checkIfIsAnyPatientByName(fullName[0], fullName[1]);
        if (tmp == -1) {
            System.out.println("We do not have patient with given name and lastname");
            return false;
        } else if (tmp == 0) {
            System.out.println("We have more than one patient with given name and lastname, enter passport number");
            String passportNumber = inputReceiver.getPassID("patient");
            return patientDAO.deletePatientFromDBByPassportNumber(passportNumber) != 0;
        }else{
            return patientDAO.deletePatientFromDBByName(fullName[0], fullName[1]) != 0;
        }
    }

    private boolean deleteDoctorCommand() throws WrongInputException {
        String[] fullName = inputReceiver.getNameSurname("doctor");
        int tmp = doctorDAO.checkIfIsAnyDoctorByName(fullName[0], fullName[1]);
        if (tmp == -1) {
            System.out.println("We do not have doctor with given name and lastname");
            return false;
        } else if (tmp == 0) {
            System.out.println("We have more than one doctor with given name and lastname, enter passport number");
            String passportNumber = inputReceiver.getPassID("doctor");
            return doctorDAO.deleteDoctorFromDBWithPassportNumber(passportNumber) != 0;
        } else {
            return doctorDAO.deleteDoctorFromDBWithName(fullName[0], fullName[1]) != 0;
        }
    }

    private boolean printPatientCommand( ) {
        return patientDAO.getPatientsFromDB();
    }

    private boolean printDoctorCommand( ) {
       return doctorDAO.getDoctorsFromDB();
    }

    private boolean printPatientsByDoctorsCommand( ) {
        return patientDAO.getPatientsGroupByDoctorsFromDB();
    }

    private boolean giveDoctorToPatientCommand( ) throws WrongInputException {
        String[] fullName = inputReceiver.getNameSurname("doctor");
        int tmp = doctorDAO.checkIfIsAnyDoctorByName(fullName[0], fullName[1]);
        int doctorId;
        if(tmp == -1){
            System.out.println("We do not have doctor with given name and lastname");
            return false;
        }else if(tmp == 0){
            String doctorPassportNumber = inputReceiver.getPassID("doctor");
            doctorId = doctorDAO.getIdOfDoctorByPassportNumber(doctorPassportNumber);
        }
        else {
            doctorId=doctorDAO.getIdOfDoctorByName(fullName[0], fullName[1]);
        }
        String[] fullPatientName = inputReceiver.getNameSurname("patient");
        tmp = patientDAO.checkIfIsAnyPatientByName(fullPatientName[0], fullPatientName[1]);
        if(tmp == -1){
            System.out.println("We do not have patient with given name and lastname");
            return false;
        }else if(tmp == 0){
            String patientPassportNumber = inputReceiver.getPassID("patient");
            tmp = patientDAO.updatePatientsDoctorByPassportNumber(patientPassportNumber, doctorId);
        }
        else {
            tmp = patientDAO.updatePatientsDoctorByName(fullPatientName[0], fullPatientName[1], doctorId);
        }
        return tmp != 0;
    }

    public boolean parseCommand() throws WrongInputException {
        Command command = inputReceiver.getCommand();
        boolean result = false;
        String role;
        switch (command) {
            case insert:
                role = inputReceiver.getRole();
                if(role.equals("doctor")) {
                    result = insertDoctorCommand();
                }else if (role.equals("patient")){
                    result = insertPatientCommand();
                }
                break;
            case delete:
                role = inputReceiver.getRole();
                if(role.equals("doctor")) {
                    result = deleteDoctorCommand();
                }else if (role.equals("patient")){
                    result = deletePatientCommand();
                }
                break;
            case give:
                result = giveDoctorToPatientCommand();
                break;
            case print:
                role = inputReceiver.getRole();
                if(role.equals("doctor")) {
                    result = printDoctorCommand();
                }else if (role.equals("patient")){
                    result = printPatientCommand();
                }
                break;
            case selectPatient:
                result = printPatientsByDoctorsCommand();
                break;
            case exit:
                return false;
        }
        if(result){
            System.out.println("Command done");
        }else{
            System.out.println("Command failed");
        }
        return true;
    }

}
