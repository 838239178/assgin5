package cn.jmu.assgin.dao;

import cn.jmu.assgin.domain.entity.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.shijh.myframework.framework.annotation.Autowired;
import org.shijh.myframework.framework.annotation.Component;
import org.shijh.myframework.framework.dao.Dao;
import org.shijh.myframework.framework.dao.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component("personDao")
public class PersonDao extends Dao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected Object resultMap(Map<String, Object> map) throws IllegalAccessException, InvocationTargetException, SQLException {
        Person person = new Person();
        BeanUtils.populate(person, map);
        return person;
    }

    public Person queryById(String id) {
        return jdbcTemplate.queryObject("select * from person where id=?", getResultMap(), id);
    }

    public Person queryByName(String name) {
        return jdbcTemplate.queryObject("select * from person where name=?", getResultMap(), name);
    }

    public List<Person> queryAll() {
        return jdbcTemplate.queryList("select * from person", getResultMap());
    }
}
