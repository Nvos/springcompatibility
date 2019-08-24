package czort.contract;

import czort.response.CountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface CrudResourceContract<MODEL, CREATE, UPDATE> {

    @PostMapping
    public ResponseEntity<MODEL> create(@RequestBody CREATE params);

    @PutMapping("/{id}")
    public ResponseEntity<MODEL> update(@PathVariable("id") Long id, @RequestBody UPDATE params);

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id);

    @GetMapping
    public ResponseEntity<Page<MODEL>> findAll(Pageable pageable);

    @GetMapping("/count")
    public ResponseEntity<CountResponse> count();

    @GetMapping("/{id}")
    public ResponseEntity<MODEL> find(@PathVariable("id") Long id);
}
