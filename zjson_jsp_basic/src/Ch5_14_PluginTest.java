import java.awt.*;
import java.applet.Applet;

public class Ch5_14_PluginTest extends Applet {
	String name;
	
	public void init(){
		name=getParameter("name");
	}
	
	public void paint(Graphics g){
		g.drawString(name,10,10);
	}
}
