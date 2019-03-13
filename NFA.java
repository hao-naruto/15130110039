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
	
	
	// 状态 经过 转移函数 指向 自身
	public static NFA toSelfNFA(String self, String tran) {
		NFA nfa = new NFA();
		
		NewCircle newCircle = new NewCircle("SIMPLE", self);
		NewPath newPath = new NewPath(tran);
		newPath.setTYPE("TO_SELF");
		
		newCircle.allSetXY(100.0, 100.0);
		
		newCircle.getFrom().add(newPath);
		newCircle.getTo().add(newPath);
		newPath.setFromCircle(newCircle);
		newPath.setToCircle(newCircle);
		
		// 添加到 NFA
		nfa.getCircleList().add(newCircle);
		nfa.getPathList().add(newPath);
		
//		// 转移 参数
//		NewTest test = new NewTest();
//		test.setTrans(tran);
//		test.getText().setText(test.getTrans());
//		test.getText().setId(test.getTrans());
//		test.setPath(newPath);
//		newPath.setTest(test);
		
		
		for(int i = 0; i < nfa.getPathList().size(); i++) {
			
			NewPath path = nfa.getPathList().get(i);
			
			path.resetPathByXY(path.getFromCircle().getCircle().getCenterX() + newCircle.getRadius()*Math.sin(30.0),
					path.getFromCircle().getCircle().getCenterY() - newCircle.getRadius()*Math.cos(30.0),
					path.getToCircle().getCircle().getCenterX() - newCircle.getRadius()*Math.sin(30.0),
					path.getToCircle().getCircle().getCenterY() - newCircle.getRadius()*Math.cos(30.0),
					100.0);
			
//			// 设置 起点和终点
//			path.getMoveTo().setX(path.getFromCircle().getCircle().getCenterX() + newCircle.getRadius()*Math.sin(30.0));
//			path.getMoveTo().setY(path.getFromCircle().getCircle().getCenterY() - newCircle.getRadius()*Math.cos(30.0));
//			
//			path.getQuadCurveTo().setX(path.getToCircle().getCircle().getCenterX() - newCircle.getRadius()*Math.sin(30.0));
//			path.getQuadCurveTo().setY(path.getToCircle().getCircle().getCenterY() - newCircle.getRadius()*Math.cos(30.0));
//			
//			double x = path.getFromCircle().getCircle().getCenterX();
//			double y = path.getFromCircle().getCircle().getCenterY() - 100;
//			
//			// 设置  控制点
//			path.getQuadCurveTo().setControlX(x);
//			path.getQuadCurveTo().setControlY(y);
//			
//			path.getPath().getElements().add(path.getMoveTo());
//			path.getPath().getElements().add(path.getQuadCurveTo());
//			
//
//			
//			double ax = path.getMoveTo().getX();
//			double ay = path.getMoveTo().getY();
//			double bx = path.getQuadCurveTo().getX();
//			double by = path.getQuadCurveTo().getY();
//			
////			double axx = (double)28.28*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ax;
////			double ayy = (double)28.28*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ay;
//			double bxx = path.getToCircle().getCircle().getCenterX() - newCircle.getRadius()*Math.sin(30.0);
//			double byy = path.getToCircle().getCircle().getCenterY() - newCircle.getRadius()*Math.cos(30.0);
//
//			path.getLineTo2().setX(bxx);
//			path.getLineTo2().setY(byy);
//			path.getPath().getElements().add(path.getLineTo2());
//			
//			//  使用 长度和角度 求 箭头的坐标
//			double bxxx = path.getLen()*(Math.cos(path.getAngle())*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) - Math.sin(path.getAngle())*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
//			double byyy = path.getLen()*(Math.cos(path.getAngle())*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) + Math.sin(path.getAngle())*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
//			
//			path.getLineTo3().setX(bxx + bxxx);
//			path.getLineTo3().setY(byy + byyy);
//			path.getPath().getElements().add(path.getLineTo3());
//			
//	
//			path.getLineTo4().setX(bxx);
//			path.getLineTo4().setY(byy);
//			path.getPath().getElements().add(path.getLineTo4());
//
//			
//			bxxx = path.getLen()*(Math.cos(path.getAngle())*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) + Math.sin(path.getAngle())*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
//			byyy = path.getLen()*(Math.cos(path.getAngle())*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) - Math.sin(path.getAngle())*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
//
//			path.getLineTo5().setX(bxx + bxxx);
//			path.getLineTo5().setY(byy + byyy);
//			path.getPath().getElements().add(path.getLineTo5());
//			
//			// 设置   转移参数 坐标
//			test.getText().setLayoutX((ax+bx)/2);
//			test.getText().setLayoutY((ay+by)/2 - 50);	
//			
//			path.setLength(100.0);
		}
		
		return nfa;
	}
	
	// 构造 NFA
	public static NFA simpleNFA(String start, String end, String tran) {
		
		if(start.equals(end)) {
			return NFA.toSelfNFA(start, tran);
		}
		
		NFA nfa = new NFA();
		
		NewCircle newCircle2 = NewCircle.getSimpleCircle(start);
		NewCircle newCircle1 = NewCircle.getSimpleCircle(end);
		newCircle1.allSetXY(30.0, 200.0);
		newCircle2.allSetXY(500.0, 500.0);
		
		NewPath newPath = new NewPath(tran);
		
		newCircle1.getFrom().add(newPath);
		newCircle2.getTo().add(newPath);
		newPath.setFromCircle(newCircle1);
		newPath.setToCircle(newCircle2);
		
		// 添加到 NFA
		nfa.getCircleList().add(newCircle1);
		nfa.getCircleList().add(newCircle2);
		nfa.getPathList().add(newPath);
		
//		// 转移 参数
//		NewTest test = new NewTest();
//		test.setTrans(tran);
//		test.getText().setText(test.getTrans());
//		test.getText().setId(test.getTrans());
//		test.setPath(newPath);
//		newPath.setTest(test);
		
		
		
		for(int i = 0; i < nfa.getPathList().size(); i++) {
			
			NewPath path = nfa.getPathList().get(i);
			
			path.resetPathByXY(path.getFromCircle().getCircle().getCenterX(), path.getFromCircle().getCircle().getCenterY(),
					path.getToCircle().getCircle().getCenterX(), path.getToCircle().getCircle().getCenterY(), 0);
			
//			path.getMoveTo().setX(path.getFromCircle().getCircle().getCenterX());
//			path.getMoveTo().setY(path.getFromCircle().getCircle().getCenterY());
//			
//			path.getQuadCurveTo().setX(path.getToCircle().getCircle().getCenterX());
//			path.getQuadCurveTo().setY(path.getToCircle().getCircle().getCenterY());
//			
//			
//			path.getPath().getElements().add(path.getMoveTo());
//			path.getPath().getElements().add(path.getQuadCurveTo());
//			
//
//			
//			double ax = path.getMoveTo().getX();
//			double ay = path.getMoveTo().getY();
//			double bx = path.getQuadCurveTo().getX();
//			double by = path.getQuadCurveTo().getY();
//			
//			path.getQuadCurveTo().setControlX((ax+by)/2);
//			path.getQuadCurveTo().setControlY((ay+by)/2);
//			
//			System.out.println(path.getQuadCurveTo().getControlX() + "  " + path.getQuadCurveTo().getControlY());
//			
////			double axx = (double)28.28*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ax;
////			double ayy = (double)28.28*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ay;
//			double bxx = -(double)newCircle1.getRadius()*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +bx;
//			double byy = -(double)newCircle1.getRadius()*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +by;
//
//			path.getLineTo2().setX(bxx);
//			path.getLineTo2().setY(byy);
//			path.getPath().getElements().add(path.getLineTo2());
//			
//			//  使用 长度和角度 求 箭头的坐标
//			double bxxx = path.getLen()*(Math.cos(path.getAngle())*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) - Math.sin(path.getAngle())*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));
//			double byyy = path.getLen()*(Math.cos(path.getAngle())*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) + Math.sin(path.getAngle())*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));
//			
//			path.getLineTo3().setX(bxx + bxxx);
//			path.getLineTo3().setY(byy + byyy);
//			path.getPath().getElements().add(path.getLineTo3());
//			
//	
//			path.getLineTo4().setX(bxx);
//			path.getLineTo4().setY(byy);
//			path.getPath().getElements().add(path.getLineTo4());
//
//			
//			bxxx = path.getLen()*(Math.cos(path.getAngle())*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) + Math.sin(path.getAngle())*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));
//			byyy = path.getLen()*(Math.cos(path.getAngle())*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) - Math.sin(path.getAngle())*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));
//
//			path.getLineTo5().setX(bxx + bxxx);
//			path.getLineTo5().setY(byy + byyy);
//			path.getPath().getElements().add(path.getLineTo5());
//			
//			// 设置   转移参数 坐标
//			test.getText().setLayoutX((ax+bx)/2);
//			test.getText().setLayoutY((ay+by)/2);		
		}
		
		return nfa;
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
