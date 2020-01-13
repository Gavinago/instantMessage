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
 * 消息记录表
 * </p>
 *
 * @author grl
 * @since 2019-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TRecodermessage对象", description="消息记录表")
public class TRecodermessage extends Model<TRecodermessage> {

    private static final long serialVersionUID=1L;

    @TableId(value = "msg_id", type = IdType.AUTO)
    private Integer msgId;

    private String appKey;

    @ApiModelProperty(value = "消息内容 json")
    private String msgContext;

    @ApiModelProperty(value = "消息发送时间")
    private Date msgSendTime;

    @ApiModelProperty(value = "消息接收时间")
    private Date msgReceiveTime;

    @ApiModelProperty(value = "0-未读 1-已读")
    private Integer msgStatus;

    @ApiModelProperty(value = "0-删除 1-正常 2-已撤回")
    private Integer msgDelflag;

    @ApiModelProperty(value = "消息类型 1-私聊")
    private Integer msgConversationType;

    private Integer msgDirection;

    @ApiModelProperty(value = "1-发送成功 0-发送失败")
    private Integer msgSentStatus;

    @ApiModelProperty(value = "消息发送者的ID")
    private String msgSenderId;

    @ApiModelProperty(value = "私聊是单个id")
    private String msgReceiverId;

    @ApiModelProperty(value = "0 -在线消息 1- 离线消息")
    private Integer msgOffLine;

    @ApiModelProperty(value = "1-已接收 0-未接收")
    private Integer msgReceivedStatus;

    @ApiModelProperty(value = "0-提示信息 1-text 文本 2-图片 3-文件 4-语音 5-商品信息")
    private Integer msgType;

    @ApiModelProperty(value = "消息随机数")
    private String msgRandomStr;


    @Override
    protected Serializable pkVal() {
        return this.msgId;
    }

}
