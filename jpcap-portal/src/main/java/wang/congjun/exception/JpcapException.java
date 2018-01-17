package wang.congjun.exception;

import wang.congjun.enums.ResultEnum;

public class JpcapException extends RuntimeException {

    private Integer code;

    public JpcapException(ResultEnum resultEnum){
        this(resultEnum.getCode(),resultEnum.getMsg());
    }

    public JpcapException(Integer code,String msg){
        super(msg);
        this.code = code;
    }
}
