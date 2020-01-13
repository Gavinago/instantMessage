package com.instant.mapper;

import com.instant.model.TUser;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Im 用户表 Mapper 接口
 * </p>
 *
 * @author grl
 * @since 2019-12-19
 */
public interface TUserMapper extends BaseMapper<TUser> {

	
	@Select(" select count(1) from t_user where user_status= 1 and user_token =#{token} ")
	Integer selectCountByToken(@Param(value="token")String token);
	
	@Select(" select * from t_user where user_belong_key = #{appKey} and app_user_id =#{appUserId} ")
	TUser selectTUserByAppKeyAndAppUserId(@Param(value="appKey")String appKey,@Param(value="appUserId")String appUserId);
	
	
}
