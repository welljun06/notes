package indi.welljun.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class WebUtils {
    //封装客户端提交的表单数据
    public static <E> E request2Bean(HttpServletRequest request, Class<E> beanClass) {
        try {
            E bean = beanClass.newInstance();
            //得到request的所有数据
            Map<String, String[]> map = request.getParameterMap();
            //注册一个转换器
            ConvertUtils.register(new Converter() {

                @Override
                public <T> T convert(Class<T> type, Object value) {
                    if (value==null) {
                        return null;
                    }
                    String str = (String)value;
                    if (str.trim().equals("")) {
                        return null;
                    }
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        return (T) df.parse(str);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, Date.class);
            BeanUtils.populate(bean, map);
            return bean;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
