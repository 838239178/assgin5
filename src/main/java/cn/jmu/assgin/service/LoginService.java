package cn.jmu.assgin.service;

import cn.jmu.assgin.dao.PersonDao;
import cn.jmu.assgin.domain.entity.Person;
import org.shijh.myframework.framework.annotation.Autowired;
import org.shijh.myframework.framework.annotation.Component;

@Component("loginService")
public class LoginService {
    @Autowired
    private PersonDao personDao;

    public Person verifyLogin(String username, String password) {
        Person person;
        if (username.matches("[0-9]+")) {
            person = personDao.queryById(username);
        } else {
            person = personDao.queryByName(username);
        }
        if (person == null) return null;
        return person.getPassword().equals(password) ? person : null;
    }
}
