package com.instant.constant;


/**
* 	类说明 
*
* @author grl
* @date 2019年11月28日  新建 
*/
public enum ReturnStatus {

	// 	请求状态：0-初始状态; 200-请求处理成功; 401-请求未认证，跳转登录页; 405-请求不允许，跳转绑定手机号页面；406-请求未授权，跳转未授权提示页; 407 -非法请求 416-表单重复提交； 500-请求处理失败 (优先解析status)
	
	FAILED(0),LOGIC_SUCCESS(1),POP_MSG_CODE(2),SUCCESS(200),UNAPPROVE(401),Not_Find_Resources(404),UNAUTHORIZED(406),ILLEGAL_ACCESS(407),REDUPLICATIVE(416),INTERNAL_ERROR(500);
	
	
	private Integer value;
	
	private ReturnStatus() {
		
	}
	private ReturnStatus(Integer value) {
		this.value = value;
	}
	public Integer getName() {
		return value;
	}
}
