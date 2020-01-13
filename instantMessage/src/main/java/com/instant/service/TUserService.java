package com.instant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.instant.model.TUser;

/**
 * <p>
 * Im 用户表 服务类
 * </p>
 *
 * @author grl
 * @since 2019-12-19
 */
public interface TUserService extends IService<TUser> {
	
	Integer selectCountByToken(String token);
	
	boolean updateSelective(TUser user);
	
	boolean insertSelective(TUser user);
	
	TUser selectTUserByAppKeyAndAppUserId(String appKey,String appUserId);
	
}
