package com.instant.utils.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.instant.constant.CommonConst;
import com.instant.constant.ReturnMesg;
import com.instant.constant.ReturnStatus;
import com.instant.session.AccountSessionHelper;
import com.instant.utils.wrapper.ParameterRequestWrapper;

/**
 * 使用注解标注过滤器@WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * filterName声明过滤器的名称（可选）
 * urlPatterns指定要过滤的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 *
 *
 */
@Component // 将此Filter交给Spring容器管理 或者在启动入口加 @ServletComponentScan
@WebFilter(filterName = "MyFilter", urlPatterns = { "/**" })
@Order(1) // 指定过滤器的执行顺序,值越大越靠后执行
public class MyFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(MyFilter.class);
	@Autowired
	private AccountSessionHelper sessionHelper;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String requestURI = req.getRequestURI();
		HttpServletResponse res = (HttpServletResponse) response;
		response.setContentType(CommonConst.CONTENT_TYPE);
		response.setCharacterEncoding(CommonConst.CHARACTER_ENCODING);
		res.setHeader("Access-Control-Allow-Methods", "POST, GET");
		// 表示隔 单位为秒 30分钟才发起预检请求。也就是说，发送两次请求
		res.setHeader("Access-Control-Max-Age", "36000");
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type,user-agent, Accept,token,AppKey,version");
		String appKey = sessionHelper.getAppkey(req);
		String token = sessionHelper.getToken(req);
		if (StringUtils.isBlank(appKey)) {
			ServletOutputStream out = response.getOutputStream();
			JSONObject o = new JSONObject();
			o.put(CommonConst.DEFAULT_RESULT_STATUS_KEY, ReturnStatus.ILLEGAL_ACCESS.getName());
			o.put(CommonConst.DEFAULT_RESULT_CODE_KEY, ReturnStatus.FAILED.getName());
			o.put(CommonConst.DEFAULT_MSG_POP_KEY, ReturnMesg.Illegal_Access.getName());
			out.write(o.toString().getBytes());
			out.flush();
			out.close();
			String agent = req.getHeader("user-agent");
			String method = req.getMethod();
			log.error("请求方式 {}             {} 非法请求 {}  请求IP {}  appKey {}  token{}", method, agent,
					requestURI + "?" + req.getQueryString(), request.getRemoteAddr(), appKey, token);
			return;
		} else {
			// 校验Appkey
			if (!sessionHelper.verifyAppKey(appKey)) {
				ServletOutputStream out = response.getOutputStream();
				JSONObject o = new JSONObject();
				o.put(CommonConst.DEFAULT_RESULT_STATUS_KEY, ReturnStatus.ILLEGAL_ACCESS.getName());
				o.put(CommonConst.DEFAULT_RESULT_CODE_KEY, ReturnStatus.FAILED.getName());
				o.put(CommonConst.DEFAULT_MSG_POP_KEY, ReturnMesg.App_key_Expired.getName());
				out.write(o.toString().getBytes());
				out.flush();
				out.close();
				String agent = req.getHeader("user-agent");
				String method = req.getMethod();
				log.error("请求方式 {}             {} 非法请求 {}  请求IP {}  appKey {}  token{}", method, agent,
						requestURI + "?" + req.getQueryString(), request.getRemoteAddr(), appKey, token);
				return;
			}
		}
		if (StringUtils.isBlank(requestURI)
				|| !(requestURI.contains("registerToken") || requestURI.contains("updateUserInfo"))) {
			if (StringUtils.isBlank(token)) {
				ServletOutputStream out = response.getOutputStream();
				JSONObject o = new JSONObject();
				o.put(CommonConst.DEFAULT_RESULT_STATUS_KEY, ReturnStatus.UNAPPROVE.getName());
				o.put(CommonConst.DEFAULT_RESULT_CODE_KEY, ReturnStatus.FAILED.getName());
				o.put(CommonConst.DEFAULT_MSG_POP_KEY, ReturnMesg.Parameters_Is_Empty.getName());
				out.write(o.toString().getBytes());
				out.flush();
				out.close();
				String agent = req.getHeader("user-agent");
				String method = req.getMethod();
				log.error("请求方式 {}             {} 请求未认证请求 {}  请求IP {}  appKey {}  token{}", method, agent,
						requestURI + "?" + req.getQueryString(), request.getRemoteAddr(), appKey, token);
				return;
			} else {
				if (!sessionHelper.verifyToken(appKey, token)) {
					ServletOutputStream out = response.getOutputStream();
					JSONObject o = new JSONObject();
					o.put(CommonConst.DEFAULT_RESULT_STATUS_KEY, ReturnStatus.UNAUTHORIZED.getName());
					o.put(CommonConst.DEFAULT_RESULT_CODE_KEY, ReturnStatus.FAILED.getName());
					o.put(CommonConst.DEFAULT_MSG_POP_KEY, ReturnMesg.Token_Expired.getName());
					out.write(o.toString().getBytes());
					out.flush();
					out.close();
					String agent = req.getHeader("user-agent");
					String method = req.getMethod();
					log.error("请求方式 {}             {} 请求未认证请求或token失效 {}  请求IP {}  appKey {}  token{}", method, agent,
							requestURI + "?" + req.getQueryString(), request.getRemoteAddr(), appKey, token);
					return;
				}
			}
		}
		chain.doFilter(new ParameterRequestWrapper((HttpServletRequest) request), response);
	}

}
