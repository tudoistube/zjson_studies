package ActionManager;

public class ActionForward {
	private boolean isRedirect=false; // redirect 판별 여부
	private String path=null; // 이동할 주소
	
	public boolean isRedirect(){
		return isRedirect;
	}
	
	public String getPath(){
		return path;
	}
	
	public void setRedirect(boolean b){
		isRedirect=b;
	}
	
	public void setPath(String string){
		path=string;
	}
}
