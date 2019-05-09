package cn.ntshare.blog.service;

import cn.ntshare.blog.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;

/**·
 * Created By Seven.wk
 * Description: 用户服务接口
 * Created At 2018/08/06
 */
public interface UserService {

    UserDTO queryUserById(Integer id);
}
