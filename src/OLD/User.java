package OLD;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class User {

    private int serial;
    private String name;
    private String occupation;
    private int siblings;
    private double height;
    private boolean married;

    public LocalDate getBirthdate() {
        return birthdate;
    }

    private final LocalDate birthdate;
    public User(int serial, String name, String occupation, int siblings,
                double height, boolean married, LocalDate birthdate) {

        this.serial = serial;
        this.name = name;
        this.occupation = occupation;
        this.siblings = siblings;
        this.height = height;
        this.married = married;
        this.birthdate = birthdate;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getSiblings() {
        return siblings;
    }

    public void setSiblings(int siblings) {
        this.siblings = siblings;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return siblings == user.siblings && Double.compare(user.height, height) == 0
                && married == user.married && Objects.equals(name, user.name)
                && Objects.equals(occupation, user.occupation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, occupation, siblings, height, married);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OLD.User{");
        sb.append("serial='").append(serial).append('\'');
        sb.append("name='").append(name).append('\'');
        sb.append(", occupation='").append(occupation).append('\'');
        sb.append(", siblings=").append(siblings);
        sb.append(", height=").append(height);
        sb.append(", married=").append(married);
        sb.append(", birthdate=").append(birthdate);
        sb.append("}");
        return sb.toString();
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("serial", serial);
        json.put("name", name);
        json.put("occupation", occupation);
        json.put("siblings", siblings);
        json.put("height", height);
        json.put("married", married);
        json.put("birthdate", birthdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return json;
    }
}