package com.instant.service;

import com.instant.model.TAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author grl
 * @since 2019-12-19
 */
public interface TAccountService extends IService<TAccount> {

	
	TAccount selectTAccountByAppKey(String appKey);
}
