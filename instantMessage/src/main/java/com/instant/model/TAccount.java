package com.instant.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账号表
 * </p>
 *
 * @author grl
 * @since 2019-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TAccount对象", description="账号表")
public class TAccount extends Model<TAccount> {

    private static final long serialVersionUID=1L;

    @TableId(value = "account_id", type = IdType.AUTO)
    private Integer accountId;

    @ApiModelProperty(value = "账号key")
    private String accountAppKey;

    private Date accountCreateTime;

    @ApiModelProperty(value = "账号过期时间")
    private Date accountExpireTime;

    @ApiModelProperty(value = "0-冻结 1-正常")
    private Integer accountStatus;

    @ApiModelProperty(value = "账号修改时间")
    private Date accountModifyTime;

    @ApiModelProperty(value = "账号详细信息 json")
    private String accountInfo;

    @ApiModelProperty(value = "账号 SECRET_KEY")
    private String accountSecretKey;


    @Override
    protected Serializable pkVal() {
        return this.accountId;
    }

}
