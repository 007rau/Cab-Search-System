package org.codejudge.sb.response;

public class GenericResponse<T> {
    private T data;

    public GenericResponse() {
    }

    public GenericResponse( T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
