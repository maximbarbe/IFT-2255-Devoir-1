public class Intervenant extends User{
    
    private String type;
    private String id;
    public Intervenant(String name, String email, String password, String type, String id) {
        super(name, email, password);
        this.type = type;
        this.id = id;
    }
}
