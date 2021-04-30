import cn.jmu.assgin.domain.entity.Message;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DoTest {

    @Test
    public void test1() throws Exception{
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class type, Object value) {//注册一个日期转换器
                Date date1 = null;
                if (value instanceof String) {
                    String date = (String) value;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    try {
                        date1 = sdf.parse(date);
                    } catch (ParseException e) {
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            date1 = sdf.parse(date);
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                        e.printStackTrace();
                    }
                }
                return date1;
            }
        }, Date.class);
        Map<String, Object> map = new HashMap<>();
        map.put("writedate", "2021-10-11 13:00:12");
        Message m = new Message();
        BeanUtils.populate(m,map);
        System.out.println(m);
    }
}
