package hack.app.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("map1")
public class MapController {
	
	@RequestMapping(value = "/")
	public String list() {
		
        return "map/map1";
    }

}
