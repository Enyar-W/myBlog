package org.tl.blog.common.Interceptor;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
@Log4j2
public class OperationInterceptor implements Interceptor {
   // private static final Logger LOGGER = LoggerFactory.getLogger(EnvInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object object = invocation.getArgs()[1];
        //sql类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            //插入操作时，自动插入env
            Field fieldCreate = object.getClass().getDeclaredField("createAt");
            fieldCreate.setAccessible(true);
            fieldCreate.set(object, new Date());
        }else{
            if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                //update时，自动更新updated_at
                Field fieldUpdate = object.getClass().getDeclaredField("updateAt");
                fieldUpdate.setAccessible(true);
                fieldUpdate.set(object, new Date());
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
