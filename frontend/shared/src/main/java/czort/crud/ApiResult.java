package czort.crud;

import czort.dialog.DialogResult;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class ApiResult<VALUE> {
    private VALUE value;
    private Result result;
    private Map<String, List<String>> errors;

    public ApiResult(ApiResult.Result result, VALUE value) {
        this.result = result;
        this.value = value;
    }

    public ApiResult(ApiResult.Result result) {
        this.result = result;
    }

    public ApiResult(ApiResult.Result result, Map<String, List<String>> errors) {
        this.result = result;
        this.errors = errors;
    }

    public static <VALUE> ApiResult<VALUE> of(Result result, VALUE value) {
        return new ApiResult<>(result, value);
    }

    public static <VALUE> ApiResult<VALUE> success(VALUE value) {
        return new ApiResult<>(Result.SUCCESS, value);
    }

    public static <VALUE> ApiResult<VALUE> error() {
        return new ApiResult<>(Result.ERROR);
    }

    public static <VALUE> ApiResult<VALUE> error(Map<String, List<String>> errors) {
        return new ApiResult<>(Result.ERROR, errors);
    }

    public boolean isSuccess() {
        return result == Result.SUCCESS;
    }

    public boolean isError() {
        return result == Result.ERROR;
    }

    public <RETURN> ApiResult<RETURN> flatMap(Function<VALUE, ApiResult<RETURN>> mapper) {

        if(this.isError()) {
            return ApiResult.error();
        }

        return mapper.apply(this.value);
    }

    public ApiResult<VALUE> onSuccess(Consumer<VALUE> onSuccessHandler) {
        if(this.isSuccess()) onSuccessHandler.accept(this.value);
        return this;
    }

    public ApiResult<VALUE> onError(Consumer<ApiResult<VALUE>> onErrorHandler) {
        if(this.isError()) onErrorHandler.accept(this);
        return this;
    }

    public enum Result {
        SUCCESS,
        ERROR
    }
}