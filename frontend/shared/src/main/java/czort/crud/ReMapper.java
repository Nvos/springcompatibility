package czort.crud;

import com.vaadin.data.ValueProvider;

import java.util.function.Function;
import java.util.function.Supplier;

public class ReMapper<MODEL, CREATE, UPDATE> {
    private ValueProvider<MODEL, Long> idProvider;
    private Supplier<CREATE> createProvider;
    private Function<MODEL, UPDATE> updateProvider;

    public ReMapper(ValueProvider<MODEL, Long> idProvider, Supplier<CREATE> createProvider, Function<MODEL, UPDATE> updateProvider) {
        this.idProvider = idProvider;
        this.createProvider = createProvider;
        this.updateProvider = updateProvider;
    }

    public ValueProvider<MODEL, Long> getIdProvider() {
        return idProvider;
    }

    public Supplier<CREATE> getCreateProvider() {
        return createProvider;
    }

    public Function<MODEL, UPDATE> getUpdateProvider() {
        return updateProvider;
    }

    public ReMapper<MODEL, CREATE, UPDATE> withIdProvider(ValueProvider<MODEL, Long> idProvider) {
        this.idProvider = idProvider;
        return this;
    }

    public ReMapper<MODEL, CREATE, UPDATE> withCreateProvider(Supplier<CREATE> createProvider) {
        this.createProvider = createProvider;
        return this;
    }

    public ReMapper<MODEL, CREATE, UPDATE> withUpdateProvider(Function<MODEL, UPDATE> updateProvider) {
        this.updateProvider = updateProvider;
        return this;
    }
}
