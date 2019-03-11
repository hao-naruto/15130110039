package xidian.software.com.nfa;

import java.util.ArrayList;

public class NFA {

	private ArrayList<NewCircle> circleList;
	
	private ArrayList<NewPath> pathList;
	
	public NFA() {
		// TODO Auto-generated constructor stub
		circleList = new ArrayList<NewCircle>();
		pathList = new ArrayList<NewPath>();
	}
	

	public ArrayList<NewCircle> getCircleList() {
		return circleList;
	}

	public void setCircleList(ArrayList<NewCircle> circleList) {
		this.circleList = circleList;
	}


	public ArrayList<NewPath> getPathList() {
		return pathList;
	}


	public void setPathList(ArrayList<NewPath> pathList) {
		this.pathList = pathList;
	}

	
	
	
	
}
