package com.wx.contact.domain.enums;

public enum UserCheckStatus
{
    CHECK_STATUS_01("审核中", 01),
    CHECK_STATUS_00("审核通过", 00),
    CHECK_STATUS_02("审核拒绝",02);

    private String message;
    private int code;

    UserCheckStatus(String message, int code)
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
