package wang.congjun.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "tb_client_addr")
@Data
public class ClientAddr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ipAddr;
    private String ipAddr6;
    private String macAddr;
    private int protocol;
    private Short status;
    private Date createTime;
    private Date updateTime;

}
