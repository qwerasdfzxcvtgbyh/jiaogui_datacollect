package com.qmtec.servicecore.service.resourceDB.backstageService.hdfs;

import com.alibaba.fastjson.JSON;
import com.qmtec.common.page.ListResult;
import com.qmtec.dataSource.dbModel.hdfs.HdfsBaseSource;
import com.qmtec.dataSource.entity.DatabaseSource;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Component
public class HdfsServiceImpl implements HdfsService {

    private FileSystem fileSystem = null;

    @Override
    public Boolean testConn(DatabaseSource resourceDB) throws Exception {
        HdfsBaseSource hdfsBaseSource = null;
        FileSystem fileSystem = null;
        boolean flag = false;
        try {
            hdfsBaseSource = JSON.parseObject(resourceDB.getDefine(), HdfsBaseSource.class);
            fileSystem = conn(hdfsBaseSource);
        } finally {
            if (fileSystem != null) {
                fileSystem.close();
                flag = true;
            }
        }
        return flag;
    }

    public FileSystem standalone(HdfsBaseSource hdfsBaseSource) throws Exception {
        Configuration configuration = new Configuration();
        return FileSystem.get(new URI(hdfsBaseSource.getDefaultFs()),
                configuration, hdfsBaseSource.getOwer());
    }

    public FileSystem colony(HdfsBaseSource hdfsBaseSource) throws Exception {
        Configuration conf = new Configuration();
        String nameservice = hdfsBaseSource.getNameservice();
        String[] namenodes = {"nn1", "nn2"};
        conf.set("fs.defaultFS", "hdfs://" + nameservice);
        conf.set("dfs.nameservices", nameservice);
        conf.set("dfs.ha.namenodes." + nameservice, namenodes[0] + "," + namenodes[1]);
        conf.set("dfs.namenode.rpc-address." + nameservice + "." + namenodes[0], hdfsBaseSource.getNameNodeAddr1());
        conf.set("dfs.namenode.rpc-address." + nameservice + "." + namenodes[1], hdfsBaseSource.getNameNodeAddr2());
        conf.set("dfs.client.failover.proxy.provider." + nameservice
                , "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        String hdfsRPCUrl = "hdfs://" + nameservice + ":" + 8020;
        return FileSystem.get(new URI(hdfsRPCUrl),
                conf, hdfsBaseSource.getOwer());
    }

    @Override
    public FileSystem conn(HdfsBaseSource hdfsBaseSource) throws Exception {
        if (hdfsBaseSource.isIsHighAvailability()) {
            return this.colony(hdfsBaseSource);
        } else {
            return this.standalone(hdfsBaseSource);
        }
    }

    @Override
    public List<String> getTableHeader(DatabaseSource resourceDB, String tableName) throws Exception {
        return null;
    }

    @Override
    public ListResult<Map<String, Object>> listSelectTableDataPreview(DatabaseSource resourceDB, String tableName) throws Exception {
        return null;
    }


}
