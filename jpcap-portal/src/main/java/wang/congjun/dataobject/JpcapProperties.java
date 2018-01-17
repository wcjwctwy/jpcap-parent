package wang.congjun.dataobject;

import lombok.Data;

@Data
public class JpcapProperties {
    /**
     * 网卡接口的下标
     * 一般获取的是一个网卡列表，如果只有一个那就为0
     * 多个就需要从中选择一个
     */
   private int intrface;
    /**
     * 每次从请求中抓取的数据最大长度
     */
   private int snaplen;
    /**
     * 设置是否混杂模式。处于混杂模式将接收所有数据包，
     * 若之后又调用了包过滤函数setFilter()将不起任何作用；
     */
   private Boolean promisc;
    /**
     * 这个参数主要用于processPacket()方法，指定超时的时间
     */
   private int to_ms;
    /**
     * 本机的ip地址
     */
   private String localIp;
    /**
     * 本机的mac地址
     */
   private String localMac;
}
