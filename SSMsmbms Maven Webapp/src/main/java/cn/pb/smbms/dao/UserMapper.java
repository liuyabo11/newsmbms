package cn.pb.smbms.dao;

import java.util.List;
import java.util.Map;

import cn.pb.smbms.pojo.User;

public interface UserMapper {
	/**
	 * 用户登录
	 * 
	 * @return
	 */
	User login(String userCode, String password);

	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	int getTotalCount(User user);

	/**
	 * 分页查询用户列表
	 * 
	 * @return
	 */
	List<User> findUserListByPage(Map<String, Object> map);

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	int addUser(User user);

	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 * @return
	 */
	int delUser(Integer id);

	/**
	 * 修改用户的方法
	 * 
	 * @param user
	 * @return
	 */
	int updateUser(User user);
}
