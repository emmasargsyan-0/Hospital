import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientDAO {

    private Connection connection ;
    private Printer printer;

    PatientDAO(Connection connection, Printer printer){
        this.printer = printer;
        this.connection = connection;
    }

    public int deletePatientFromDBByPassportNumber(String passportNumber) {
        int result = 0;
        String query="DELETE FROM Patient WHERE [PassportNumber]=?";
        try{
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,passportNumber);
        result = statement.executeUpdate();
        statement.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public int deletePatientFromDBByName(String name, String lastName) {
        int result = 0;
        try {
            String query="DELETE FROM Patient WHERE [Name]=? AND [LastName]=? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,lastName);
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updatePatientsDoctorByPassportNumber(String doctorPassportNumber, int doctorId){
        int result = 0;
        try {
            String query="UPDATE Patient SET [DoctorID]=? WHERE [PassportNumber]=? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,doctorId);
            statement.setString(2,doctorPassportNumber);
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updatePatientsDoctorByName(String name,String lastName, int doctorId){
        int result = 0;
        try {
            String query="UPDATE Patient SET [DoctorID]=? WHERE [Name]=? AND LastName=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,doctorId);
            statement.setString(2,name);
            statement.setString(3,lastName);
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insertPatientToDB(Patient patient) {
        int result = 0;
        try {
            String query="INSERT INTO Patient([Name],[LastName], Illness, PassportNumber, DoctorId) values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,patient.getName());
            statement.setString(2,patient.getSurname());
            statement.setString(3,patient.getIllness());
            statement.setString(4,patient.getPassportNumber());
            statement.setInt(5,patient.getDoctorId());
            System.out.println(query+ patient);
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int checkIfIsAnyPatientByPassport(String passportNumber){
        int number=0;
        try {
            String query="Select * from Patient where PassportNumber=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,passportNumber);
            number = printer.printTable(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  number;
    }

    public int checkIfIsAnyPatientByName(String name, String lastName) {
        int number=0;
        try  {
            String query="Select * from Patient where [Name]=? and LastName =?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,lastName);
            number = printer.printPatientAndReturnId(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  number;
    }

    public boolean getPatientsFromDB() {
        try {
            String query="Select * from Patient";
            PreparedStatement statement = connection.prepareStatement(query);
            printer.printTable(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean getPatientsGroupByDoctorsFromDB() {
        try {
            String query="SELECT Doctor.[Name], Doctor.LastName, Doctor.PassportNumber, Doctor.Specialization, Patient.[Name], " +
                    " Patient.LastName, Patient.PassportNumber, Patient.Illness " +
                    " FROM Doctor " +
                    " Left JOIN Patient ON Doctor.ID=Patient.DoctorID " +
                    " Order by Doctor.[Name], Doctor.LastName, Doctor.PassportNumber, Doctor.Specialization";
            PreparedStatement statement = connection.prepareStatement(query);
            printer.printTable(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}

