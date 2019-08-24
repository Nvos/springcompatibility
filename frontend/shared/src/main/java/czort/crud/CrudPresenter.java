package czort.crud;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Component;
import czort.contract.CrudResourceContract;
import czort.dialog.FormDialog;
import czort.mvp.BasePresenter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@ViewScope
@SpringComponent
public class CrudPresenter<MODEL extends Object, CREATE extends Object, UPDATE extends Object>
        extends BasePresenter<CrudViewFragment<MODEL, CREATE, UPDATE>>
        implements CrudContract.Presenter<MODEL, CREATE, UPDATE>
{

    private final CrudResourceContract<MODEL, CREATE, UPDATE> crudClient;
    private Class<MODEL> modelType;
    private Class<CREATE> createType;
    private Class<UPDATE> updateType;

    public CrudPresenter(CrudResourceContract<MODEL, CREATE, UPDATE> crudClient) {
        this.crudClient = crudClient;
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
    public void handleEdit(Long id) {
        MODEL current = find(id);
        UPDATE updateProps = BeanUtils.instantiate(updateType);
        BeanUtils.copyProperties(current, updateProps);

        view.openUpdateDialog(id, updateProps);
    }

    @Override
    public void handleCreate() {
        CREATE createProps = BeanUtils.instantiate(createType);

        view.openCreateDialog(createProps);
    }

    public CrudPresenter<MODEL, CREATE, UPDATE> setTypes(
            Class<MODEL> modelType,
            Class<CREATE> createType,
            Class<UPDATE> updateType
    ) {
        this.modelType = modelType;
        this.createType = createType;
        this.updateType = updateType;

        return this;
    }

    public Class<MODEL> getModelType() {
        return modelType;
    }

    public Class<CREATE> getCreateType() {
        return createType;
    }

    public Class<UPDATE> getUpdateType() {
        return updateType;
    }
}
