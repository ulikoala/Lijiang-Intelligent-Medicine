package io.renren.modules.api.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.renren.modules.api.entity.MacEntity;
import io.renren.modules.api.service.MacService;
import io.renren.modules.api.service.OkHttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@EnableAsync
public class LoadMachineData {
    public static final String BOOTPATH = "http://lglfpt.com:8006";//生产地址
//    public static final String BOOTPATH = "http://localhost:8080/renren-admin/api/test";//测试地址

    private Logger logger = LoggerFactory.getLogger(getClass());
    private SimpleDateFormat dataFromat = new SimpleDateFormat("HH:mm:ss");

    @Resource
    OkHttpService okHttpService;
    @Resource
    MacService macService;

    @Async
    @Scheduled(cron = "0 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void loadData() {
        //任务开始时间
        long startTime = System.currentTimeMillis();
        logger.info("定时器路径:"+this.getClass().getName());
        try {
            String params = "昆明弘远生物技术有限公司";
            logger.info("我是无参数的loadData方法，正在被执行，参数为：" + params);
            long addCount = getMacList(params);
            logger.info("执行结果：向数据库添加了"+addCount+"条记录。");

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            logger.info("任务执行完毕," + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            logger.error("任务执行失败：", e);
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public long getMacList(String ClientName) {
        long addCount=0;
        ObjectMapper objectMapper = new ObjectMapper();//转换json对象
        List<MacEntity> macList = new ArrayList<MacEntity>();//用来存储返回的药柜数组
        macList = macService.selectList(null);
        Map<String, Object> responseMap = new HashMap<String, Object>();//用来存储返回的Map对象
        try {
            String response = okHttpService.get(BOOTPATH + "/YnzhRes/getMacList?ClientName=" + ClientName);
            Map<String, Object> map = objectMapper.readValue(response, Map.class);
            List<Map> mapList = (List<Map>) map.get("rows");
            if (mapList != null && mapList.size() != 0 && !Objects.equals(map.get("rows").toString(), "[{}]")) {
                for (Map mapli : mapList) {
                    MacEntity macEntity = new MacEntity();
                    macEntity.setMachineNo((String) mapli.get("MacNo"));
                    macEntity.setMachineShortName((String) mapli.get("MacShortNam"));
                    macEntity.setMachineAdress((String) mapli.get("Adress"));
                    macEntity.setClientName(ClientName);
                    if (!macList.contains(macEntity)) {
                        System.out.println("实体："+macEntity);
                        macService.insert(macEntity);
                        addCount++;
                    }
                }
            }
            return addCount;
        } catch (Exception e) {
            e.printStackTrace();
            return addCount;
        }
    }
}
