package cn.jmu.assgin.service;

import cn.jmu.assgin.dao.RevertDao;
import cn.jmu.assgin.domain.dto.RevertDTO;
import cn.jmu.assgin.domain.entity.Revert;
import org.apache.commons.beanutils.BeanUtils;
import org.shijh.myframework.framework.annotation.Autowired;
import org.shijh.myframework.framework.annotation.Component;
import org.shijh.myframework.framework.dao.Dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component("revertService")
public class RevertService {
    @Autowired
    private RevertDao revertDao;

    public boolean addRevert(RevertDTO revertDTO) {
        if (revertDTO.getMessageID() == null) {
            return false;
        }
        Revert revert = new Revert();
        try {
            BeanUtils.copyProperties(revert,revertDTO);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return revertDao.insert(revert) > 0;
    }

    public List<Revert> getMessageReverts(RevertDTO revertDTO) {
        return revertDao.queryByMessageId(revertDTO.getMessageID());
    }

    public boolean modifyContent(RevertDTO revertDTO) {
        Revert revert = new Revert();
        try {
            BeanUtils.copyProperties(revert,revertDTO);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return revertDao.updateContent(revert) > 0;
    }

    public boolean removeRevert(RevertDTO revertDTO) {
        Revert i = revertDao.queryByWriterAndID(revertDTO);
        if (i == null) return false;
        return revertDao.delete(revertDTO.getMessageID(), revertDTO.getRevertID()) > 0;
    }
}
