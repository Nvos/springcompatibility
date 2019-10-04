package czort.request;

import czort.validator.UniqueLogin;

import java.util.List;

public class UserUpdateRequest implements UserRequest {
    private int id;
    @UniqueLogin
    private String name;
    private String email;
    private List<String> items;
    private Integer value;

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
