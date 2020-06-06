package com.iwhalecloud.swagger.dubbo.web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.iwhalecloud.swagger.dubbo.config.DubboPropertyConfig;
import com.iwhalecloud.swagger.dubbo.config.DubboServiceScanner;
import com.iwhalecloud.swagger.dubbo.config.SwaggerDocCache;
import com.iwhalecloud.swagger.dubbo.reader.Reader;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.config.SwaggerConfig;
import io.swagger.models.Swagger;
import springfox.documentation.spring.web.json.Json;

@Controller
@RequestMapping("${swagger.dubbo.doc:swagger-dubbo}")
@Api(hidden = true)
public class SwaggerDubboController {

    public static final String DEFAULT_URL = "/api-docs";
    private static final String HAL_MEDIA_TYPE = "application/hal+json";
    
	@Autowired
	private DubboServiceScanner dubboServiceScanner;
	@Autowired
	private DubboPropertyConfig dubboPropertyConfig;
	@Autowired
	private SwaggerDocCache swaggerDocCache;

	@Value("${swagger.dubbo.http:cg}")
	private String httpContext;
	@Value("${swagger.dubbo.enable:true}")
	private boolean enable = true;

	@RequestMapping(value = DEFAULT_URL,
	        method = RequestMethod.GET,
	        produces = {"application/json; charset=utf-8", HAL_MEDIA_TYPE})
	@ResponseBody
	public ResponseEntity<Json> getApiList() throws JsonProcessingException, ClassNotFoundException {
		
		if (!enable){
			return new ResponseEntity<Json>(HttpStatus.NOT_FOUND);
		}

		// 先不存缓存取, 先关闭
		//Swagger swagger = swaggerDocCache.getSwagger();
		Swagger swagger = null;
		if (null != swagger){
			return new ResponseEntity<Json>(new Json(io.swagger.util.Json.mapper().writeValueAsString(swagger)), HttpStatus.OK);
		}else{
			swagger = new Swagger();
		}

		final SwaggerConfig configurator = dubboPropertyConfig;
		if (configurator != null) {
			configurator.configure(swagger);
		}

		// 接口信息
		// 将dubbo接口的class类型放入Map中
		//interfaceMapRef.putIfAbsent(bean.getInterfaceClass(), bean.getRef());
		Map<Class<?>, Object> interfaceMapRef = dubboServiceScanner.interfaceMapRef();
		Map<Class<?>, Object> interfaceMapReffer = new HashMap<Class<?>, Object>(4);
		Class<?> orderService = Class.forName("com.iwhalecloud.api.IOrderService", true, Thread.currentThread().getContextClassLoader());
		Class<?> userInfoService = Class.forName("com.iwhalecloud.api.IUserInfoService", true, Thread.currentThread().getContextClassLoader());
		interfaceMapReffer.put(orderService, null);
		interfaceMapReffer.put(userInfoService, null);
		interfaceMapRef.put(orderService, orderService);
		if (null != interfaceMapRef) {
			// 解析接口信息, 生成swagger json数据
			Reader.read(swagger, interfaceMapRef, httpContext);
		}
		swaggerDocCache.setSwagger(swagger);
		return new ResponseEntity<Json>(new Json(io.swagger.util.Json.mapper().writeValueAsString(swagger)), HttpStatus.OK);
	}

}
