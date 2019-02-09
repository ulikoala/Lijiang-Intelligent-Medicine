package io.renren;

import io.renren.modules.api.entity.MacEntity;
import io.renren.modules.api.service.MacService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MacMapperTest {

    @Resource
    MacService macService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<MacEntity> macEntityList = macService.selectList(null);
//        Assert.assertEquals(5, userList.size());
        macEntityList.forEach(System.out::println);
    }
}
