


t_flume_channelInfo

t_flumeCofig_ChannelInfo



t_flume_sourcesInfo

t_flumeCofig_sourcesInfo


t_flume_sinkInfo

t_flumeCofig_sinkInfo



####################################################################################################################################

CREATE TABLE `t_flume_channelInfo` (
  `id` varchar(32) NOT NULL,
  `component_name` varchar(255) DEFAULT NULL COMMENT '组件名',
  `ip_addr` varchar(255) DEFAULT NULL,
  
  `start_time` datetime DEFAULT NULL COMMENT '启动时间',
  `put_success_count` varchar(255) DEFAULT NULL COMMENT '成功放入数量',
  `put_attempt_count` varchar(255) DEFAULT NULL COMMENT '正在放入数量',
  `percentage` varchar(255) DEFAULT NULL COMMENT '百分比',
  `channel_size` varchar(255) DEFAULT NULL COMMENT '通道容量',
  `take_success_count` varchar(255) DEFAULT NULL COMMENT '成功取走的数量',
  `take_attempt_count` varchar(255) DEFAULT NULL COMMENT '正在取走的数量',
  
  `run_state` int(4) DEFAULT NULL COMMENT '运行状态[RUNING(1):已开启, CLOSE(2):关闭, PORTMONITORINGEXCEPTION(3):端口监控异常]',
  `delete_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='flume监控端口的通道内容';


CREATE TABLE `t_flumeCofig_ChannelInfo` (
  `context_id` bigint(20) DEFAULT NULL,
  `id` varchar(32) DEFAULT NULL,
  UNIQUE KEY `flune_channel` (`context_id`,`id`),
  KEY `channelID` (`id`),
  CONSTRAINT `channelID` FOREIGN KEY (`id`) REFERENCES `t_flume_channelInfo` (`id`) ON DELETE CASCADE,
  CONSTRAINT `flumeID` FOREIGN KEY (`context_id`) REFERENCES `t_flume_config` (`context_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


####################################################################################################################################

CREATE TABLE `t_flume_sourcesInfo` (
  `id` varchar(32) NOT NULL,
  `component_name` varchar(255) DEFAULT NULL COMMENT '组件名',
  `ip_addr` varchar(255) DEFAULT NULL COMMENT 'Ip地址',
  
  `current_throughput"` varchar(255) DEFAULT NULL COMMENT '当前吞吐量',
  `max_throughput"` varchar(255) DEFAULT NULL COMMENT '最大吞吐量',
  `event_count"` varchar(255) DEFAULT NULL COMMENT '事件总数',
  `average_throughput"`  varchar(255) DEFAULT NULL COMMENT '平均吞吐量',
  
  `run_state` int(4) DEFAULT NULL COMMENT '运行状态[RUNING(1):已开启, CLOSE(2):关闭]',
  `delete_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='flume监控端口的来源内容';


CREATE TABLE `t_flumeCofig_sourcesInfo` (
  `context_id` bigint(20) DEFAULT NULL,
  `id` varchar(32) DEFAULT NULL,
  UNIQUE KEY `flune_channel` (`context_id`,`id`),
  KEY `channelID` (`id`),
  CONSTRAINT `sourcesID` FOREIGN KEY (`id`) REFERENCES `t_flume_sourcesInfo` (`id`) ON DELETE CASCADE,
  CONSTRAINT `flumeID` FOREIGN KEY (`context_id`) REFERENCES `t_flume_config` (`context_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



####################################################################################################################################

CREATE TABLE `t_flume_sinkInfo` (
  `id` varchar(32) NOT NULL,
  `component_name` varchar(255) DEFAULT NULL COMMENT '组件名',
  `ip_addr` varchar(255) DEFAULT NULL COMMENT 'Ip地址',
  `start_time` datetime DEFAULT NULL COMMENT '启动时间',
  `connection_created_count` varchar(255) DEFAULT NULL COMMENT '存储系统创建连接数量',
  `batch_complete_count` varchar(255) DEFAULT NULL COMMENT '批处理完成计数',
  `batch_empty_count` varchar(255) DEFAULT NULL COMMENT '空的批量的数量',
  `event_drainAttempt_count` varchar(255) DEFAULT NULL COMMENT '写出到存储的事件总数量',
  `batch_underflow_count` varchar(255) DEFAULT NULL COMMENT '批量下溢计数',
  `connection_failed_count` varchar(255) DEFAULT NULL COMMENT '存储系统由于错误关闭的连接数量',
  `connection_closed_count` varchar(255) DEFAULT NULL COMMENT '存储系统关闭的连接数量',
  `rollback_count` varchar(255) DEFAULT NULL COMMENT '回滚计数',
  `event_drain_success_count` varchar(255) DEFAULT NULL COMMENT '成功写出到存储的事件总数量',
  `kafka_event_sendTimer` varchar(255) DEFAULT NULL COMMENT 'Kafka事件发送计时器',	 
  `run_state` int(4) DEFAULT NULL COMMENT '运行状态[RUNING(1):已开启, CLOSE(2):关闭]',
  `delete_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='flume监控端口的下沉内容';


CREATE TABLE `t_flumeCofig_sinkInfo` (
  `context_id` bigint(20) DEFAULT NULL,
  `id` varchar(32) DEFAULT NULL,
  UNIQUE KEY `flune_channel` (`context_id`,`id`),
  KEY `sinkID` (`id`),
  CONSTRAINT `sinkID` FOREIGN KEY (`id`) REFERENCES `t_flume_sinkInfo` (`id`) ON DELETE CASCADE,
  CONSTRAINT `flumeID3` FOREIGN KEY (`context_id`) REFERENCES `t_flume_config` (`context_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  

		
		



