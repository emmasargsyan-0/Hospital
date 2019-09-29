import java.util.Scanner;

public class InputReceiver {

    private Scanner scanner;

    public InputReceiver(Scanner scanner) {
        this.scanner = scanner;
    }

    public Command getCommand() throws WrongInputException {
        System.out.println("Print command(Insert, Delete, Print, Print Patients By Doctors, Give Patient To Doctor, Exit)");
        String com = "";
        while(com.equals("")) {
            com = scanner.nextLine().toLowerCase();
        }
        switch (com) {
            case "insert":
                return Command.insert;
            case "delete":
                return Command.delete;
            case "give patient to doctor":
                return Command.give;
            case "print":
                return Command.print;
            case "print patients by doctors":
                return Command.selectPatient;
            case "exit":
                return Command.exit;
        }
        throw new WrongInputException();
    }

    public String[] getNameSurname(String str) throws WrongInputException {
        System.out.println("Print " + str + " full name");
        String[] fullName = scanner.nextLine().split(" ");
        if(fullName.length == 2){
            fullName[0] = fullName[0].substring(0,1).toUpperCase()+
                    fullName[0].substring(1).toLowerCase();
            fullName[1] = fullName[1].substring(0,1).toUpperCase()+
                    fullName[1].substring(1).toLowerCase();
            return fullName;
        }
        throw new WrongInputException();
    }

    public String getPassID(String str) throws WrongInputException {
        System.out.println("Print " + str + " passport id");
        String passID= scanner.nextLine().toUpperCase();
        if(passID.length()==9)
            return passID;
        throw new WrongInputException();
    }

    public String getRole() throws WrongInputException {
        System.out.println("Print role");
        String role= scanner.nextLine().toLowerCase();
        if(role.equals("doctor") || role.equals("patient"))
            return role;
        throw new WrongInputException();
    }

    public  int getDoctorID() throws WrongInputException {
        System.out.println("Enter DoctorId if any or 0");
        return scanner.nextInt();
    }

    public  String getSpecialization(){
        System.out.println("Print specialization");
        String specialization= scanner.nextLine().toLowerCase();
        return specialization.substring(0,1).toUpperCase()+specialization.substring(1);
    }

    public  String getIllness(){
        System.out.println("Print illness or null");
        String disease= scanner.nextLine().toLowerCase();
        return disease.substring(0,1).toUpperCase()+disease.substring(1);
    }

    public void close()
    {
        scanner.close();
    }

}
