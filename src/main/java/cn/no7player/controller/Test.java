package cn.no7player.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;






import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import cn.no7player.model.SystemConfiguration;
import cn.no7player.service.SystemConfigurationServiceImpl;

@Api(value = "test", description = "test", produces = "application/json")
@Controller
@RequestMapping(value = "/api/test")
public class Test {
	
	@Autowired
	private SystemConfigurationServiceImpl sys;
	
	
	@ApiOperation(value = "test", notes = "index", httpMethod = "POST", produces = "application/json", position = 1)
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public void test( @RequestBody SystemConfiguration orderRefundableParam) {
		System.out.println(sys.listPageConfig(orderRefundableParam));
         System.out.println(orderRefundableParam.getPage().getTotalPage());
         System.out.println(orderRefundableParam.getPage().getCurrentPage());
		}
	}


