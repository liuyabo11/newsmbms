package cn.pb.smbms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.pb.smbms.dao.UserMapper;
import cn.pb.smbms.pojo.User;
import cn.pb.smbms.service.UserService;
import cn.pb.smbms.util.PageUtil;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	Logger logger = Logger.getLogger(UserServiceImpl.class);

	/**
	 * 用户登录的方法
	 */
	public User login(String userCode, String password) {
		return userMapper.login(userCode, password);
	}

	/**
	 * 分页查询用户列表
	 */
	public PageUtil findUserListByPage(Integer pageIndex, Integer pageSize,
			User user) {
		// 封装数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageIndex", (pageIndex - 1) * pageSize);
		map.put("pageSize", pageSize);
		if (user != null) {
			map.put("userName", user.getUserName());
		}

		// 调用dao层方法
		List<User> userList = userMapper.findUserListByPage(map);
		logger.info("查询到的userlist：" + userList);

		// 创建PageUtil对象
		PageUtil pageUtil = new PageUtil();
		pageUtil.setPageIndex(pageIndex);
		pageUtil.setPageSize(pageSize);
		pageUtil.setTotalCount(userMapper.getTotalCount(user));
		logger.info("总记录数：" + userMapper.getTotalCount(user));
		pageUtil.setUserList(userList);
		return pageUtil;
	}

	/**
	 * 新增用户
	 */
	public boolean addUser(User user) {
		boolean flag = false;
		int i = userMapper.addUser(user);
		logger.info("新增用户时返回数据影响行数：i=" + i);
		if (i > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 根据id删除用户
	 */
	public boolean delUser(Integer id) {
		logger.debug("要删除的id:" + id);
		boolean flag = false;
		int i = userMapper.delUser(id);
		if (i > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 修改用户的方法
	 */
	public boolean updateUser(User user) {
		logger.debug("要修改的用户id:" + user.getId());
		int i = userMapper.updateUser(user);
		boolean flag = false;
		if (i > 0) {
			flag = true;
		}
		return flag;
	}

}
