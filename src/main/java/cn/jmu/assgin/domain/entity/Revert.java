package cn.jmu.assgin.domain.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * null
 * @TableName revert
 */
@Data
public class Revert implements Serializable {
    /**
     * 回复ID
     */
    private Integer revertID;

    /**
     * 原贴ID
     */
    private Integer messageID;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 回帖者
     */
    private String writer;

    /**
     * 回帖时间
     */
    private Date writeDate;

    private static final long serialVersionUID = 1L;
}