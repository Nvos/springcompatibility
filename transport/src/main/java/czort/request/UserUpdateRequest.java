package czort.request;

public class UserUpdateRequest implements UserRequest {
    private int id;
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

    public int getId() {
        return id;
    }

    public UserUpdateRequest setId(int id) {
        this.id = id;
        return this;
    }

    public UserUpdateRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserUpdateRequest() {
    }
}
