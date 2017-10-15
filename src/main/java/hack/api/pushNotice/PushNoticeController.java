package hack.api.pushNotice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hack.api.todo.TodoResource;
import hack.domain.model.Todo;

@RestController
@RequestMapping("push")
public class PushNoticeController {
	
	@RequestMapping(method = RequestMethod.GET) 
    @ResponseStatus(HttpStatus.OK)
    public String getTodos() {

        return "Hello"; 
    }

}
