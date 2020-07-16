package cn.pb.smbms.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.pb.smbms.service.ProviderService;

@Controller
@RequestMapping(value = "/provider")
public class ProviderController {
	private Logger logger = Logger.getLogger(ProviderController.class);
	@Resource
	private ProviderService providerService;

	//测试


	//ceshi2
	/**
	 * 跳转到providerList页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list.html")
	public String list() {
		logger.debug("进入了provider里的list方法！！");
		return "providerList";
	}
}
