package com.gmf.core.admin.ctl;


import com.gmf.core.common.annotation.AvoidRepeatCommit;
import com.gmf.core.common.api.CommonResult;
import com.gmf.core.common.exception.Asserts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gmf
 * @date 2020/12/31 11:48
 * @descrition:
 */
@RestController
@RequestMapping("/core")
@Slf4j
public class TestCtl {
    @AvoidRepeatCommit(timeout =10L)
    @GetMapping("test")
    public CommonResult<String> test(){
        Asserts.fail("11111111111111");
        log.error("-------------------test-----------------------");
        return CommonResult.success("成功");
    }
}
