package com.instant.service.Impl;

import com.instant.model.TAccount;
import com.instant.mapper.TAccountMapper;
import com.instant.service.TAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author grl
 * @since 2019-12-19
 */
@Service
public class TAccountServiceImpl extends ServiceImpl<TAccountMapper, TAccount> implements TAccountService {

	private TAccountMapper tAccountMapper;
	
	@Override
	public TAccount selectTAccountByAppKey(String appKey) {
		// 2019年12月20日 add by grl
		return tAccountMapper.selectTAccountByAppkey(appKey);
	}

}
