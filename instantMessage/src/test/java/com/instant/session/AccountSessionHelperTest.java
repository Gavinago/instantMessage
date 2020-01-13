package com.instant.session;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.instant.base.BaseTest;
import com.instant.model.TAccount;

/**
* 	类说明 
*
* @author grl
* @date 2019年12月21日  新建 
*/
public class AccountSessionHelperTest extends BaseTest{

	@Autowired
	private AccountSessionHelper accountSessionHelper;
	/**
	 * <p>
	 * 从15库中获取 TAccountMap
	 * 存储的是 appkey 和 TAccount的键值对
	 * </p>
	 * @author grl
	 * @return
	 */
	//@Test
	public void getTAccountMapTest() {
		JSONObject tAccountMap = accountSessionHelper.getTAccountMap();
		System.out.println(tAccountMap);
		Assert.notNull(tAccountMap, "获取 TAccountMap 成功 ", tAccountMap);
	}
	@Test
	public void putAppkeyAndAccountTest() {
		String appkey = "123456789";
		TAccount account = new TAccount();
		account.setAccountAppKey(appkey);
		accountSessionHelper.putAppkeyAndAccount(appkey, account);
	}
}
