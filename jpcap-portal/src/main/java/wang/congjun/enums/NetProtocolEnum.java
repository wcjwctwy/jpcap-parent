package wang.congjun.enums;

import lombok.Getter;

@Getter
public enum  NetProtocolEnum {
    IPPROTOCOL(1,"ip协议")
    ,ARPPROTOCOL(2,"arp协议")
    ,REALTELPROTOCOL(3,"以太网协议")
    ,ICMPPROTOCOL(4,"icmp协议")
    ,TCPPROTOCOL(5,"TCP协议")
    ,UDPPROTOCOL(6,"UDP协议")
    ;
    private Integer code;
    private String msg;

    NetProtocolEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
