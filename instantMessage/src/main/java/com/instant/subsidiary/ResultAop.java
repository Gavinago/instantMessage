package com.instant.subsidiary;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.instant.constant.CommonConst;
import com.instant.constant.ReturnMesg;
import com.instant.constant.ReturnStatus;
import com.instant.utils.redis.RedisUtil;

/**
 * 类说明
 *
 * @author grl
 * @date 2019年11月14日 新建
 */
@Aspect
@Component
public class ResultAop {

	private static final Logger log = LoggerFactory.getLogger(ResultAop.class);

	private final String pointcutStr = "execution( * com.instant.controller..*.*(..))";

	@Pointcut(pointcutStr)
	public void excuteController() {

	}

	@AfterReturning(value = pointcutStr, returning = "jobj")
	public JSONObject doAroundAdvice(JoinPoint joinPoint, JSONObject jobj) {
		// proceedingJoinPoint.getArgs();
		try {// obj之前可以写目标方法执行前的逻辑
			procesResultObj(jobj);
			return jobj;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			log.error("@AfterReturning 出错{}", throwable.getMessage());
		}
		return null;
	}

	/**
	 * 处理返回对象
	 */
	@SuppressWarnings("unused")
	private void procesResultObj(JSONObject obj) {
		if (obj != null) {
			String message = (String) obj.get(CommonConst.DEFAULT_MSG_POP_KEY);
			Integer code = ReturnStatus.LOGIC_SUCCESS.getName();
			if (message != null) {
				code = ReturnStatus.POP_MSG_CODE.getName();
				obj.remove(CommonConst.DEFAULT_MSG_POP_KEY);
			} else {
				message = ReturnMesg.Success.getName();
			}
			String str = JSON.toJSONString(obj);
			obj.clear();
			obj.put(CommonConst.DEFAULT_RESULT_STATUS_KEY, ReturnStatus.SUCCESS.getName());
			obj.put(CommonConst.DEFAULT_RESULT_CODE_KEY, code);
			obj.put(CommonConst.DEFAULT_MSG_POP_KEY, message);
			obj.put(CommonConst.DEFAULT_RESULT_DATA_KEY, JSON.parseObject(str));
		}else{
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			try {
				response.setCharacterEncoding(CommonConst.CHARACTER_ENCODING);
				response.setContentType(CommonConst.CONTENT_TYPE);
				ServletOutputStream out = out = response.getOutputStream();
				JSONObject o = new JSONObject();
				o.put(CommonConst.DEFAULT_RESULT_CODE_KEY, ReturnStatus.FAILED.getName());
				o.put(CommonConst.DEFAULT_RESULT_STATUS_KEY, ReturnStatus.SUCCESS.getName());
				o.put(CommonConst.DEFAULT_MSG_POP_KEY, ReturnMesg.Parameters_Error.getName());
				out.write(o.toString().getBytes());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				log.error(" 返回值为 null 获取 response.getOutputStream 出错 ");
			}
		}
	}
}
