//2016-18223 Jane Shin
public class ContactEntry {

    //class fields (leave them as private)
    private String name;
    private int phoneNumber;
    private String occupation; //Student, Professor, or TA


    public ContactEntry() {
        name = null;
        phoneNumber = 0;
        occupation = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getName() {
        return this.name;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public boolean equals(ContactEntry obj) {
        if((this.getName()).equals(obj.getName()) && (this.getOccupation()).equals(obj.getOccupation()) && this.getPhoneNumber() == obj.getPhoneNumber())
            return true;
        else return false;
    }
}
