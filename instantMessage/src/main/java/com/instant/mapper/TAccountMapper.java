package com.instant.mapper;

import com.instant.model.TAccount;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 账号表 Mapper 接口
 * </p>
 *
 * @author grl
 * @since 2019-12-19
 */
public interface TAccountMapper extends BaseMapper<TAccount> {

	
	@Select("  select * from t_account where account_app_key = #{appKey} ")
	TAccount selectTAccountByAppkey(@Param(value="appKey")String appKey);
}
