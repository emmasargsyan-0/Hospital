import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Printer {

    public int printTable(ResultSet rs) throws SQLException {
        int columnsNumber = 0;
        String word;
        int number = 0;
        while (rs.next()) {
            if(number == 0){
                ResultSetMetaData rsmd = rs.getMetaData();
                columnsNumber = rsmd.getColumnCount();

                for (int i = 1; i <= columnsNumber; i++) {
                    word = rsmd.getColumnName(i);
                    if (i > 1){
                        System.out.print("|");
                    }

                    System.out.print(word);
                    for (int j = 0; j <5-word.length() ; j++) {
                        System.out.print(" ");
                    }

                }
                System.out.println("");
            }
            number++;
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                {
                    System.out.print("|");

                }
                word = rs.getString(i);
                System.out.print(word);
                if(word!=null) {
                    for (int j = 0; j < 5 - word.length(); j++) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("");
        }
        return number;
    }

    public int printDoctorAndReturnId(ResultSet resultSet) {
        int id = -1;
        int number = 0;
        try {
            while (resultSet.next()) {
                number++;
                id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String lastName = resultSet.getString("LastName");
                String specialization = resultSet.getString("Specialization");
                String passportNumber = resultSet.getString("PassportNumber");
                System.out.println("Name - " + name + ", Lastname - " + lastName +
                        ", Specialization - " + specialization + ", PassportNumber - " + passportNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(number > 1)
            return 0;
        return  id;
    }

    public int printPatientAndReturnId(ResultSet resultSet) {
        int id = -1;
        int number = 0;
        try {
            while (resultSet.next()) {
                number++;
                id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String lastName = resultSet.getString("LastName");
                String specialization = resultSet.getString("Illness");
                String passportNumber = resultSet.getString("PassportNumber");
                System.out.println("Name - " + name + ", Lastname - " + lastName +
                        ", Specialization - " + specialization + ", PassportNumber - " + passportNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(number > 1)
            return 0;
        return  id;
    }

}
