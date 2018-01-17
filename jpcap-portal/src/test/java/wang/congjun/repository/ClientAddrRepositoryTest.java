package wang.congjun.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.congjun.JpcapApplication;
import wang.congjun.dataobject.ClientAddr;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpcapApplication.class)
public class ClientAddrRepositoryTest {

    @Autowired
    private ClientAddrRepository clientAddrRepository;

    @Test
    public void testSave(){
        Date date = new Date();
        ClientAddr clientAddr = new ClientAddr();
        clientAddr.setIpAddr("1.1.1.1");
        clientAddr.setStatus(Short.valueOf("0"));
        clientAddr.setMacAddr("11:22:11:33:55:ff");
        clientAddr.setCreateTime(date);
        clientAddr.setUpdateTime(date);
        ClientAddr save = clientAddrRepository.save(clientAddr);
        Assert.assertNotNull(save);
    }

}