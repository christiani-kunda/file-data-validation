package exercise.rssb.rw.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class Response<T> implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Response.class);

    private Boolean status;
    private T data;
    private String message;

    public Response(Exception exception){
        LOGGER.error(ExceptionUtils.getStackTrace(exception));
        this.status = false;
        this.data = null;
        this.message = exception.getMessage();
    }

    public Response(Exception exception, String message){
        LOGGER.error(ExceptionUtils.getStackTrace(exception));
        this.status = false;
        this.data = null;
        this.message = message;
    }

    public Response(T data) {
        this.status = true;
        this.data = data;
        this.message = "SUCCESS";
    }

    public Response() {

    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}