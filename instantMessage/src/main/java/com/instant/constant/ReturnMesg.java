package com.instant.constant;
/**
* 	类说明 
* 定义格式 Xxx_Xxx_Xxx
* @author grl
* @date 2019年11月28日  新建 
*/
public enum ReturnMesg {
	Too_Many_Failures("尝试次数过多"),Account_Password_Error("账号或密码错误"),Account_Not_Activated("账号未启用")
	,Success("ok"),Verification_Code_Error("验证码错误"),Parameters_Is_Empty("参数不能为空")
	,Failed("failed"),Goods_Have_Expired ("该商品已失效"),No_More_Data("没有更多数据") ,
	Parameters_Error("参数错误"),Token_Expired("token不正确或已过期"),Illegal_Access("非法访问"),
	App_key_Expired("appKey不正确或已经过期 ");
	
	private String name;
	private ReturnMesg() {
		
	}
	private ReturnMesg(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
