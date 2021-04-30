package cn.jmu.assgin.domain.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * null
 * @TableName message
 */
@Data
public class Message implements Serializable {
    /**
     * 留言ID
     */
    private int messageID;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 楼主
     */
    private String writer;

    /**
     * 发帖时间
     */
    private Date writeDate;

    private static final long serialVersionUID = 1L;
}