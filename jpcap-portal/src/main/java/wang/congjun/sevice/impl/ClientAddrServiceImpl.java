package wang.congjun.sevice.impl;

import jpcap.JpcapCaptor;
import jpcap.JpcapSender;
import jpcap.packet.Packet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import wang.congjun.converter.Packet2PacketDTOConverter;
import wang.congjun.converter.PacketDTO2ClientAddrConverter;
import wang.congjun.dataobject.ClientAddr;
import wang.congjun.dto.PacketDTO;
import wang.congjun.enums.ResultEnum;
import wang.congjun.exception.JpcapException;
import wang.congjun.pkghandler.SavePkgHandler;
import wang.congjun.repository.ClientAddrRepository;
import wang.congjun.sevice.ClientAddrService;

import java.util.Date;

@Service
@Slf4j
public class ClientAddrServiceImpl implements ClientAddrService {

    @Autowired
    private JpcapCaptor jpcapCaptor;

    @Autowired
    private ClientAddrRepository clientAddrRepository;

    @Autowired
    private JpcapSender jpcapSender;

    @Override
    public void save() {
        jpcapCaptor.loopPacket(-1,p -> {
//            jpcapSender.sendPacket(p);
            save(p);
        });
    }

    @Override
    public ClientAddr save(Packet p) {
        PacketDTO convert = Packet2PacketDTOConverter.convert(p);
        log.info("【存储主机信息】转换包信息 convert={}",convert);
        if(ObjectUtils.isEmpty(convert)) return null;

        try {
            //查询mac是否存在
            ClientAddr clientAddrByMacAddr = clientAddrRepository.findClientAddrByMacAddr(convert.getSrcMac());
            ClientAddr clientAddr = PacketDTO2ClientAddrConverter.convert(convert);
            if(ObjectUtils.isEmpty(clientAddrByMacAddr)) {
                log.info("【存储主机信息】第一次添加 clientAddr={}",clientAddr);
                return clientAddrRepository.save(clientAddr);
            }
            clientAddr.setId(clientAddrByMacAddr.getId());
            String ipAddr = clientAddrByMacAddr.getIpAddr();
            if(!ObjectUtils.isEmpty(ipAddr))clientAddr.setIpAddr(ipAddr);
            String ipAddr6 = clientAddrByMacAddr.getIpAddr6();
            if(!ObjectUtils.isEmpty(ipAddr6))clientAddr.setIpAddr6(ipAddr6);
            String macAddr = clientAddrByMacAddr.getMacAddr();
            if(!ObjectUtils.isEmpty(macAddr))clientAddr.setMacAddr(macAddr);
            clientAddr.setUpdateTime(new Date());
            log.info("【存储主机信息】更新数据 clientAddr={}",clientAddr);
            return clientAddrRepository.save(clientAddr);
        }catch (Exception e){
            log.error("【存储主机信息】出错了！convert={}",convert);
            throw new JpcapException(ResultEnum.SAVE_CLIENTADDR_FAILED.getCode(),e.getMessage());
        }
    }



    @Override
    public void findByIp() {

    }
}
