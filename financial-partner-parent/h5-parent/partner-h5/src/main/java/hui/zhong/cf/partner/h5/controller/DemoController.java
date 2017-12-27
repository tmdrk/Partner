package hui.zhong.cf.partner.h5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
	
	@RequestMapping("/get")
	@ResponseBody
	public Object get(){
		
		return "success";
	}

}
