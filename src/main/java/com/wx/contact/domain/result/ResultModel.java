package com.wx.contact.domain.result;


import com.wx.contact.domain.enums.ResponsesStatus;
import lombok.Data;

@Data
public class ResultModel
{
    private int code;
    private String message;
    private Object data;

    //禁止直接setData
    private void setData(){}

    public ResultModel()
    {
        this.data = null;
        this.code = ResponsesStatus.ERROR.getCode();
        this.message = ResponsesStatus.ERROR.getMessage();
    }

    public void setResult(ResponsesStatus responsesStatus, Object data)
    {
        this.data = data;
        this.code = responsesStatus.getCode();
        this.message = responsesStatus.getMessage();
    }

    public void setResult(ResponsesStatus responsesStatus)
    {
        this.data = null;
        this.code = responsesStatus.getCode();
        this.message = responsesStatus.getMessage();
    }

    public void setResult(Object data)
    {
        if (data == null) {
            this.data = null;
            this.code = ResponsesStatus.NODATA.getCode();
            this.message = ResponsesStatus.NODATA.getMessage();
        } else {
            this.data = data;
            this.code = ResponsesStatus.SUCCESS.getCode();
            this.message = ResponsesStatus.SUCCESS.getMessage();
        }
    }

}