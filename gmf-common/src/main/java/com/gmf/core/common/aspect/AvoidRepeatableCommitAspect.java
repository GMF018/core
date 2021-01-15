package com.gmf.core.common.aspect;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gmf.core.common.annotation.AvoidRepeatCommit;
import com.gmf.core.common.exception.Asserts;
import com.gmf.core.common.service.RedisService;
import com.gmf.core.common.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author gmf
 * @date 2021/1/15 10:04
 * @descrition: 避免重复提交的切面类
 */
@Component
@Aspect
@Slf4j
public class AvoidRepeatableCommitAspect {
    @Autowired
    RedisService redisService;

    @Around("@annotation(com.gmf.core.common.annotation.AvoidRepeatCommit)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = RequestUtil.getRequestIp(request);
        //此处method获取的是代理对象（由代理模式生成的）的方法
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //此处realMethod是目标对象（原始的）的方法
        // Method realMethod = point.getTarget().getClass().getDeclaredMethod(signature.getName(),method.getParameterTypes());

        //目标类、方法
        String className = method.getDeclaringClass().getName();
        String name = method.getName();
        String ipKey = String.format("%s#%s", className, name);
        int hashCode = Math.abs(ipKey.hashCode());
        String key = String.format("%s_%d", ip, hashCode);
        //logger.info("ipKey={},hashCode={},key={}",ipKey,hashCode,key);
        log.info(String.format("ipKey={},hashCode={},key={}", ipKey, hashCode, key));
        //通过反射技术来获取注解对象
        AvoidRepeatCommit avoidRepeatableCommit = method.getAnnotation(AvoidRepeatCommit.class);
        long timeout = avoidRepeatableCommit.timeout();
        if (timeout < 0) {
            //过期时间10秒
            timeout = 10;
        }
        //获取key键对应的值
        String value = (String) redisService.get(key);
        if (StringUtils.isNotBlank(value)) {
            Asserts.fail("请勿重复提交！");
        }
        //新增一个字符串类型的值，key是键，value是值。
        redisService.set(key, UUID.randomUUID(), timeout);

        //返回继续执行被拦截到的方法
        return point.proceed();
    }
}
