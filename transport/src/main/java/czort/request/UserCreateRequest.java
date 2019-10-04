package czort.request;

import czort.validator.UniqueLogin;

import java.util.List;

public class UserCreateRequest implements UserRequest {
    @UniqueLogin
    private String name;
    private String email;
    private List<String> items;
    private Integer value;

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

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public UserCreateRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserCreateRequest() {
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
