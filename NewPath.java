package xidian.software.com.nfa;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;

public class NewPath {
	
	private Path path;
	//线
	private MoveTo moveTo;
	private QuadCurveTo quadCurveTo;
	private LineTo lineTo2;
	private LineTo lineTo3;
	private LineTo lineTo4;
	private LineTo lineTo5;
	
	private NewCircle fromCircle;
	private NewCircle toCircle;
	
	// control  坐标
	private double length;
	
	private NewTest test;
	
	public NewPath() {
		// TODO Auto-generated constructor stub
		path = new Path();
		moveTo = new MoveTo();
		lineTo2 = new LineTo();
		lineTo3 = new LineTo();
		lineTo4 = new LineTo();
		lineTo5 = new LineTo();
		
		quadCurveTo = new QuadCurveTo();
		
		
		path.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		path.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(path.getStrokeWidth() == 5.0) {
					path.setStrokeWidth(1.0);
				}else {
					path.setStrokeWidth(5.0);
				}
				
			}
		});
		
		path.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				
				double sX = moveTo.getX();
				double sY = moveTo.getY();
				double eX = quadCurveTo.getX();
				double eY = quadCurveTo.getY();
				
				double midX = (eX+sX)/2;
				double midY = (eY+sY)/2;
				
				double mouseX = event.getSceneX();
				double mouseY = event.getSceneY();
				
				
				// 这 算的 也太 麻烦了 吧
				length = 2*Math.sqrt((mouseX-midX)*(mouseX-midX) + (mouseY-midY)*(mouseY-midY))*
						((eY-sY)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) * (mouseX-midX)/Math.sqrt((mouseX-midX)*(mouseX-midX) + (mouseY-midY)*(mouseY-midY))
						- (eX-sX)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) * (mouseY-midY)/Math.sqrt((mouseX-midX)*(mouseX-midX) + (mouseY-midY)*(mouseY-midY)));
				
				double x = length * (eY-sY)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) + midX;
				double y = -length * (eX-sX)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) + midY;
				
				quadCurveTo.setControlX(x);
				quadCurveTo.setControlY(y);
				
				
				path.getElements().remove(lineTo5);
				path.getElements().remove(lineTo4);
				path.getElements().remove(lineTo3);
				path.getElements().remove(lineTo2);
				path.getElements().remove(quadCurveTo);
				path.getElements().remove(moveTo);
				
				path.getElements().add(moveTo);
				path.getElements().add(quadCurveTo);
				
//				double ax = moveTo.getX();
//				double ay = moveTo.getY();
				double bx = quadCurveTo.getX();
				double by = quadCurveTo.getY();
				
//				double axx = (double)28.28*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ax;
//				double ayy = (double)28.28*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ay;
				double bxx = -(double)28.28*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) +bx;
				double byy = -(double)28.28*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) +by;

				lineTo2.setX(bxx);
				lineTo2.setY(byy);
				path.getElements().add(lineTo2);
				
				
				double bxxx = 10*(Math.cos(15.0)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) - Math.sin(15.0)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
				double byyy = 10*(Math.cos(15.0)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) + Math.sin(15.0)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
				
				lineTo3.setX(bxx + bxxx);
				lineTo3.setY(byy + byyy);
				path.getElements().add(lineTo3);
				

				lineTo4.setX(bxx);
				lineTo4.setY(byy);
				path.getElements().add(lineTo4);

				
				bxxx = 10*(Math.cos(15.0)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) + Math.sin(15.0)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
				byyy = 10*(Math.cos(15.0)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) - Math.sin(15.0)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));

				lineTo5.setX(bxx + bxxx);
				lineTo5.setY(byy + byyy);
				path.getElements().add(lineTo5);
				
				System.out.println(x + "   " + y);
				
				//  判断在 直线的  哪一侧
//				System.out.println((ey-sy)*(event.getX()-OldX) + (sx-ex)*(event.getY()-OldY) + (ex*sy-sx*ey));

