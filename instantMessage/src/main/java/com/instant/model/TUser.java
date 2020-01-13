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
 * Im 用户表
 * </p>
 *
 * @author grl
 * @since 2019-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TUser对象", description="Im 用户表")
public class TUser extends Model<TUser> {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private Integer accountId;

    @ApiModelProperty(value = "用户头像")
    private String userPortrait;

    @ApiModelProperty(value = "用户性别 1-男 2-女 0 -未知")
    private Integer userGender;

    @ApiModelProperty(value = "用户token")
    private String userToken;

    @ApiModelProperty(value = "用户所属账号的key")
    private String userBelongKey;

    @ApiModelProperty(value = "用户最后一次登录时间")
    private Date userLastLoginTime;

    @ApiModelProperty(value = "用户创建时间")
    private Date userCreateTime;

    @ApiModelProperty(value = "用户信息修改时间")
    private Date userInfoModifyTime;

    @ApiModelProperty(value = "用户状态 0- 冻结 1-正常 2-加入黑名单")
    private Integer userStatus;

    @ApiModelProperty(value = "用户昵称")
    private String userNickname;

    @ApiModelProperty(value = "AppKey账户里的用户id(在APPKey下唯一)")
    private String appUserId;

    @ApiModelProperty(value = "推送的regId")
    private String appRegiId;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
