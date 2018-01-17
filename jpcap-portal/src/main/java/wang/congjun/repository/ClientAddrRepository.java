package wang.congjun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wang.congjun.dataobject.ClientAddr;

public interface ClientAddrRepository extends JpaRepository<ClientAddr,Long> {

     ClientAddr findClientAddrByMacAddr(String mac);

}
