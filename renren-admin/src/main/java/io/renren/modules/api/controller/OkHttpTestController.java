package io.renren.modules.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.renren.modules.api.entity.MacEntity;
import io.renren.modules.api.entity.MacPosEntity;
import io.renren.modules.api.entity.SaleRecordEntity;
import io.renren.modules.api.service.MacService;
import io.renren.modules.api.service.OkHttpService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/api")
public class OkHttpTestController {
    public static final String BOOTPATH = "http://lglfpt.com:8006";//生产地址
//    public static final String BOOTPATH = "http://localhost:8080/renren-admin/api/test";//测试地址
    @Resource
    OkHttpService okHttpService;
    @Resource
    MacService macService;

    //模拟测试  start
    @ResponseBody
    @RequestMapping("/test/YnzhRes/getMacList")
    public String test(String ClientName) {

//        return "{\"redataname\":\"MacInfo\",\"success\":\"true\",\"errorsmg\":\"\",\"rowscount\":2,\"rows\":[{}]}";
        return "{\"redataname\":\"MacInfo\",\"success\":\"true\",\"errorsmg\":\"\",\"rowscount\":2,\"rows\":[{\"MacNo\":\"005a1402-792e-4d76-a146-98f3757f53c2\",\"MacShortNam\":\"云南智慧药房一号机\",\"Adress\":\"\"},{\"MacNo\":\"07a0f819-df01-49b3-90e0-e50476c0e4c5\",\"MacShortNam\":\"丽江市政府机\",\"Adress\":\"\"}]}";
    }

    @ResponseBody
    @RequestMapping("/test/YnzhRes/getMacPosList")
    public String test2(String ClientName) {

        return "{\"redataname\":\"MacPosInfo\",\"success\":\"true\",\"errorsmg\":\"\",\"rowscount\":3,\"rows\":[{\"MacNo\":\"005a1402-792e-4d76-a146-98f3757f53c2\",\"DrugbatchNo\":\"\",\"Maxnum\":\"22\",\"Nownum\":\"0\",\"PosCode\":\"1 \",\"Posstate\":\"1\",\"MacShortNam\":\"云南智慧药房一号机\"},{\"MacNo\":\"005a1402-792e-4d76-a146-98f3757f53c2\",\"DrugbatchNo\":\"347ac71d-6652-4492-9603-162fc98437e9\",\"Maxnum\":\"8\",\"Nownum\":\"3\",\"PosCode\":\"51 \",\"Posstate\":\"1\",\"MacShortNam\":\"云南智慧药房一号机\"},{\"MacNo\":\"005a1402-792e-4d76-a146-98f3757f53c2\",\"DrugbatchNo\":\"\",\"Maxnum\":\"22\",\"Nownum\":\"0\",\"PosCode\":\"54 \",\"Posstate\":\"1\",\"MacShortNam\":\"云南智慧药房一号机\"}]}";
    }

    @ResponseBody
    @RequestMapping("/test/YnzhRes/getSaleRecord")
    public String test3(String ClientName) {

        return "{\"redataname\":\"SaleDataInf\",\"success\":\"true\",\"errorsmg\":\"\",\"rowscount\":2,\"rows\":[{\"TDeaNo\":\"A021B7EDEE3220181229163400997\",\"TDeaState\":\"True\",\"OutDrugTime\":\"2018\\/12\\/29 16:34:29\",\"PayType\":\"微信\",\"ShouldOutCount\":\"1\",\"RealOutCount\":\"1\",\"OutDrugPrice\":\"1\",\"OutDrugAllPrice\":\"1\",\"DrugsName\":\"医用灭菌棉签\",\"DrugsBatch\":\"\",\"DrugsBatchNo\":\"\",\"DrugsNo\":\"\",\"OutDrugResult\":\"1\",\"MacShortName\":\"丽江市政府机\",\"ClientName\":\"昆明弘远生物技术有限公司\",\"PosCode\":\"69 \"},{\"TDeaNo\":\"20E52AF50AE820181227153038298\",\"TDeaState\":\"True\",\"OutDrugTime\":\"2018\\/12\\/27 15:30:59\",\"PayType\":\"微信\",\"ShouldOutCount\":\"1\",\"RealOutCount\":\"1\",\"OutDrugPrice\":\"0.01\",\"OutDrugAllPrice\":\"0.01\",\"DrugsName\":\"小儿清咽颗粒\",\"DrugsBatch\":\"170613\",\"DrugsBatchNo\":\"\",\"DrugsNo\":\"\",\"OutDrugResult\":\"1\",\"MacShortName\":\"云南智慧药房一号机\",\"ClientName\":\"昆明弘远生物技术有限公司\",\"PosCode\":\"54 \"}]}";
    }

