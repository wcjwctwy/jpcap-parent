package wang.congjun.enums;


import lombok.Getter;

@Getter
public enum ResultEnum {

    GET_INTERFACE_FAILED(1,"获取网卡接口失败")
    ,SAVE_CLIENTADDR_FAILED(2,"存储获取的包信息失败")
    ,CONVERT_ARP_FAILED(3,"转换ARP失败")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
