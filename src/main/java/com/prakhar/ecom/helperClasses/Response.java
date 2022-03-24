package com.prakhar.ecom.helperClasses;

import java.util.Date;

public class Response {
    private Boolean success;
    private Object data;
    private Date date;
    private String message;

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", data=" + data +
                ", date=" + date +
                ", message='" + message + '\'' +
                '}';
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Response(Boolean success, Object data,String message) {
        this.success = success;
        this.data = data;
        this.date = new Date();
        this.message=message;
    }
}
