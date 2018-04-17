package example.entity;

import java.io.Serializable;


public class JResult implements Serializable {

    private int code;//对应StatusCode里的错误编码
    private Object content;//返回值
    private String msg;//对应StatusCode里的汉字说明


    public int getCode() {
        return code;
    }

    public JResult setCode(int code) {
        this.code = code;
        return this;
    }

    public Object getContent() {
        return content;
    }

    public JResult setContent(Object content) {
        this.content = content;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

}

