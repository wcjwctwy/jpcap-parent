package wang.congjun.converter;

import jpcap.packet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import wang.congjun.dto.PacketDTO;
import wang.congjun.enums.NetProtocolEnum;
import wang.congjun.enums.ResultEnum;
import wang.congjun.exception.JpcapException;
import wang.congjun.utils.MacUtil;

import java.net.InetAddress;

@Slf4j
public class Packet2PacketDTOConverter {

    public static PacketDTO convert(Packet p) {
        if (!ObjectUtils.isEmpty(p)) {

            if (p instanceof ARPPacket) {
                log.info("【转换包信息】ARP包转换");
               return arpConvert(p);
            }
            if (p instanceof IPPacket) {
                log.info("【转换包信息】IP包转换");
                return ipConvert(p);
            }
            log.info("【转换包信息】未知包");
            return null;
        }
        log.info("【转换包信息】空包");
        return null;
    }


    /**
     * 转换ARP消息
     * @param p
     * @return
     */
    public static PacketDTO arpConvert(Packet p) {
        PacketDTO packetDTO = new PacketDTO();
        //获取消息内容
        packetDTO.setData(p.data);
        //获取以太网的网卡信息
        DatalinkPacket datalink = p.datalink;
        if (datalink instanceof EthernetPacket) {
            EthernetPacket ethernetPacket = (EthernetPacket) datalink;
            packetDTO.setSrcMac(MacUtil.mactos(ethernetPacket.src_mac));
            packetDTO.setDecMac(MacUtil.mactos(ethernetPacket.dst_mac));

        }
        //添加协议
        packetDTO.setProtocol(NetProtocolEnum.ARPPROTOCOL.getCode());
        //强转Arp
        ARPPacket arpPacket = (ARPPacket) p;
        try {
            packetDTO.setSrcIp(InetAddress.getByAddress(arpPacket.sender_protoaddr).getHostAddress());
            packetDTO.setDecIp(InetAddress.getByAddress(arpPacket.target_protoaddr).getHostAddress());
        } catch (Exception e) {
            log.error("【转换包信息】ARP包获取ip地址失败");
            throw  new JpcapException(ResultEnum.CONVERT_ARP_FAILED);
        }
        return packetDTO;
    }


    /**
     * 转换IP消息
     * @param p
     * @return
     */
    public static PacketDTO ipConvert(Packet p) {
        PacketDTO packetDTO = new PacketDTO();
        //获取消息内容
        packetDTO.setData(p.data);
        //获取以太网的网卡信息
        DatalinkPacket datalink = p.datalink;
        if (datalink instanceof EthernetPacket) {
            EthernetPacket ethernetPacket = (EthernetPacket) datalink;
            packetDTO.setSrcMac(MacUtil.mactos(ethernetPacket.src_mac));
            packetDTO.setDecMac(MacUtil.mactos(ethernetPacket.dst_mac));

        }

        //强转Arp
        IPPacket ipPacket = (IPPacket) p;
        packetDTO.setVersion(ipPacket.version);
        try {
            packetDTO.setSrcIp(ipPacket.src_ip.getHostAddress());
            packetDTO.setDecIp(ipPacket.dst_ip.getHostAddress());
        } catch (Exception e) {
            log.error("【转换包信息】ARP包获取ip地址失败");
            throw  new JpcapException(ResultEnum.CONVERT_ARP_FAILED);
        }

        //添加协议
        packetDTO.setProtocol(NetProtocolEnum.IPPROTOCOL.getCode());

        if (p instanceof TCPPacket) {
            packetDTO.setProtocol(NetProtocolEnum.TCPPROTOCOL.getCode());
        }
        if (p instanceof UDPPacket) {
            packetDTO.setProtocol(NetProtocolEnum.UDPPROTOCOL.getCode());
        }
        if (p instanceof ICMPPacket) {
            packetDTO.setProtocol(NetProtocolEnum.ICMPPROTOCOL.getCode());
        }
        log.info("【转换包信息】ip包转换结果：packetDTO={}",packetDTO);
        return packetDTO;
    }


}
