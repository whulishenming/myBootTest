package com.lsm.boot.shiro.vo;

public enum ResultCode {

    C200(200, "Success"),
    
    C203(203, "No Enough Authority"),

    C204(204, "No Content"),

    C301(301, "Request Error"),

    C400(400, "Client Error"),

    C403(403, "Forbidden"),

    C401(401, "Unauthorized Auth"),

    C412(412, "参数错误"),

    C415(415, "Error Format"),

    C422(422, "Error Valid"),

    C500(500, "Internal Server Error"),

    C501(501, "User No Login"),

    C502(502, "页面url不能重复"),

    C601(601,"外部接口调用出错");

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;

    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
