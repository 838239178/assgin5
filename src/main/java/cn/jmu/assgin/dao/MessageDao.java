package cn.jmu.assgin.dao;

import cn.jmu.assgin.domain.entity.Message;
import org.apache.commons.beanutils.BeanUtils;
import org.shijh.myframework.framework.annotation.Autowired;
import org.shijh.myframework.framework.annotation.Component;
import org.shijh.myframework.framework.dao.Dao;
import org.shijh.myframework.framework.dao.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component("messageDao")
public class MessageDao extends Dao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected Object resultMap(Map<String, Object> map) throws IllegalAccessException, InvocationTargetException, SQLException {
        Message message = new Message();
        BeanUtils.populate(message, map);
        return message;
    }

    public int insert(Message message) {
        return jdbcTemplate.executeUpdate(message, "insert into message values (NULL, #{title},#{content},#{writer}, NOW())");
    }

    public int delete(int messageId) {
        return jdbcTemplate.executeUpdate("delete from message where messageID=?", messageId);
    }

    public Message queryById(int messageId) {
        return jdbcTemplate.queryObject("select * from message where messageID=?", getResultMap(), messageId);
    }

    public List<Message> queryAll() {
        return jdbcTemplate.queryList("select * from message", getResultMap());
    }

    public int updateContent(Message message) {
        return jdbcTemplate.executeUpdate(message, "update message set content=#{content}, writeDate=now() where messageID=#{messageID}");
    }

    public List<Message> queryByWriter(String name) {
        return jdbcTemplate.queryList("select * from message where writer=?", getResultMap(), name);
    }
}
