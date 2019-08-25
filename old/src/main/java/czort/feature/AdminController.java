package czort.feature;

import czort.contract.CrudResourceContract;
import czort.entity.AdminEntity;
import czort.entity.UserEntity;
import czort.repository.AdminRepository;
import czort.repository.UserRepository;
import czort.request.AdminCreateRequest;
import czort.request.AdminUpdateRequest;
import czort.request.UserCreateRequest;
import czort.request.UserUpdateRequest;
import czort.response.AdminResponse;
import czort.response.CountResponse;
import czort.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RequestMapping("/admin")
@RestController()
public class AdminController implements CrudResourceContract<AdminResponse, AdminCreateRequest, AdminUpdateRequest> {

    private final AdminRepository repository;

    @Autowired
    public AdminController(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<AdminResponse> create(@RequestBody AdminCreateRequest params) {
        AdminEntity entity = new AdminEntity(
                params.getName(),
                params.getEmail()
        );

        repository.save(entity);

        return ResponseEntity.ok(toResponse(entity));
    }

    @Override
    public ResponseEntity<AdminResponse> update(@PathVariable Long id, @RequestBody AdminUpdateRequest params) {
        AdminEntity entity = repository.findOne(id);
        entity.setEmail(params.getEmail());
        entity.setName(params.getName());

        return ResponseEntity.ok(toResponse(entity));
    }

    @Override
    public ResponseEntity delete(@PathVariable Long id) {
        repository.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Page<AdminResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable).map(this::toResponse));
    }

    @Override
    public ResponseEntity<CountResponse> count() {
        return ResponseEntity.ok(new CountResponse((int) repository.count()));
    }

    @Override
    public ResponseEntity<AdminResponse> find(@PathVariable Long id) {

        return ResponseEntity.ok(toResponse(repository.findOne(id)));
    }

    private AdminResponse toResponse(AdminEntity entity) {
        AdminResponse response = new AdminResponse();
        response.setEmail(entity.getEmail());
        response.setName(entity.getName());
        response.setId(entity.getId());

        return response;
    }
}
