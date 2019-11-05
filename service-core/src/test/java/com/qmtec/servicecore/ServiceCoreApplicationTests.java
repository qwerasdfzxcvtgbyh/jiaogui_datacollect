package com.qmtec.servicecore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceCoreApplicationTests {

    @Test
    public void contextLoads() {

        /*try {
            //HDFSUtil.listFiles("/",false);

            FileStatus[] fileStatuses = HDFSUtil.listFileStatus("/");
            for(FileStatus fileStatus:fileStatuses){
                System.out.println(fileStatus.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

}
