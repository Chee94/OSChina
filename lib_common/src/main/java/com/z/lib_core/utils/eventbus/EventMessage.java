package com.z.lib_core.utils.eventbus;

/**
 * Creator:  chee
 * Date：2020/9/10 - 17:18
 * Desc：
 */
public class EventMessage<T> {

    private int code;
    private T data;

    public EventMessage(int code) {
        this.code = code;
    }


    public EventMessage(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public static EventMessage newInstance(int code) {
        EventMessage message = new EventMessage(code);
        return message;
    }

    public static EventMessage newInstance(int code, Object o) {
        EventMessage message = new EventMessage(code, o);
        return message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

}
