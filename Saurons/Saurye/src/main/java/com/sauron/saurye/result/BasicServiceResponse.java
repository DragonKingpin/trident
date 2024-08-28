package com.sauron.saurye.result;

public class BasicServiceResponse<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    public static <T> BasicServiceResponse<T> success() {
        BasicServiceResponse<T> BasicServiceResponse = new BasicServiceResponse<T>();
        BasicServiceResponse.code = 200;
        return BasicServiceResponse;
    }

    public static <T> BasicServiceResponse<T> success(T object) {
        BasicServiceResponse<T> BasicServiceResponse = new BasicServiceResponse<T>();
        BasicServiceResponse.data = object;
        BasicServiceResponse.code = 200;
        return BasicServiceResponse;
    }

    public static <T> BasicServiceResponse<T> error(String msg) {
        BasicServiceResponse BasicServiceResponse = new BasicServiceResponse();
        BasicServiceResponse.msg = msg;
        BasicServiceResponse.code = 500;
        return BasicServiceResponse;
    }

    public static <T> BasicServiceResponse<T> error(Integer code ,String msg) {
        BasicServiceResponse BasicServiceResponse = new BasicServiceResponse();
        BasicServiceResponse.msg = msg;
        BasicServiceResponse.code = code;
        return BasicServiceResponse;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BasicServiceResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
