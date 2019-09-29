import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Patient extends Human implements Serializable{

    private String illness;
    private int doctorId = 0;


    public Patient(String name, String lastName, String illness, String passportNumber){
        super(name, lastName, passportNumber);
        this.illness = illness;
    }

    @JsonCreator
    public Patient(@JsonProperty("name") String name, @JsonProperty("lastName") String lastName,
                   @JsonProperty("illness") String illness, @JsonProperty("passportNumber") String passportNumber,
                   @JsonProperty("DoctorId") int doctorId ){
        super(name, lastName, passportNumber);
        this.illness = illness;
        this.doctorId = doctorId;
    }

    public void setDoctorId(int doctorId){this.doctorId=doctorId; }

    public int getDoctorId(){ return doctorId;}

    public String getIllness() {
        return illness;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + getName() + '\'' +
                ", lastName='" + getSurname() + '\'' +
                ", illness='" + illness + '\'' +
                ", passportNumber='" + getPassportNumber() + '\'' +
                ", doctorId='" + doctorId + '\'' +
                '}'+",";
    }

}
