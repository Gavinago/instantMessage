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
 * im数据监控表
 * </p>
 *
 * @author grl
 * @since 2019-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ImMonitor对象", description="im数据监控表")
public class ImMonitor extends Model<ImMonitor> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "监控id")
    @TableId(value = "monitor_id", type = IdType.AUTO)
    private Integer monitorId;

    @ApiModelProperty(value = "appkey")
    private Integer accountId;

    @ApiModelProperty(value = "监控日期")
    private Date monitorData;

    @ApiModelProperty(value = "web访问设备数")
    private Integer monitorWebCount;

    @ApiModelProperty(value = "安卓访问设备数")
    private Integer monitorAndroidCount;

    @ApiModelProperty(value = "ios访问设备数")
    private Integer monitorIosCount;

    @ApiModelProperty(value = "访问总人数")
    private Integer monitorUserCount;


    @Override
    protected Serializable pkVal() {
        return this.monitorId;
    }

}
