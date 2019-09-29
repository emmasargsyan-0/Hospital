import java.sql.*;

public class DoctorDAO {

    private Connection connection ;
    private Printer printer;

    DoctorDAO(Connection connection, Printer printer){
        this.printer = printer;
        this.connection = connection;
    }

    public int checkIfIsAnyDoctorByPassport(String passportNumber){
        int number = 0;
        try {
            String query="Select * from Doctor where PassportNumber=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,passportNumber);
            number = printer.printTable(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }

    public int checkIfIsAnyDoctorByName(String name, String lastName) {
        int number=0;
        try {
            String query="Select * from Doctor where [Name]=? and LastName =?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,lastName);
            number = printer.printDoctorAndReturnId(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  number;
    }

    public int getIdOfDoctorByPassportNumber(String doctorPassportNumber){
        int id = 0;
        try  {
            String query="Select [ID] from Doctor where PassportNumber=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,doctorPassportNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                id = resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int getIdOfDoctorByName(String doctorName, String doctorLastName){
        int id = 0;
        try{
            String query="Select [ID] from Doctor where [Name]=? and LastName=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,doctorName);
            statement.setString(2,doctorLastName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                id = resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean getDoctorsFromDB() {
        try {
            String query="Select * from Doctor";
            PreparedStatement statement = connection.prepareStatement(query);
            printer.printTable(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int insertDoctorToDB(Doctor doctor) {
        int result = 0;
        try {
            String query="INSERT INTO Doctor([Name],[LastName],Specialization, PassportNumber) values (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,doctor.getName());
            statement.setString(2,doctor.getSurname());
            statement.setString(3,doctor.getSpecialization());
            statement.setString(4,doctor.getPassportNumber());
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteDoctorFromDBWithName(String name, String lastName) {
        int result = 0;
        try{
            String query="DELETE FROM Doctor WHERE [Name]=? AND [LastName]=?";
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

    public int deleteDoctorFromDBWithPassportNumber(String passportNumber) {
        int result = 0;
        try {
            String query="DELETE FROM Doctor WHERE [passportNumber]=? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,passportNumber);
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
