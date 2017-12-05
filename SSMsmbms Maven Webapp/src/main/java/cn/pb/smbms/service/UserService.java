package cn.pb.smbms.service;

import cn.pb.smbms.pojo.User;
import cn.pb.smbms.util.PageUtil;

public interface UserService {
	/**
	 * 用户登录
	 * 
	 * @return
	 */
	User login(String userCode, String password);

	/**
	 * 分页查询用户列表
	 * 
	 * @return
	 */
	PageUtil findUserListByPage(Integer pageIndex, Integer pageSize, User user);

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	boolean addUser(User user);

	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 * @return
	 */
	boolean delUser(Integer id);

	/**
	 * 修改用户的方法
	 * 
	 * @param user
	 * @return
	 */
	boolean updateUser(User user);

}
