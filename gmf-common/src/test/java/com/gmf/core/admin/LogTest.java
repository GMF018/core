package com.gmf.core.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author gmf
 * @date 2020/12/25 14:30
 * @descrition:
 */
@Slf4j
public class LogTest {
    @Test
    public void log() {
        log.error("测试");
        log.info("测试");
        log.warn("测试");
        log.debug("测试");


    }
}
