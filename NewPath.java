package xidian.software.com.nfa;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;

public class NewPath {
	
	//  线的类型  是否 指向 自身
	private String TYPE = "TO_OTHER";
	
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
	
	//线的粗细
	private double strokeWidth = 5.0;
	
	// 箭头的 长度  与 角度
	private double len = 10;
	private double angle = 15.0;
	
	
	private NewText text;
	
	public NewPath(String tran) {
		// TODO Auto-generated constructor stub
		path = new Path();
		moveTo = new MoveTo();
		lineTo2 = new LineTo();
		lineTo3 = new LineTo();
		lineTo4 = new LineTo();
		lineTo5 = new LineTo();
		
		quadCurveTo = new QuadCurveTo();
		
		path.getElements().add(moveTo);
		path.getElements().add(quadCurveTo);
		path.getElements().add(lineTo2);
		path.getElements().add(lineTo3);
		path.getElements().add(lineTo4);
		path.getElements().add(lineTo5);
		
		text = new NewText();
		text.setTrans(tran);
		text.getText().setText(tran);
		text.setPath(this);
		
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
				if(path.getStrokeWidth() == strokeWidth) {
					path.setStrokeWidth(1.0);
				}else {
					path.setStrokeWidth(strokeWidth);
				}
				
			}
		});
		
		path.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				
				NewPath.this.drag(event.getSceneX(), event.getSceneY());
				
				//  判断在 直线的  哪一侧
//				System.out.println((ey-sy)*(event.getX()-OldX) + (sx-ex)*(event.getY()-OldY) + (ex*sy-sx*ey));

//				return ((P2.y - P1.y) * point.x + (P1.x-P2.x) * point.y + (P2.x*P1.y - P1.x*P2.y));
				
			}
		});
		
		
	}

	/*
	 *  首尾 坐标时  重画 path
	 */
	public NewPath resetPathByXY(double sX, double sY, double eX, double eY, double length) {
		
		
		path.getElements().remove(lineTo5);
		path.getElements().remove(lineTo4);
		path.getElements().remove(lineTo3);
		path.getElements().remove(lineTo2);
		path.getElements().remove(quadCurveTo);
		path.getElements().remove(moveTo);
		
		moveTo.setX(sX);
		moveTo.setY(sY);
		quadCurveTo.setX(eX);
		quadCurveTo.setY(eY);
		this.length = length;

		double midX = (eX+sX)/2;
		double midY = (eY+sY)/2;
		
		double x = length * (eY-sY)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) + midX;
		double y = -length * (eX-sX)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) + midY;
		
		//设置 控制点
		quadCurveTo.setControlX(x);
		quadCurveTo.setControlY(y);
		
		// 中间字母的 移动
		text.move(length * (eY-sY)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY))/2 + midX, -length * (eX-sX)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY))/2 + midY);
		
		
		path.getElements().add(moveTo);
		path.getElements().add(quadCurveTo);
		
//		double ax = moveTo.getX();
//		double ay = moveTo.getY();
		double bx = quadCurveTo.getX();
		double by = quadCurveTo.getY();
		