//				return ((P2.y - P1.y) * point.x + (P1.x-P2.x) * point.y + (P2.x*P1.y - P1.x*P2.y));
				
			}
		});
		
		
	}

	
	public void drag(double dragX, double dragY, String type) {
		
		if("start".equals(type)) {
			moveTo.setX(moveTo.getX() + dragX);
			moveTo.setY(moveTo.getY() + dragY);
			
			
			
		}else if("end".equals(type)) {
			quadCurveTo.setX(quadCurveTo.getX() + dragX);
			quadCurveTo.setY(quadCurveTo.getY() + dragY);
	
		}
		
		double sX = moveTo.getX();
		double sY = moveTo.getY();
		double eX = quadCurveTo.getX();
		double eY = quadCurveTo.getY();
		
		double midX = (eX+sX)/2;
		double midY = (eY+sY)/2;
		
		double x = length * (eY-sY)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) + midX;
		double y = -length * (eX-sX)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) + midY;
		
		quadCurveTo.setControlX(x);
		quadCurveTo.setControlY(y);
		
		path.getElements().remove(lineTo5);
		path.getElements().remove(lineTo4);
		path.getElements().remove(lineTo3);
		path.getElements().remove(lineTo2);
		path.getElements().remove(quadCurveTo);
		path.getElements().remove(moveTo);
		
		path.getElements().add(moveTo);
		path.getElements().add(quadCurveTo);
		
//		double ax = moveTo.getX();
//		double ay = moveTo.getY();
		double bx = quadCurveTo.getX();
		double by = quadCurveTo.getY();
		
//		double axx = (double)28.28*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ax;
//		double ayy = (double)28.28*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ay;
		double bxx = -(double)28.28*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) +bx;
		double byy = -(double)28.28*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) +by;

		lineTo2.setX(bxx);
		lineTo2.setY(byy);
		path.getElements().add(lineTo2);
		
		
		double bxxx = 10*(Math.cos(15.0)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) - Math.sin(15.0)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
		double byyy = 10*(Math.cos(15.0)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) + Math.sin(15.0)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
		
		lineTo3.setX(bxx + bxxx);
		lineTo3.setY(byy + byyy);
		path.getElements().add(lineTo3);
		

		lineTo4.setX(bxx);
		lineTo4.setY(byy);
		path.getElements().add(lineTo4);

		
		bxxx = 10*(Math.cos(15.0)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) + Math.sin(15.0)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
		byyy = 10*(Math.cos(15.0)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) - Math.sin(15.0)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));

		lineTo5.setX(bxx + bxxx);
		lineTo5.setY(byy + byyy);
		path.getElements().add(lineTo5);
		
		
	}
	
	
	public QuadCurveTo getQuadCurveTo() {
		return quadCurveTo;
	}





	public void setQuadCurveTo(QuadCurveTo quadCurveTo) {
		this.quadCurveTo = quadCurveTo;
	}



	public NewCircle getFromCircle() {
		return fromCircle;
	}



	public void setFromCircle(NewCircle fromCircle) {
		this.fromCircle = fromCircle;
	}



	public NewCircle getToCircle() {
		return toCircle;
	}



	public void setToCircle(NewCircle toCircle) {
		this.toCircle = toCircle;
	}



	public Path getPath() {
		return path;
	}


	public void setPath(Path path) {
		this.path = path;
	}


	public MoveTo getMoveTo() {
		return moveTo;
	}


	public void setMoveTo(MoveTo moveTo) {
		this.moveTo = moveTo;
	}


	public LineTo getLineTo2() {
		return lineTo2;
	}


	public void setLineTo2(LineTo lineTo2) {
		this.lineTo2 = lineTo2;
	}


	public LineTo getLineTo3() {
		return lineTo3;
	}


	public void setLineTo3(LineTo lineTo3) {
		this.lineTo3 = lineTo3;
	}


	public LineTo getLineTo4() {
		return lineTo4;
	}


	public void setLineTo4(LineTo lineTo4) {
		this.lineTo4 = lineTo4;
	}


	public LineTo getLineTo5() {
		return lineTo5;
	}


	public void setLineTo5(LineTo lineTo5) {
		this.lineTo5 = lineTo5;
	}


	public double getLength() {
		return length;
	}


	public void setLength(double length) {
		this.length = length;
	}


	public NewTest getTest() {
		return test;
	}


	public void setTest(NewTest test) {
		this.test = test;
	}
	
	
	

}
