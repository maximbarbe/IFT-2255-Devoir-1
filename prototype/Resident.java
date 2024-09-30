public class Resident extends User{
    
    private String birthday;
    private String adresse;

    public Resident(String name, String birthday, String email, String password, String adresse) {
        super(name, email, password);
        this.birthday = birthday;
        this.adresse =adresse;
    }
    
}
