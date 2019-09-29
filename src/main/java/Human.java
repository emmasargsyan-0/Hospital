public abstract class Human {

    private String name;
    private String surname;
    private String passportNumber;

    public Human(String name, String surname, String passportNumber){
        this.surname = surname;
        this.name = name;
        this.passportNumber=passportNumber;
    }

    public String getName(){
        return name;
    }

    public String getPassportNumber(){
        return passportNumber;
    }

    public String getSurname(){
        return surname;
    }

}
