package cn.jmu.assgin.service;

import cn.jmu.assgin.dao.MessageDao;
import cn.jmu.assgin.dao.RevertDao;
import cn.jmu.assgin.domain.entity.Message;
import cn.jmu.assgin.domain.entity.Revert;
import cn.jmu.assgin.domain.vo.PostVO;
import cn.jmu.assgin.domain.vo.UserVO;
import javafx.geometry.Pos;
import org.apache.commons.beanutils.BeanUtils;
import org.shijh.myframework.framework.annotation.Autowired;
import org.shijh.myframework.framework.annotation.Component;

import java.util.List;

@Component("messageService")
public class MessageService {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private RevertDao revertDao;

    public List<Message> getAllMessage() {
        return messageDao.queryAll();
    }

    public List<Message> getUserMessage(UserVO userVO) {
        String name = userVO.getName();
        return messageDao.queryByWriter(name);
    }

    public PostVO getPost(int messageId) {
        Message message = messageDao.queryById(messageId);
        if (message == null) {
            return null;
        }
        List<Revert> reverts = revertDao.queryByMessageId(messageId);
        PostVO postVO = new PostVO();
        postVO.setMessage(message);
        postVO.setReverts(reverts);
        return postVO;
    }

    public boolean removePost(int messageId) {
        int i = messageDao.delete(messageId);
        int j = revertDao.deleteByMessageId(messageId);
        return i != 0 || j != 0;
    }

    public boolean modifyContent(Message message) {
        return messageDao.updateContent(message) > 0;
    }

    public boolean addMessage(Message message) {
        return messageDao.insert(message) > 0;
    }
}
