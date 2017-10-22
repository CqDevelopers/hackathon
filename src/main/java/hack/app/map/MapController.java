package hack.app.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("map")
public class MapController {
	
	@RequestMapping(value = "/1")
	public String list() {
		
        return "map/map1";
    }

}
