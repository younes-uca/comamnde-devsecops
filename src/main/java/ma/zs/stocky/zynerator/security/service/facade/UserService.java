package ma.zs.stocky.zynerator.security.service.facade;

import ma.zs.stocky.zynerator.security.bean.User;
import ma.zs.stocky.zynerator.security.dao.criteria.core.UserCriteria;
import ma.zs.stocky.zynerator.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IService<User, UserCriteria>, UserDetailsService {

    User findByUsername(String username);

    User findByUsernameWithRoles(String username);

    String cryptPassword(String value);

    boolean changePassword(String username, String newPassword);

    int deleteByUsername(String username);

    UserDetails loadUserByUsername(String username);


}
