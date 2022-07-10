package com.eastshine.rapcrew.utl;

import org.springframework.http.HttpStatus;

public class ApiResponse {
	
private int status;
    
    private Object data;

    private String error;

    private String message;


    public ApiResponse() {
        this(HttpStatus.OK.value());
    }


    public ApiResponse(int status) {
        super();
        this.status = status;
    }


    public ApiResponse(HttpStatus status) {
        this(status.value());
    }


    public ApiResponse(Object data) {
        this.status = HttpStatus.OK.value();
        this.data = data;
    }


    public ApiResponse(int status, String error) {
        this.status = status;
        this.error = error;
    }


    public ApiResponse(HttpStatus status, String error) {
        this.status = status.value();
        this.error = error;
    }

    public static ApiResponse ok() {
        return new ApiResponse();
    }

    public static ApiResponse ok(Object data) {
        return new ApiResponse(data);
    }

    public static ApiResponse error(HttpStatus status, String error) {
        return new ApiResponse(status, error);
    }


    public int getStatus() {
        return status;
    }


    public void setStatus(int status) {
        this.status = status;
    }


    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }


    public String getError() {
        return error;
    }


    public void setError(String error) {
        this.error = error;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ApiResponse [status=");
        builder.append(status);
        builder.append(", ");
        if (data != null) {
            builder.append("data=");
            builder.append(data);
            builder.append(", ");
        }
        if (error != null) {
            builder.append("error=");
            builder.append(error);
            builder.append(", ");
        }
        if (message != null) {
            builder.append("message=");
            builder.append(message);
        }
        builder.append("]");
        return builder.toString();
    }

}
