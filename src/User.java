import java.util.Objects;
import java.time.LocalDate;
import java.time.Period;

public class User {

    private int serial;
    private String name;
    private String occupation;
    private int siblings;
    private double height;
    private boolean married;
    private LocalDate birthdate;
    private int age;

    public User(int serial, String name, String occupation, int siblings,
                double height, boolean married, LocalDate birthdate) {

        this.serial = serial;
        this.name = name;
        this.occupation = occupation;
        this.siblings = siblings;
        this.height = height;
        this.married = married;
        this.birthdate = birthdate;
        this.age = Period.between(birthdate, LocalDate.now()).getYears();
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate bdate) {
        this.birthdate = bdate;
        this.age = Period.between(bdate, LocalDate.now()).getYears();
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

    public int getAge() {
        return age;
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
        return Objects.hash(name, occupation, siblings, height, married, birthdate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("serial='").append(serial).append('\'');
        sb.append("name='").append(name).append('\'');
        sb.append(", occupation='").append(occupation).append('\'');
        sb.append(", siblings=").append(siblings);
        sb.append(", height=").append(height);
        sb.append(", married=").append(married);
        sb.append(", birthdate=").append(birthdate);
        sb.append(", age=").append(getAge());
        sb.append('}');
        return sb.toString();
    }
}