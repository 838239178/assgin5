package cn.jmu.assgin.dao;

import cn.jmu.assgin.domain.dto.RevertDTO;
import cn.jmu.assgin.domain.entity.Revert;
import org.apache.commons.beanutils.BeanUtils;
import org.shijh.myframework.framework.annotation.Autowired;
import org.shijh.myframework.framework.annotation.Component;
import org.shijh.myframework.framework.dao.Dao;
import org.shijh.myframework.framework.dao.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Component("revertDao")
public class RevertDao extends Dao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected Object resultMap(Map<String, Object> map) throws IllegalAccessException, InvocationTargetException {
        Revert revert = new Revert();
        BeanUtils.populate(revert, map);
        return revert;
    }

    public List<Revert> queryByMessageId(int messageId) {
        return jdbcTemplate.queryList("select * from revert where messageid=?", getResultMap(), messageId);
    }

    public int insert(Revert revert) {
        return jdbcTemplate.executeUpdate(revert, "insert into revert values (NULL, #{messageID},#{content},#{writer}, NOW())");
    }

    public int updateContent(Revert revert) {
        return jdbcTemplate.executeUpdate(revert,
                "update revert set content=#{content}, writeDate=now() where messageID=#{messageID} and revertID=#{revertID}");
    }

    public int deleteByMessageId(int messageId) {
        return jdbcTemplate.executeUpdate("delete from revert where messageId=?", messageId);
    }

    public Revert queryByWriterAndID(RevertDTO revertDTO) {
        return jdbcTemplate.queryObject("select * from revert where writer=#{writer} and revertID=#{revertID}", revertDTO, getResultMap());
    }

    public int delete(Integer messageid, Integer revertid) {
        return jdbcTemplate.executeUpdate("delete from revert where messageID=? and revertID=?", messageid, revertid);
    }
}
