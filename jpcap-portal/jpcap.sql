CREATE TABLE `tb_client_addr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip_addr` varchar(20) DEFAULT NULL COMMENT 'IP地址v4',
  `ip_addr6` varchar(64) DEFAULT NULL COMMENT 'ipv6地址',
  `mac_addr` varchar(20) NOT NULL COMMENT 'mac地址',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `protocol` tinyint(4) NOT NULL COMMENT 'arp/ip/http/tcp/icmp',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mac_addr` (`mac_addr`)
) ENGINE=InnoDB AUTO_INCREMENT=1764 DEFAULT CHARSET=utf8mb4;