package wang.congjun.pkghandler;

import jpcap.JpcapSender;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.congjun.sevice.ClientAddrService;

@Component
@Slf4j
public class SavePkgHandler implements PacketReceiver {

    @Autowired
    private ClientAddrService clientAddrService;

    @Autowired
    private JpcapSender jpcapSender;

    @Override
    public void receivePacket(Packet p) {
        jpcapSender.sendPacket(p);
        clientAddrService.save(p);
    }
}
