package rabbit.doc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RabbitController {

	/**
	 * 
	 * release-note页面
	 * @return
	 * 
	 */
	@RequestMapping("/release-note")
	public String releaseNode(){
		return "release-note";
	}
	/**
	 * 
	 * orm页面
	 * @return
	 * 
	 */
	@RequestMapping("/orm")
	public String orm(){
		return "orm";
	}

	/**
	 * 
	 * orm页面
	 * @return
	 * 
	 */
	@RequestMapping("/orm2")
	public String orm2(){
	    return "orm2";
	}

	@RequestMapping("/splited")
	public String splited(){
	    return "splited";
	}

	@RequestMapping("/performance")
	public String performance(){
	    return "performance";
	}

	/**
	 * 
	 * orm api页面
	 * @return
	 * 
	 */
	@RequestMapping("/ormapi")
	public String ormapi(){
		return "ormapi";
	}
	
	/**
	 * 
	 * orm api页面
	 * @return
	 * 
	 */
	@RequestMapping("/distributed")
	public String distributed(){
		return "distributed";
	}

	/**
	 * 
	 * rpc页面
	 * @return
	 * 
	 */
	@RequestMapping("/rpc")
	public String rpc(){
		return "rpc";
	}

	/**
	 * 
	 * spouter页面
	 * @return
	 * 
	 */
	@RequestMapping("/spouter")
	public String spouter(){
		return "spouter";
	}

	/**
	 * 
	 * spoutWriter页面
	 * @return
	 * 
	 */
	@RequestMapping("/spoutwriter")
	public String spoutWriter(){
		return "spout-writer";
	}

	/**
	 * 
	 * download页面
	 * @return
	 * 
	 */
	@RequestMapping("/download")
	public String download(){
		return "download";
	}
	
	/**
	 * 
	 * contact页面
	 * @return
	 * 
	 */
	@RequestMapping("/contact")
	public String contact(){
		return "contact";
	}

	/**
	 * 
	 * 首页
	 * @return
	 * 
	 */
	@RequestMapping("/")
	public String welcome(){
		return "rabbit";
	}
}
