package com.gmf.core.admin;

import com.gmf.core.admin.dao.UmsAdmin;
import com.gmf.core.admin.mapper.UmsAdminMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author gmf
 * @date 2020/12/31 11:55
 * @descrition:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UmsAdminMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<UmsAdmin> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
//        userList.forEach(System.out::println);
    }

}