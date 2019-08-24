package czort.crud;

import com.vaadin.ui.Component;
import czort.dialog.FormDialog;
import czort.grid.BaseGrid;
import czort.mvp.Presenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CrudContract {
    public interface Presenter<MODEL, CREATE, UPDATE> extends czort.mvp.Presenter<CrudViewFragment<MODEL, CREATE, UPDATE>> {
        public MODEL create(CREATE params);
        public MODEL update(Long id, UPDATE params);
        public MODEL find(Long id);
        public Page<MODEL> findAll(Pageable pageable, Map<String, String> filter);

        public void handleEdit(Long id);
        public void handleCreate();
        public Presenter<MODEL, CREATE, UPDATE> setTypes(
                Class<MODEL> modelType,
                Class<CREATE> createType,
                Class<UPDATE> updateType
        );
    }

    public interface View<MODEL, CREATE, UPDATE> {
        public FormDialog openUpdateDialog(Long id, UPDATE params);
        public FormDialog openCreateDialog(CREATE params);
        public BaseGrid<MODEL> getGrid();
    }
}