    //模拟测试  end

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getMacListOriginal")
    public Map<String, Object> getMacListOriginal(String ClientName) {
        ObjectMapper objectMapper = new ObjectMapper();//转换json对象
        List<MacEntity> macList = new ArrayList<MacEntity>();//用来存储返回的药柜数组
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
                    macList.add(macEntity);
                }
                responseMap.put("rows", macList);
            } else {
                responseMap.put("rows", "[{}]");
            }
            responseMap.put("redataname", (String) map.get("redataname"));
            responseMap.put("rowscount", map.get("rowscount"));
            responseMap.put("success", (String) map.get("success"));
            responseMap.put("errorsmg", (String) map.get("errorsmg"));
            return responseMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getMacList")
    public Map<String, Object> getMacList(String ClientName) {
        List<MacEntity> macList = new ArrayList<MacEntity>();//用来存储返回的药柜数组
        Map<String, Object> responseMap = new HashMap<String, Object>();//用来存储返回的Map对象
        try {
            macList = macService.selectList(new EntityWrapper<MacEntity>().eq("client_name", ClientName));
            if (macList != null && macList.size() > 0) {
                responseMap.put("rows", macList);
                responseMap.put("success", true);
                responseMap.put("rowscount", macList.size());
                responseMap.put("errorsmg", "");
            } else {
                responseMap.put("rows", "[{}]");
                responseMap.put("success", false);
                responseMap.put("rowscount", 0);
                responseMap.put("errorsmg", "此客户不存在");
            }
            responseMap.put("redataname", "MacInfo");
            if (ClientName == null || ClientName.isEmpty()) {
                responseMap.put("errorsmg", "客户不能为空");
            }
            return responseMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getMacPosList")
    public Map<String, Object> getMacPosList(String ClientName, String MacNo) {
        ObjectMapper objectMapper = new ObjectMapper();//转换json对象
        List<MacPosEntity> macPosList = new ArrayList<MacPosEntity>();//用来存储返回的药柜储位数组
        Map<String, Object> responseMap = new HashMap<String, Object>();//用来存储返回的Map对象
        Map<String, String> requestMap = new HashMap<String, String>();
        requestMap.put("ClientName", ClientName);
        requestMap.put("MacNo", MacNo);
        try {
            String response = okHttpService.get(BOOTPATH + "/YnzhRes/getMacPosList", requestMap);
            Map<String, Object> map = objectMapper.readValue(response, Map.class);
            List<Map> mapList = (List<Map>) map.get("rows");
            if (mapList != null && mapList.size() != 0 && !Objects.equals(map.get("rows").toString(), "[{}]")) {
                for (Map mapli : mapList) {
                    MacPosEntity macPosEntity = new MacPosEntity();
                    macPosEntity.setMachineNo((String) mapli.get("MacNo"));
                    macPosEntity.setDrugBatchNo((String) mapli.get("DrugbatchNo"));
                    macPosEntity.setMaxNumber((String) mapli.get("Maxnum"));
                    macPosEntity.setNowNumber((String) mapli.get("Nownum"));
                    macPosEntity.setPositionCode((String) mapli.get("PosCode"));
                    macPosEntity.setPositionState((String) mapli.get("Posstate"));
                    macPosEntity.setMachineShortName((String) mapli.get("MacShortNam"));
                    macPosList.add(macPosEntity);
                }
                responseMap.put("rows", macPosList);
            } else {
                responseMap.put("rows", "[{}]");
            }
            responseMap.put("redataname", (String) map.get("redataname"));
            responseMap.put("rowscount", map.get("rowscount"));
            responseMap.put("success", (String) map.get("success"));
            responseMap.put("errorsmg", (String) map.get("errorsmg"));
            return responseMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getSaleRecord")
    public Map<String, Object> getSaleRecord(String ClientName, String SBegtime, String EBegtime) {
        ObjectMapper objectMapper = new ObjectMapper();//转换json对象
        List<SaleRecordEntity> saleRecordList = new ArrayList<SaleRecordEntity>();//用来存储返回的药柜储位数组
        Map<String, Object> responseMap = new HashMap<String, Object>();//用来存储返回的Map对象
        Map<String, String> requestMap = new HashMap<String, String>();
        requestMap.put("ClientName", ClientName);
        requestMap.put("SBegtime", SBegtime);
        requestMap.put("EBegtime", EBegtime);
        try {
            String response = okHttpService.get(BOOTPATH + "/YnzhRes/getSaleRecord", requestMap);
            Map<String, Object> map = objectMapper.readValue(response, Map.class);
            List<Map> mapList = (List<Map>) map.get("rows");
            if (mapList != null && mapList.size() != 0 && !Objects.equals(map.get("rows").toString(), "[{}]")) {
                for (Map mapli : mapList) {
                    SaleRecordEntity saleRecordEntity = new SaleRecordEntity();
                    saleRecordEntity.setOrderNo((String) mapli.get("TDeaNo"));
                    saleRecordEntity.setOrderState(Boolean.parseBoolean((String) mapli.get("TDeaState")));
                    saleRecordEntity.setOutDrugTime(((String) mapli.get("OutDrugTime")).replace("/", "-"));
                    saleRecordEntity.setPayType((String) mapli.get("PayType"));
                    saleRecordEntity.setShouldOutCount(Integer.parseInt((String) mapli.get("ShouldOutCount")));
                    saleRecordEntity.setRealOutCount(Integer.parseInt((String) mapli.get("RealOutCount")));
                    saleRecordEntity.setOutDrugPrice(new BigDecimal((String) mapli.get("OutDrugPrice")));
                    saleRecordEntity.setOutDrugAllPrice(new BigDecimal((String) mapli.get("OutDrugAllPrice")));
                    saleRecordEntity.setDrugName((String) mapli.get("DrugsName"));
                    saleRecordEntity.setDrugBatch((String) mapli.get("DrugsBatch"));
                    saleRecordEntity.setDrugBatchNo((String) mapli.get("DrugsBatchNo"));
                    saleRecordEntity.setDrugNo((String) mapli.get("DrugsNo"));
                    saleRecordEntity.setOutDrugResult((String) mapli.get("OutDrugResult"));
                    saleRecordEntity.setMachineShortName((String) mapli.get("MacShortName"));
                    saleRecordEntity.setClientName((String) mapli.get("ClientName"));
                    saleRecordEntity.setPositionCode((String) mapli.get("PosCode"));
                    saleRecordList.add(saleRecordEntity);
                }
                responseMap.put("rows", saleRecordList);
            } else {
                responseMap.put("rows", "[{}]");
            }
            responseMap.put("redataname", (String) map.get("redataname"));
            responseMap.put("rowscount", map.get("rowscount"));
            responseMap.put("success", (String) map.get("success"));
            responseMap.put("errorsmg", (String) map.get("errorsmg"));
            return responseMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
