package czort.crud;

import com.googlecode.gentyref.TypeToken;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Component;
import czort.contract.CrudResourceContract;
import czort.dialog.FormDialog;
import czort.dialog.Result;
import czort.form.Form;
import czort.form.StandardForm;
import czort.mvp.BasePresenter;
import feign.FeignException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@ViewScope
@SpringComponent
public abstract class CrudPresenter<MODEL extends Object, CREATE extends Object, UPDATE extends Object>
        extends BasePresenter<CrudViewFragment<MODEL, CREATE, UPDATE>> {

    @Autowired
    private ApplicationContext context;

    private final CrudResourceContract<MODEL, CREATE, UPDATE> crudClient;
    private final ReMapper<MODEL, CREATE, UPDATE> reMapper;
    private GenericCrudDataProvider<MODEL> dataProvider;

    protected abstract ReMapper<MODEL, CREATE, UPDATE> getReMapper();

    public CrudPresenter(
            CrudResourceContract<MODEL, CREATE, UPDATE> crudClient
    ) {
        this.crudClient = crudClient;
        this.reMapper = getReMapper();
        this.dataProvider = new GenericCrudDataProvider<>(this);
    }

    public MODEL create(CREATE params) {
        return crudClient.create(params).getBody();
    }

    public MODEL update(Long id, UPDATE params) {
        return crudClient.update(id, params).getBody();
    }

    public MODEL find(Long id) {
        return crudClient.find(id).getBody();
    }

    public Page<MODEL> findAll(Pageable pageable) {
        return crudClient.findAll(pageable).getBody();
    }

    public int count() {
        return crudClient.count().getBody().getCount();
    }

    public Page<MODEL> findAll(Pageable pageable, Map<String, String> filter) {
        return crudClient.findAll(pageable).getBody();
    }

    public void handleEdit(MODEL model) {
        Long id = reMapper.getIdProvider().apply(model);
        MODEL current = find(id);

        FormDialog<UPDATE> dialog = view.openUpdateDialog(id, reMapper.getUpdateProvider().apply(current));
        dialog.withResultHandler(result -> result
                .onAccept(value -> {
                    update(id, value);
                    view.getDataProvider().refreshAll();

                    dialog.closeWithoutPrompt();
                })
                .onCancel(cancelResult -> {
                    dialog.close();
                })
        );
    }

    public void handleCreate() {
        view.openCreateDialog(reMapper.getCreateProvider().get()).with(self -> self
            .withResultHandler(result -> result
            .onAccept(value -> {
                createErrorHandlerFn.apply(value, this::create)
                    .onError(error -> {
                    // get Form from self
                    // Set errors as needed
                    })
                    .onSuccess($ -> {
                        view.getDataProvider().refreshAll();
                        self.closeWithoutPrompt();
                    });
            })
            .onCancel(cancelResult -> {
                self.close();
            })
        ));
    }

    private BiFunction<CREATE, Function<CREATE, MODEL>, ApiResult<MODEL>> createErrorHandlerFn = (params, createFn) -> {
        try {
            return ApiResult.success(createFn.apply(params));
        } catch (FeignException ex) {
            if (ex.status() >= 500) {
                // Something's fucked, I guess gotta log this?
                System.out.println("monkaS...");
            }

            return ApiResult.error();
        }
    };

    public GenericCrudDataProvider<MODEL> getDataProvider() {
        return dataProvider;
    }
}
