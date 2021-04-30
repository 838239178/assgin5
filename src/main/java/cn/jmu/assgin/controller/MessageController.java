package cn.jmu.assgin.controller;

import cn.jmu.assgin.domain.entity.Message;
import cn.jmu.assgin.domain.vo.PostVO;
import cn.jmu.assgin.domain.vo.UserVO;
import cn.jmu.assgin.service.MessageService;
import org.shijh.myframework.framework.annotation.*;
import org.shijh.myframework.framework.bean.ModelAndView;
import org.shijh.myframework.framework.controller.Controller;

import javax.crypto.interfaces.PBEKey;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component("messageController")
@Mapping("/message")
public class MessageController extends Controller {
    @Autowired
    private MessageService messageService;

    @Mapping("/query/all")
    public ModelAndView allMessage() {
        ModelAndView mv = new ModelAndView();
        List<Message> messages = messageService.getAllMessage();
        mv.setSuccess(true);
        mv.setModel(messages);
        mv.setView("/message-list.jsp");
        return mv;
    }

    @Mapping("/query/user")
    @DoIntercept(value = "CheckSession", params = "user")
    public ModelAndView userMessage(HttpSession httpSession) {
        ModelAndView mv = new ModelAndView();
        UserVO user = (UserVO) httpSession.getAttribute("user");
        List<Message> messages = messageService.getUserMessage(user);
        mv.setSuccess(true);
        mv.setModel(messages);
        mv.setView("/message-list.jsp");
        return mv;
    }

    @Mapping("/query/post")
    public ModelAndView post(@Param("messageID") int messageID) {
        ModelAndView mv = new ModelAndView();
        PostVO post = messageService.getPost(messageID);
        mv.setSuccess(post != null);
        if (post != null) {
            mv.setModel(post);
            mv.setView("/post.jsp");
        } else {
            mv.setModel("不存在该帖子");
            mv.setView("/error.jsp");
        }
        return mv;
    }

    @Mapping("/deploy")
    @DoIntercept(value = "CheckSession", params = {"user"})
    public ModelAndView deploy(Message message, HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("user");
        message.setWriter(user.getName());
        boolean b = messageService.addMessage(message);
        ModelAndView mv = new ModelAndView();
        mv.setSuccess(b);
        if (b) {
            mv.setView("/api/message/query/all");
        } else {
            mv.setView("/deploy-message.jsp");
            mv.setModel("发布失败，可能包含错误字符");
        }
        return mv;
    }


    @Mapping("/remove/id")
    @DoIntercept(value = "CheckSession", params = {"user"})
    public ModelAndView removePost(@Param("messageID") int messageID, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        UserVO user = (UserVO) session.getAttribute("user");
        PostVO post = messageService.getPost(messageID);
        if (post == null) {
            mv.setSuccess(false);
            mv.setView("/error.jsp");
            mv.setModel("没有该帖子");
            return mv;
        }
        String writer = post.getMessage().getWriter();
        boolean b;
        if (!writer.equals(user.getName())) {
            mv.setSuccess(false);
            mv.setModel("企图删除他人的帖子");
            return mv;
        } else {
            b = messageService.removePost(messageID);
        }
        mv.setSuccess(b);
        if (b) {
            mv.setView("/api/message/query/all");
        } else {
            mv.setView("/error.jsp");
            mv.setModel("未知错误");
        }
        return mv;
    }

    @Mapping("/view/update")
    @DoIntercept(value = "CheckSession", params = {"user"})
    public ModelAndView updateView(int messageID,HttpSession session) {
        PostVO post = messageService.getPost(messageID);
        ModelAndView modelAndView = new ModelAndView();
        if (post == null) {
            modelAndView.setSuccess(false);
            modelAndView.setModel("没有给帖子");
            modelAndView.setView("/error.jsp");
            return modelAndView;
        }
        UserVO user = (UserVO) session.getAttribute("user");
        if (!post.getMessage().getWriter().equals(user.getName())) {
            modelAndView.setSuccess(false);
            modelAndView.setModel("不能改别人的帖子");
            modelAndView.setView("/error.jsp");
            return modelAndView;
        }
        modelAndView.setView("/deploy-message.jsp");
        modelAndView.setModel(post.getMessage());
        modelAndView.setSuccess(true);
        return modelAndView;
    }

    @Mapping("/update")
    @DoIntercept(value = "CheckSession", params = {"user"})
    public ModelAndView update(Message message, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        UserVO user = (UserVO) session.getAttribute("user");
        message.setWriter(user.getName());
        boolean b = messageService.modifyContent(message);
        mv.setSuccess(b);
        if (b) {
            mv.setView("/api/message/query/post?messageID="+message.getMessageID());
        } else {
            mv.setView("/deploy-message.jsp");
            mv.setModel(message);
        }
        return mv;
    }
}
