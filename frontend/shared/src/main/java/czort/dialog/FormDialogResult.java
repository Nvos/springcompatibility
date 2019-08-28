package czort.dialog;

import java.util.function.Consumer;
import java.util.function.Function;

public class FormDialogResult<MODEL> {
    private final Result result;
    private MODEL value;

    public FormDialogResult(Result result, MODEL value) {
        this.result = result;
        this.value = value;
    }

    public FormDialogResult(Result result) {
        this.result = result;
    }

    public static <MODEL> FormDialogResult<MODEL> of(Result result, MODEL value) {
        return new FormDialogResult<>(result, value);
    }

    public static <MODEL> FormDialogResult<MODEL> accept(MODEL value) {
        return new FormDialogResult<>(Result.ACCEPT, value);
    }

    public static <MODEL> FormDialogResult<MODEL> cancel() {
        return new FormDialogResult<>(Result.CANCEL);
    }

    public boolean isCancel() {
        return this.result == Result.CANCEL;
    }

    public boolean isAccept() {
        return this.result == Result.ACCEPT;
    }

    public<RETURN> FormDialogResult<RETURN> flatMap(Function<MODEL, FormDialogResult<RETURN>> mapper) {

        if(this.isCancel()) {
            return FormDialogResult.cancel();
        }

        return mapper.apply(this.value);
    }

    public FormDialogResult<MODEL> onAccept(Consumer<MODEL> onAcceptHandler) {
        if(this.isAccept()) onAcceptHandler.accept(this.value);
        return this;
    }

    public FormDialogResult<MODEL> onCancel(Consumer<FormDialogResult<MODEL>> onCancelHandler) {
        if(this.isCancel()) onCancelHandler.accept(this);
        return this;
    }
}
