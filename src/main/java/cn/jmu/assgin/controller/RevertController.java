package cn.jmu.assgin.controller;

import cn.jmu.assgin.domain.dto.RevertDTO;
import cn.jmu.assgin.domain.entity.Revert;
import cn.jmu.assgin.domain.vo.UserVO;
import cn.jmu.assgin.service.RevertService;
import org.shijh.myframework.framework.annotation.Autowired;
import org.shijh.myframework.framework.annotation.Component;
import org.shijh.myframework.framework.annotation.DoIntercept;
import org.shijh.myframework.framework.annotation.Mapping;
import org.shijh.myframework.framework.bean.ModelAndView;
import org.shijh.myframework.framework.controller.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component("revertController")
@Mapping("/revert")
public class RevertController extends Controller {
    @Autowired
    private RevertService revertService;

    @Mapping("/query/messageid")
    public ModelAndView revertsOfMessage(RevertDTO revertDTO) {
        List<Revert> messageReverts = revertService.getMessageReverts(revertDTO);
        ModelAndView mv = new ModelAndView();
        mv.setModel(messageReverts);
        mv.setSuccess(true);
        return mv;
    }

    @Mapping("/modify")
    @DoIntercept(value = "CheckSession", params = {"user"})
    public ModelAndView changeRevert(RevertDTO revertDTO, HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("user");
        String username = user.getName();
        ModelAndView mv = new ModelAndView();
        if (!username.equals(revertDTO.getWriter()))  {
            mv.setSuccess(false);
            mv.setView("/error.jsp");
            mv.setModel("请不要修改他人的回复");
            return mv;
        }
        boolean b = revertService.modifyContent(revertDTO);
        mv.setSuccess(b);
        if (b) {
            mv.setView("/api/message/query/post?messageId="+revertDTO.getMessageID());
        } else {
            mv.setView("/error.jsp");
            mv.setModel("修改失败");
        }
        return mv;
    }

    @Mapping("/reply")
    @DoIntercept(value = "CheckSession", params = {"user"})
    public ModelAndView addRevertToMessage(RevertDTO revertDTO, HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("user");
        String username = user.getName();
        revertDTO.setWriter(username);
        boolean b = revertService.addRevert(revertDTO);
        ModelAndView mv = new ModelAndView();
        mv.setSuccess(b);
        if (b) {
            mv.setView("/api/message/query/post?messageId="+revertDTO.getMessageID());
        } else {
            mv.setView("/error.jsp");
            mv.setModel("改帖子不存在");
        }
        return mv;
    }

    @Mapping("/remove")
    @DoIntercept(value = "CheckSession", params = {"user"})
    private ModelAndView removeRevert(RevertDTO revertDTO, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        UserVO user = (UserVO) session.getAttribute("user");
        if (revertDTO.getWriter() == null) revertDTO.setWriter(user.getName());
        if (!user.getName().equals(revertDTO.getWriter())) {
            mv.setSuccess(false);
            mv.setView("/error.jsp");
            mv.setModel("不能删除他人的回复");
            return mv;
        }
        boolean b = revertService.removeRevert(revertDTO);
        mv.setSuccess(b);
        if (!b) {
            mv.setView("/error.jsp");
            mv.setModel("不存在此回复");
        }
        mv.setView("/api/message/query/post?messageID="+revertDTO.getMessageID());
        return mv;
    }
}
