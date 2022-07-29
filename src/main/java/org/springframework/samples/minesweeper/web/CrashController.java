package org.springframework.samples.minesweeper.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller used to showcase what happens when an exception is thrown
 *
 * @author Michael Isvy
 *         <p/>
 *         Also see how the bean of type 'SimpleMappingExceptionResolver' has
 *         been declared inside /WEB-INF/mvc-core-config.xml
 */
@Controller
public class CrashController {

	@GetMapping(value = "/oups")
	public String triggerException() {
		throw new RuntimeException(
				"Expected: controller used to showcase what " + "happens when an exception is thrown");
	}
}