import java.io.Serializable;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Doctor extends Human implements Serializable {

    private String specialization;
    private ArrayList<Patient> patientList = new ArrayList<Patient>();

    @JsonCreator
    public Doctor(@JsonProperty("name") String name, @JsonProperty("lastName") String surname,
                  @JsonProperty("passportNumber") String passportNumber, @JsonProperty("specialization") String specialization){
        super(name, surname, passportNumber);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void addPatient(Patient patient){
        patientList.add(patient);
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void printStatistics(){
        System.out.print(getName() + " " + getSurname() + " " + specialization + "-");
        for(int i = 0; i < patientList.size(); i++) {
            System.out.println(patientList.get(i));
        }
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + getName() + '\'' +
                ", lastName='" + getSurname() + '\'' +
                ", specialization='" + specialization + '\'' +
                ", passportNumber='" + getPassportNumber() + '\'' +
                '}'+",";
    }

}
