package hack.app.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("map2")
public class MapController2 {
	
	@RequestMapping(value = "/")
	public String list() {
		
        return "map/map2";
    }

}
