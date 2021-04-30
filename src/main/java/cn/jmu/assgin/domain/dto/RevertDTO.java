package cn.jmu.assgin.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RevertDTO implements Serializable {
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
}