//		double axx = (double)28.28*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ax;
//		double ayy = (double)28.28*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ay;
		double bxx = -(double)toCircle.getCircle().getRadius()*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) +bx;
		double byy = -(double)toCircle.getCircle().getRadius()*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) +by;

		// 判断  线的类型  指向  自身 还是 指向 other
		if(!"TO_OTHER".equals(TYPE)) {
			bxx = bx;
			byy = by;

		}
		
		lineTo2.setX(bxx);
		lineTo2.setY(byy);
		path.getElements().add(lineTo2);
		
		
		double bxxx = len*(Math.cos(angle)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) - Math.sin(angle)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
		double byyy = len*(Math.cos(angle)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) + Math.sin(angle)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
		
		lineTo3.setX(bxx + bxxx);
		lineTo3.setY(byy + byyy);
		path.getElements().add(lineTo3);
		

		lineTo4.setX(bxx);
		lineTo4.setY(byy);
		path.getElements().add(lineTo4);

		
		bxxx = len*(Math.cos(angle)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) + Math.sin(angle)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
		byyy = len*(Math.cos(angle)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) - Math.sin(angle)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));

		lineTo5.setX(bxx + bxxx);
		lineTo5.setY(byy + byyy);
		path.getElements().add(lineTo5);
		
		return this;
		
	}
	
	/*
	 * 两个端点坐标不变   曲线的弧度变化
	 */
	public void drag(double mouseX, double mouseY) {
		double sX = moveTo.getX();
		double sY = moveTo.getY();
		double eX = quadCurveTo.getX();
		double eY = quadCurveTo.getY();
		
		double midX = (eX+sX)/2;
		double midY = (eY+sY)/2;
		
		
		// 这 算的 也太 麻烦了 吧  计算的是 鼠标距离 线 高度的 2 倍
		length = 2*Math.sqrt((mouseX-midX)*(mouseX-midX) + (mouseY-midY)*(mouseY-midY))*
				((eY-sY)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) * (mouseX-midX)/Math.sqrt((mouseX-midX)*(mouseX-midX) + (mouseY-midY)*(mouseY-midY))
				- (eX-sX)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) * (mouseY-midY)/Math.sqrt((mouseX-midX)*(mouseX-midX) + (mouseY-midY)*(mouseY-midY)));
		
		this.resetPathByXY(sX, sY, eX, eY, length);
		
