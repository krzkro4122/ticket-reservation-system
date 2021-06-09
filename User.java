public class User {
    // Private fields
    private String pesel, password, fullName;

    // Constructor with params
    User(String pesel, String password, String fullName){
        this.pesel = pesel;
        this.password = password;
        this.fullName = fullName;
    }

    // Getters
    public String GetPesel(){ return this.pesel; }
    public String GetPassword(){ return this.password; }
    public String GetfullName(){ return this.fullName; }

    // Setters
    public void SetPesel(String pesel){ this.pesel = pesel; }
    public void SetPassword(String password){ this.password = password; }
    public void SetFullName(String fullName){ this.fullName = fullName; }

    // toString
    @Override
    public String toString(){
        return (
            "Pesel: " + pesel +
            "\nPassword: " + password +
            "\nFullName: " + fullName +
            "\n"
        );
    }
}
