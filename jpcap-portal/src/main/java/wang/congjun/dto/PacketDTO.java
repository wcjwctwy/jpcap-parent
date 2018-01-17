package wang.congjun.dto;

import lombok.Data;

@Data
public class PacketDTO {

   private String srcIp;
   private String decIp;
   private String srcMac;
   private String decMac;
   private Byte version;
   private byte[] data;
   private int protocol;
}
