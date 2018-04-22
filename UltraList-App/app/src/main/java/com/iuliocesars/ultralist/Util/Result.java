package com.iuliocesars.ultralist.Util;

/**
 * Created by IulioCesars on 22/04/2018.
 */

public class Result<T> {
    private Boolean success;
    private T value;

    public Result(Boolean success, T value) {
        success = success;
        this.value = value;
    }

    public Result(Boolean success) {
        success = success;
        this.value = null;
    }

    public Result() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
