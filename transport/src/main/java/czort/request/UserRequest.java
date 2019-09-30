package czort.request;

import java.util.List;

public interface UserRequest {
    String getName();
    void setName(String name);
    String getEmail();
    void setEmail(String email);
    List<String> getItems();
    void setItems(List<String> items);
}
