abstract public class Person {

    protected String id, name, role, email, DOB;

    public Person(){
        this.name = "null";
        this.id = "null";
        this.role = "default";
        this.email = "null";
        this.DOB = "00/00/0000";
    }
    public Person(String id, String name, String role, String email, String DOB){
        this.name = name;
        this.id = id;
        this.role = role;
        this.email = email;
        this.DOB = DOB;
    }

}