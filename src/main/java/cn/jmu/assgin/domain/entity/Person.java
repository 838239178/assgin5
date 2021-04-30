package cn.jmu.assgin.domain.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * null
 * @TableName person
 */
@Data
public class Person implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String password;

    private static final long serialVersionUID = 1L;

}