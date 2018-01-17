package wang.congjun.sevice.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.congjun.JpcapApplication;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpcapApplication.class)
public class ClientAddrServiceImplTest {

    @Autowired
    private ClientAddrServiceImpl clientAddrService;

    @Test
    public void save() throws Exception {
        clientAddrService.save();
    }

    @Test
    public void save1() throws Exception {
    }

    @Test
    public void findByIp() throws Exception {
    }

}