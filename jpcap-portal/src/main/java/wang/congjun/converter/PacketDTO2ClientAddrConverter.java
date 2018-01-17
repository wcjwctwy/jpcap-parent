package wang.congjun.converter;

import wang.congjun.dataobject.ClientAddr;
import wang.congjun.dto.PacketDTO;

import java.util.Date;

public class PacketDTO2ClientAddrConverter {

    public static ClientAddr convert(PacketDTO packetDTO){
        ClientAddr clientAddr = new ClientAddr();
        Byte version = packetDTO.getVersion();
        if(version!=null) {
            int v= Integer.valueOf(version);
            if (v == 4) {
                clientAddr.setIpAddr(packetDTO.getSrcIp());
            }
            if (v == 6) {
                clientAddr.setIpAddr6(packetDTO.getSrcIp());
            }
        }
        clientAddr.setMacAddr(packetDTO.getSrcMac());
        clientAddr.setProtocol(packetDTO.getProtocol());
        clientAddr.setStatus(Short.valueOf("0"));
        Date date = new Date();
        clientAddr.setCreateTime(date);
        clientAddr.setUpdateTime(date);
        return clientAddr;
    }

}
