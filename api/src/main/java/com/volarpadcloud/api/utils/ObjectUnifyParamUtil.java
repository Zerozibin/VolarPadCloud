package com.volarpadcloud.api.utils;

import com.volarpadcloud.api.entity.enums.DelEnum;
import com.volarpadcloud.api.entity.enums.StatusEnum;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * @author linzibin
 * @email @Zerozibin@163.com
 * @date 2019/1/9 16:05
 * @application 针对对象的公用代码抽取便捷使用
 */
public class ObjectUnifyParamUtil {


    /**
     * 偷懒用，设置对象的id，status，isdel，createtime，updatetime
     * @param object
     */
    public static void setUnifyParam(Object object) {
        try {
            Class<?> cls = object.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if(!"serialVersionUID".equalsIgnoreCase(field.getName())){
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(),cls);
                    if("id".equalsIgnoreCase(field.getName())){
                        Method readMethod = pd.getWriteMethod();
                        readMethod.invoke(object, new Object[]{UUID.randomUUID().toString()});
                    }
                    if("status".equalsIgnoreCase(field.getName())){
                        Method readMethod = pd.getWriteMethod();
                        readMethod.invoke(object, new Object[]{StatusEnum.VOLARPAD_STATUS_START.getCode()});
                    }
                    if("isDel".equalsIgnoreCase(field.getName())){
                        Method readMethod = pd.getWriteMethod();
                        readMethod.invoke(object, new Object[]{DelEnum.VOLARPAD_ISDEL.getCode()});
                    }
                    if("createTime".equalsIgnoreCase(field.getName())){
                        Method readMethod = pd.getWriteMethod();
                        readMethod.invoke(object, new Object[]{new Date()});
                    }
                    if("updateTime".equalsIgnoreCase(field.getName())){
                        Method readMethod = pd.getWriteMethod();
                        readMethod.invoke(object, new Object[]{new Date()});
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
