package czort.crud;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Component;
import czort.contract.CrudResourceContract;
import czort.dialog.FormDialog;
import czort.mvp.BasePresenter;
import jdk.internal.org.objectweb.asm.commons.Remapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@ViewScope
@SpringComponent
public abstract class CrudPresenter<MODEL extends Object, CREATE extends Object, UPDATE extends Object>
        extends BasePresenter<CrudViewFragment<MODEL, CREATE, UPDATE>>
        implements CrudContract.Presenter<MODEL, CREATE, UPDATE>
{

    private final CrudResourceContract<MODEL, CREATE, UPDATE> crudClient;
    private final ReMapper<MODEL, CREATE, UPDATE> reMapper;

    protected abstract ReMapper<MODEL, CREATE, UPDATE> getReMapper();

    public CrudPresenter(CrudResourceContract<MODEL, CREATE, UPDATE> crudClient) {
        this.crudClient = crudClient;
        this.reMapper = getReMapper();
    }

    public MODEL create(CREATE params) {
        return crudClient.create(params).getBody();
    }

    public MODEL update(Long id, UPDATE params) {
        this.view.openUpdateDialog(id, params);
        return crudClient.update(id, params).getBody();
    }

    public MODEL find(Long id) {
        return crudClient.find(id).getBody();
    }

    public Page<MODEL> findAll(Pageable pageable, Map<String, String> filter) {
        return crudClient.findAll(pageable).getBody();
    }

    @Override
    public void handleEdit(MODEL model) {
        Long id = reMapper.getIdProvider().apply(model);
        MODEL current = find(id);

        view.openUpdateDialog(id, reMapper.getUpdateProvider().apply(current));
    }

    @Override
    public void handleCreate() {
        view.openCreateDialog(reMapper.getCreateProvider().get());
    }
}
