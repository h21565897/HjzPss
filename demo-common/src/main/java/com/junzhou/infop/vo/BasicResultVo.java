package com.junzhou.infop.vo;

import com.junzhou.infop.enums.RespStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BasicResultVo<T> {


    private String status;
    private String msg;

    private T data;

    public BasicResultVo(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static <T> BasicResultVo<T> success() {
        return new BasicResultVo<>(RespStatusEnum.SUCCESS.getCode(), null, null);
    }

    public static <T> BasicResultVo<T> success(String msg, T data) {
        return new BasicResultVo<>(RespStatusEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> BasicResultVo<T> success(T data) {
        return new BasicResultVo<>(RespStatusEnum.SUCCESS.getCode(), null, data);
    }

    public static <T> BasicResultVo<T> success(String msg) {
        return new BasicResultVo<>(RespStatusEnum.SUCCESS.getCode(), msg, null);
    }

    public static <T> BasicResultVo<T> fail(String msg) {
        return fail(RespStatusEnum.FAIL, msg);
    }

    public static <T> BasicResultVo<T> fail(RespStatusEnum status, String msg) {
        return new BasicResultVo<>(status.getCode(), msg, null);

    }
}
