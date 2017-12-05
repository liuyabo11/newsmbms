package cn.pb.smbms.controller;

/**
 * 用户的controller
 */
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.pb.smbms.pojo.User;
import cn.pb.smbms.service.UserService;
import cn.pb.smbms.util.PageUtil;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Resource
	private UserService userService;

	// log4j
	Logger logger = Logger.getLogger(UserController.class);

	/**
	 * 登录的方法
	 * 
	 * @param userCode
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login.html")
	public ModelAndView login(String userCode, String userPassword,
			HttpSession session) {
		logger.info("进入了login的方法");
		User user = userService.login(userCode, userPassword);
		session.setAttribute("user", user);
		ModelAndView mv = new ModelAndView();
		if (user != null) {
			mv.setViewName("welcome");
		} else {
			mv.setViewName("login");
			mv.addObject("info", "用户名或密码不正确！");
		}
		return mv;
	}

	/**
	 * 用户退出 session失效
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/exit.html")
	public String exit(HttpSession session) {
		logger.info("进入了退出exit方法");
		// 清除session
		session.removeAttribute("userSession");
		return "redirect:/user/login.html";
	}

	/**
	 * 跳转到userList页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list.html")
	public String list() {
		logger.info("进入了list方法");
		return "userList";
	}

	/**
	 * 分页
	 * 
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping(value = "/findUsersList")
	@ResponseBody
	public Object findUsersList(
			@RequestParam(defaultValue = "1", value = "page") Integer pageIndex,
			@RequestParam(defaultValue = "2", value = "rows") Integer pageSize,
			User user) {
		logger.info("进入了findUsersList方法！pageIndex=" + pageIndex);
		logger.info("userName:" + user.getUserName());

		Map<String, Object> map = new HashMap<String, Object>();

		PageUtil pageUtil = userService.findUserListByPage(pageIndex, pageSize,
				user);

		Integer count = pageUtil.getTotalCount();
		map.put("total", count);
		map.put("rows", pageUtil.getUserList());
		return map;
	}

	/**
	 * 新增用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addUser")
	@ResponseBody
	public Object saveUser(User user, HttpSession session) {
		logger.info("进入了saveUser方法：判断id是否为空？是：走新增的方法。不是：走修改的方法。userId："
				+ user.getId());
		boolean flag = false;
		if (user.getId() == null) {
			logger.debug("userId为：" + user.getId() + "执行add方法！");
			user.setCreatedBy(((User) session.getAttribute("user")).getId());
			user.setCreationDate(new Date());
			flag = userService.addUser(user);
		} else {
			logger.debug("userId为：" + user.getId() + "执行update方法！");
			user.setCreatedBy(((User) session.getAttribute("user")).getId());
			user.setCreationDate(new Date());
			flag = userService.updateUser(user);
		}

		return flag;
	}

	/**
	 * 删除的方法
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delUser")
	@ResponseBody
	public Object delUser(String strId) {
		logger.debug("进入了delUser方法！要删除的id为：" + strId);
		boolean flag = false;
		// 将前台传过来的要删除的ids字符串 拆分成数组
		String[] ids = strId.split(",");
		for (String id : ids) {
			flag = userService.delUser(Integer.parseInt(id));
		}
		return flag;
	}
}
