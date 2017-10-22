package hack.app.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("map")
public class MapController2 {
	
	@RequestMapping(value = "/2")
	public String list() {
		
        return "map/map2";
    }

}
