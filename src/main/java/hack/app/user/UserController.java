package hack.app.user;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.exception.BusinessException;

import hack.domain.model.User;
import hack.domain.service.user.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Inject
	UserService userService;
	
	private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);
	
	@RequestMapping(value = "/")
	public String list() {
		
		Collection<User> users = userService.findAll();
		
		System.out.println(users);
        return "welcome/home";
    }

}
