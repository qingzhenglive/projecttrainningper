package testFreeMarker;

public class User {
	private String username;

    private String password;

    private Integer age;

    private String address;

    
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(String username, String password, Integer age, String address) {
        super();
        this.username = username;
        this.password = password;
        this.age = age;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
