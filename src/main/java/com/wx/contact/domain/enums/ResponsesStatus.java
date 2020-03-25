package com.wx.contact.domain.enums;

public enum ResponsesStatus
{
    SUCCESS("查询成功", 200),
    NODATA("查询成功,暂无数据", 201),
    INSERTERROR("插入失败",998),
    MODEL_NAME_NULL("模型名称不能为空！",1001),
    MODEL_REPEAT("模型重复！",1002),
    PROCESS_NOID("没有人员ID",2001),
    NORECORD("没有记录！",998),
    PARAMS_ERROR("参数错误",2002),
    ERROR("内部错误",999),
    EXIST("该手机号已经存在",111);

    private String message;
    private int code;

    ResponsesStatus(String message, int code)
    {
        this.message = message;
        this.code = code;
    }

    public String getMessage()
    {
        return this.message;
    }

    public int getCode()
    {
        return this.code;
    }
}
