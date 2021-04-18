/**
 * 
 */
package com.sf.etps.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** @version:
* @Description: （对类进行功能描述） 
* @author: 80004520  
*/
@RestController
@RequestMapping(value = "/etps-eureka-server")
public class EchoFacade {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/echo")
	public String echoTest() {
		logger.info("activity echo test请求...");
		return "ok";
	}
	
	
	
	
}
