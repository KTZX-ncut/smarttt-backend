package com.example.smartttevaluation.Utils;

import com.example.smartttevaluation.dto.Result;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Aspect
@Component
public class TransactionAspect {

    @AfterReturning(pointcut = "@annotation(org.springframework.transaction.annotation.Transactional)", returning = "result")
    public void checkTransactionResult(Object result) {
        if (result instanceof Result) {
            Result response = (Result) result;
            if (response.getCode() != 200) {
                // 触发事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
    }
}
