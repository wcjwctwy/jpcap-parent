package wang.congjun.config;

import jpcap.JpcapCaptor;
import jpcap.JpcapSender;
import jpcap.JpcapWriter;
import jpcap.NetworkInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import wang.congjun.dataobject.JpcapProperties;
import wang.congjun.enums.ResultEnum;
import wang.congjun.exception.JpcapException;

import java.io.IOException;

@Component
@Slf4j
public class JpcapConfig {

    @Bean
    @ConfigurationProperties(prefix = "jpcap")
    public JpcapProperties jpcapProperties() {
        return new JpcapProperties();
    }


    @Bean
    public JpcapCaptor jpcapCaptor(JpcapProperties jpcapProperties) {
        NetworkInterface[] deviceList = JpcapCaptor.getDeviceList();
        for(NetworkInterface networkInterface : deviceList){
            log.info("【配置网卡接口】网卡列表datalink_name：{}",networkInterface.name);
            log.info("【配置网卡接口】网卡列表datalink_description：{}",networkInterface.description);
        }
        JpcapCaptor jpcapCaptor;
        try {
            jpcapCaptor = JpcapCaptor.openDevice(deviceList[jpcapProperties.getIntrface()],
                    jpcapProperties.getSnaplen(),
                    jpcapProperties.getPromisc(),
                    jpcapProperties.getTo_ms());
        } catch (IOException e) {
            log.info("【配置网卡接口】获取失败jpcapProperties={}",jpcapProperties);
            throw new JpcapException(ResultEnum.GET_INTERFACE_FAILED);
        }
        return jpcapCaptor;
    }

    @Bean
    public JpcapSender jpcapSender(JpcapCaptor jpcapCaptor){
        JpcapSender jpcapSenderInstance = jpcapCaptor.getJpcapSenderInstance();
        return jpcapSenderInstance;
    }

}
