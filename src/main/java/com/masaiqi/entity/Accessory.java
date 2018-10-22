package com.masaiqi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 附件表
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Accessory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 附件url
     */
    private String url;

    /**
     * 附件归属:0-团队；1-项目组；2-个人；3-其他；
     */
    private Integer authority;

    /**
     * 上传者Id
     */
    @TableField("uploadId")
    private Integer uploadId;


}
