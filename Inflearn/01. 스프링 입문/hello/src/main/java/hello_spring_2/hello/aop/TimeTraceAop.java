package hello_spring_2.hello.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component // config에 @Bean 등록 안할꺼면 Component 스캔 해줘야함
public class TimeTraceAop {
    @Around("execution(* hello_spring_2.hello..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("Start : " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("End : "+ joinPoint.toString() + " " +timeMs + "ms");
        }
    }
}