//		double x = length * (eY-sY)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) + midX;
//		double y = -length * (eX-sX)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) + midY;
//		
//		//设置 控制点
//		quadCurveTo.setControlX(x);
//		quadCurveTo.setControlY(y);
//		
//		// 中间字母的 移动
//		test.move(length * (eY-sY)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY))/2 + midX, -length * (eX-sX)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY))/2 + midY);
//		
//		
//		path.getElements().remove(lineTo5);
//		path.getElements().remove(lineTo4);
//		path.getElements().remove(lineTo3);
//		path.getElements().remove(lineTo2);
//		path.getElements().remove(quadCurveTo);
//		path.getElements().remove(moveTo);
//		
//		path.getElements().add(moveTo);
//		path.getElements().add(quadCurveTo);
//		
////		double ax = moveTo.getX();
////		double ay = moveTo.getY();
//		double bx = quadCurveTo.getX();
//		double by = quadCurveTo.getY();
//		
////		double axx = (double)28.28*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ax;
////		double ayy = (double)28.28*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ay;
//		double bxx = -(double)fromCircle.getCircle().getRadius()*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) +bx;
//		double byy = -(double)fromCircle.getCircle().getRadius()*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) +by;
//
//		// 判断  线的类型  指向  自身 还是 指向 other
//		if(!"TO_OTHER".equals(TYPE)) {
//			bxx = bx;
//			byy = by;
//
//		}
//		
//		lineTo2.setX(bxx);
//		lineTo2.setY(byy);
//		path.getElements().add(lineTo2);
//		
//		
//		double bxxx = len*(Math.cos(angle)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) - Math.sin(angle)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
//		double byyy = len*(Math.cos(angle)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) + Math.sin(angle)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
//		
//		lineTo3.setX(bxx + bxxx);
//		lineTo3.setY(byy + byyy);
//		path.getElements().add(lineTo3);
//		
//
//		lineTo4.setX(bxx);
//		lineTo4.setY(byy);
//		path.getElements().add(lineTo4);
//
//		
//		bxxx = len*(Math.cos(angle)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) + Math.sin(angle)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
//		byyy = len*(Math.cos(angle)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) - Math.sin(angle)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
//
//		lineTo5.setX(bxx + bxxx);
//		lineTo5.setY(byy + byyy);
//		path.getElements().add(lineTo5);
		
	}
	
	/*
	 * 因为  圆的移动 进行移动
	 */
	public void circleDrag(double dragX, double dragY, String type) {
		
		if("start".equals(type)) {
			
			this.resetPathByXY(moveTo.getX() + dragX, moveTo.getY() + dragY,
					quadCurveTo.getX(), quadCurveTo.getY(), length);
//			
//			moveTo.setX(moveTo.getX() + dragX);
//			moveTo.setY(moveTo.getY() + dragY);
			
			
			
		}else if("end".equals(type)) {
			
			this.resetPathByXY(moveTo.getX(), moveTo.getY(),
					quadCurveTo.getX() + dragX, quadCurveTo.getY() + dragY, length);
//			
//			quadCurveTo.setX(quadCurveTo.getX() + dragX);
//			quadCurveTo.setY(quadCurveTo.getY() + dragY);
	
		}
		
		
		
//		double sX = moveTo.getX();
//		double sY = moveTo.getY();
//		double eX = quadCurveTo.getX();
//		double eY = quadCurveTo.getY();
//		
//		double midX = (eX+sX)/2;
//		double midY = (eY+sY)/2;
//		
//		double x = length * (eY-sY)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) + midX;
//		double y = -length * (eX-sX)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY)) + midY;
//		
//		quadCurveTo.setControlX(x);
//		quadCurveTo.setControlY(y);
//		
//
//		// 中间字母的 移动
//		test.move(length * (eY-sY)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY))/2 + midX, -length * (eX-sX)/Math.sqrt((eX-sX)*(eX-sX) + (eY-sY)*(eY-sY))/2 + midY);
//		
//		
//		path.getElements().remove(lineTo5);
//		path.getElements().remove(lineTo4);
//		path.getElements().remove(lineTo3);
//		path.getElements().remove(lineTo2);
//		path.getElements().remove(quadCurveTo);
//		path.getElements().remove(moveTo);
//		
//		path.getElements().add(moveTo);
//		path.getElements().add(quadCurveTo);
//		
////		double ax = moveTo.getX();
////		double ay = moveTo.getY();
//		double bx = quadCurveTo.getX();
//		double by = quadCurveTo.getY();
//		
////		double axx = (double)28.28*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ax;
////		double ayy = (double)28.28*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ay;
//		double bxx = -(double)fromCircle.getRadius()*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) +bx;
//		double byy = -(double)fromCircle.getRadius()*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) +by;
//
//		// 判断  线的类型  指向  自身 还是 指向 other
//		if(!"TO_OTHER".equals(TYPE)) {
//			bxx = bx;
//			byy = by;
//
//		}
//		
//		
//		lineTo2.setX(bxx);
//		lineTo2.setY(byy);
//		path.getElements().add(lineTo2);
//		
//		
//		double bxxx = len*(Math.cos(angle)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) - Math.sin(angle)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
//		double byyy = len*(Math.cos(angle)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) + Math.sin(angle)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
//		
//		lineTo3.setX(bxx + bxxx);
//		lineTo3.setY(byy + byyy);
//		path.getElements().add(lineTo3);
//		
//
//		lineTo4.setX(bxx);
//		lineTo4.setY(byy);
//		path.getElements().add(lineTo4);
//
//		
//		bxxx = len*(Math.cos(angle)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) + Math.sin(angle)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
//		byyy = len*(Math.cos(angle)*(by-y)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)) - Math.sin(angle)*(bx-x)/Math.sqrt((bx-x)*(bx-x) + (by-y)*(by-y)));
//
//		lineTo5.setX(bxx + bxxx);
//		lineTo5.setY(byy + byyy);
//		path.getElements().add(lineTo5);
//		
//		
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

	

	public NewText getText() {
		return text;
	}

	public void setText(NewText text) {
		this.text = text;
	}

	public double getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(double strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public double getLen() {
		return len;
	}

	public void setLen(double len) {
		this.len = len;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	
	
	

}
