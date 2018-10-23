package com.masaiqi.json;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台处理结果封装对象
 * @param <T>
 */
@Data
public class JsonResult<T> {

    private Boolean success = false;//操作成功标记 true-成功 false-失败
    private String msg;//消息
    private List<T> datas;//数据

    public JsonResult(String msg) {
        this.success = false;
        this.msg = msg;
    }

    public JsonResult(List<T> datas) {
        this.success = true;
        this.datas = datas;
    }

    public JsonResult(Boolean success, String msg, List<T> datas) {
        this.success = success;
        this.msg = msg;
        this.datas = datas;
    }

    public JsonResult() {
        this.success = false;
    }

    public void setDate(T t){
        List<T> lists = new ArrayList<>(0);
        lists.add(t);
        this.datas = lists;
        this.success = true;
    }
}
