package ActionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionInterface 
{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
