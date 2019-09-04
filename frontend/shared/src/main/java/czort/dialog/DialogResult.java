package czort.dialog;

import java.util.function.Consumer;
import java.util.function.Function;

public class DialogResult<VALUE> {
    private final Result result;
    private VALUE value;

    public DialogResult(Result result, VALUE value) {
        this.result = result;
        this.value = value;
    }

    public DialogResult(Result result) {
        this.result = result;
    }

    public static <VALUE> DialogResult<VALUE> of(Result result, VALUE value) {
        return new DialogResult<>(result, value);
    }

    public static <VALUE> DialogResult<VALUE> accept(VALUE value) {
        return new DialogResult<>(Result.ACCEPT, value);
    }

    public static <VALUE> DialogResult<VALUE> cancel() {
        return new DialogResult<>(Result.CANCEL);
    }

    public boolean isCancel() {
        return this.result == Result.CANCEL;
    }

    public boolean isAccept() {
        return this.result == Result.ACCEPT;
    }

    public<RETURN> DialogResult<RETURN> flatMap(Function<VALUE, DialogResult<RETURN>> mapper) {

        if(this.isCancel()) {
            return DialogResult.cancel();
        }

        return mapper.apply(this.value);
    }

    public DialogResult<VALUE> onAccept(Consumer<VALUE> onAcceptHandler) {
        if(this.isAccept()) onAcceptHandler.accept(this.value);
        return this;
    }

    public DialogResult<VALUE> onCancel(Consumer<DialogResult<VALUE>> onCancelHandler) {
        if(this.isCancel()) onCancelHandler.accept(this);
        return this;
    }
}
