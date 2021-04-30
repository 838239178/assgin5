package cn.jmu.assgin.domain.vo;

import cn.jmu.assgin.domain.entity.Message;
import cn.jmu.assgin.domain.entity.Revert;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class PostVO implements Serializable {
    /**
     * 帖子信息
     */
    private Message message;

    /**
     * 回复列表
     */
    private List<Revert> reverts;

}
