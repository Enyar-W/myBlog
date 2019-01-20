package org.tl.blog.common.utils;

public class AjaxResponse {

    private Boolean success = false;

    private String msg = "Oh!The server is gua diao le!";

    private Object obj;

    public AjaxResponse() {
    }

    public AjaxResponse(Boolean success, String msg, Object obj) {
        this.success = success;
        this.msg = msg;
        this.obj = obj;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
        if(success){
            setMsg("this request is success!");
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
