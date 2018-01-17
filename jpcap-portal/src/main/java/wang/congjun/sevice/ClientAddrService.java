package wang.congjun.sevice;

import jpcap.packet.Packet;
import wang.congjun.dataobject.ClientAddr;

public interface ClientAddrService {

    /**
     * 获取主机信息存储
     */
    void save();

    /**
     * 获取主机信息存储
     */
    ClientAddr save(Packet p);

    /**
     * 更具ip查询主机信息
     */
    void findByIp();
}
