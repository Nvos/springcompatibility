package czort.request;

public class AdminCreateRequest {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AdminCreateRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public AdminCreateRequest() {
    }
}
