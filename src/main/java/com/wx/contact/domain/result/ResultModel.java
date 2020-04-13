package com.wx.contact.domain.result;


import com.wx.contact.domain.enums.ResponsesStatus;
import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class ResultModel<T> {
    private int code;
    private String message;
    private T data;

    //禁止直接setData
    private void setData() {
    }

    public ResultModel() {
        this.data = null;
        this.code = ResponsesStatus.ERROR.getCode();
        this.message = ResponsesStatus.ERROR.getMessage();
    }

    public void setResult(ResponsesStatus responsesStatus, T data) {
        this.data = data;
        this.code = responsesStatus.getCode();
        this.message = responsesStatus.getMessage();
    }

    public void setResult(ResponsesStatus responsesStatus) {
        this.data = null;
        this.code = responsesStatus.getCode();
        this.message = responsesStatus.getMessage();
    }

    /**
     * 通用查询成功返回json
     *
     * @param data 需要返回的结果集
     * @return
     */
    public static <T> ResultModel<T> success(Object data) {
        ResultModel<T> resultModel = new ResultModel<>();
        resultModel.setResult((T) data);
        return resultModel;
    }

    /**
     * 通用失败返回json
     */
    public static ResultModel error() {
        return new ResultModel();
    }

    /**
     * 失败指定返回信息
     */
    public static ResultModel error(ResponsesStatus responsesStatus) {
        ResultModel resultModel = new ResultModel();
        resultModel.setResult(responsesStatus);
        return resultModel;
    }

    public void setResult(T data) {
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