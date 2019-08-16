## init project



##访问flume监控端口返回的数据
{
	"SINK.k1": {
		"ConnectionCreatedCount": "0",      //下一个阶段或存储系统创建的连接数量（如HDFS创建一个新文件）
		"BatchCompleteCount": "0",          //与最大批量尺寸相等的批量的数量
		"BatchEmptyCount": "0",             //空的批量的数量，如果数量很大表示souce写数据比sink清理数据慢速度慢很多
		"EventDrainAttemptCount": "387340", //sink尝试写出到存储的事件总数量
		"StartTime": "1562833986439",
		"BatchUnderflowCount": "0",         //比sink配置使用的最大批量尺寸更小的批量的数量，如果该值很高也表示sink比souce更快
		"ConnectionFailedCount": "0",       //下一阶段或存储系统由于错误关闭的连接数量（如HDFS上一个新创建的文件因为超时而关闭）
		"ConnectionClosedCount": "0",       //下一阶段或存储系统关闭的连接数量(如在HDFS中关闭一个文件)
		"Type": "SINK",
		"RollbackCount": "0",
		"EventDrainSuccessCount": "387320", //sink成功写出到存储的事件总数量
		"KafkaEventSendTimer": "5677",
		"StopTime": "0"
	},
	"SOURCESQL.s1": {
		"CurrentThroughput": "0",
		"MaxThroughput": "0",
		"EventCount": "393600",
		"AverageThroughput": "20715"
	},
	"CHANNEL.c1": {
		"ChannelCapacity": "100000",                  //channel的容量
		"ChannelFillPercentage": "6.279999999999999", //channel满时的百分比
		"Type": "CHANNEL",                            
		"ChannelSize": "6280",                        //目前channel中事件的总数量
		"EventTakeSuccessCount": "387320",            //sink成功读取的事件的总数量
		"EventTakeAttemptCount": "387340",            //sink尝试从channel拉取事件的总数量。这不意味着每次事件都被返回，因为sink拉取的时候channel可能没有任何数据
		"StartTime": "1562833986244",                 //channel启动时自Epoch以来的毫秒值时间
		"EventPutAttemptCount": "393700",             //Source尝试写入Channe的事件总数量
		"EventPutSuccessCount": "393600",             //成功写入channel且提交的事件总数量
		"StopTime": "0"                               //channel停止时自Epoch以来的毫秒值时间
	}
}