package com.instant.service.Impl;

import com.instant.model.TUser;
import com.instant.mapper.TUserMapper;
import com.instant.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Im 用户表 服务实现类
 * </p>
 *
 * @author grl
 * @since 2019-12-19
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

	@Autowired
	private TUserMapper tUserMapper;
	
	@Override
	public Integer selectCountByToken(String token) {
		Integer count = tUserMapper.selectCountByToken(token);
		return count;
	}
	@Transactional
	@Override
	public boolean updateSelective(TUser user) {
		// 2019年12月20日 add by grl
		return user.updateById();
	}
	@Transactional
	@Override
	public boolean insertSelective(TUser user) {
		// 2019年12月20日 add by grl
		return user.insert();
	}
	@Override
	public TUser selectTUserByAppKeyAndAppUserId(String appKey, String appUserId) {
		// 2019年12月20日 add by grl
		return tUserMapper.selectTUserByAppKeyAndAppUserId(appKey, appUserId);
	}

}
