package io.renren;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OkHttpTest {

    @Test
    public void ObjectMapperTest(){
        ObjectMapper objectMapper = new ObjectMapper();
        //属性为 空（“”） 或者为 NULL 都不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        Map<Object,Object> map=new HashMap<Object,Object>();
        map.put("name","zhangsan");
        map.put("lastName","");
        map.put("age",null);
        map.put(1,12);
        map.put(2,0);
        try {
            String json =objectMapper.writeValueAsString(map);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
