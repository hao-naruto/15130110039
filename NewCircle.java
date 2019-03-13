package xidian.software.com.nfa;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class NewCircle {
	
	// 状态的类型   SIMPLE STAR　END
	private String TYPE = "SIMPLE";
	
	// 初态
	private Polygon polygon;
	// 终态
	private Circle inCircle;
	
	private Circle circle;
	private ArrayList<NewPath> from;
	private ArrayList<NewPath> to;
	
	private NewState newState;

	private double OldX;
	private double OldY;
	
	
	// 圆的 圆点坐标  圆的 半径 
	private double centerX = 20.0;
	private double centerY = 20.0;
	private double radius = 28.28;
	
	private double inRadius = 20.00;
	
	/*
	 * type 类型  初态 终态   普通状态
	 * state 状态 ABC
	 */
	public NewCircle(String type, String state) {
		// TODO Auto-generated constructor stub
		
		this.TYPE = type;
		
		circle = new Circle();
		from = new ArrayList<NewPath>();
		to = new ArrayList<NewPath>();
		
		// 设置 状态字
		newState = new NewState(state);
		newState.setXY(centerX, centerY);
		newState.setCircle(this);
		
		inCircle = new Circle();
		polygon = new Polygon();
		
		
		circle.setCenterX(centerX);
		circle.setCenterY(centerY);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.WHITE);
		circle.setRadius(radius);
		
		// 终态
		if("END".equals(type)) {
			inCircle.setCenterX(centerX);
			inCircle.setCenterY(centerY);
			inCircle.setStroke(Color.BLACK);
			inCircle.setFill(Color.WHITE);
			inCircle.setRadius(inRadius);
		}
		// 初态
		if("START".equals(type)) {
			// 设置 坐标
			polygon.getPoints().addAll(new Double[] {
					centerX - radius, centerY,
					centerX - radius - radius*2/3, centerY - radius*3/4,
					centerX - radius - radius*2/3, centerY + radius*3/4
			});
			polygon.setStroke(Color.BLACK);
			polygon.setFill(Color.WHITE);
			
		}
		
		
		
		
		circle.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				OldX = event.getX();
				OldY = event.getY();

			}
		});
		
		
		circle.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {	
				
				
				// 移动  自身
				NewCircle.this.drag(event.getX() - OldX, event.getY() - OldY);
				
				// 移动  状态
				newState.drag(event.getX() - OldX, event.getY() - OldY);
				
				// 移动 线
				for(int i = 0; i < from.size(); i ++) {
					NewPath path = from.get(i);
					
					//  移动 线
					path.circleDrag(event.getX() - OldX, event.getY() - OldY, "start");

								
					
				}
				for(int i = 0; i < to.size(); i ++) {
					NewPath path = to.get(i);
					
					path.circleDrag(event.getX() - OldX, event.getY() - OldY, "end");
					
				}
				
			}
		});
		
		inCircle.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				OldX = event.getX();
				OldY = event.getY();

			}
		});
		
		
		inCircle.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {	
				
				
				// 移动  自身
				NewCircle.this.drag(event.getX() - OldX, event.getY() - OldY);
				
				// 移动  状态
				newState.drag(event.getX() - OldX, event.getY() - OldY);
				
				// 移动 线
				for(int i = 0; i < from.size(); i ++) {
					NewPath path = from.get(i);
					
					//  移动 线
					path.circleDrag(event.getX() - OldX, event.getY() - OldY, "start");

								
					
				}
				for(int i = 0; i < to.size(); i ++) {
					NewPath path = to.get(i);
					
					path.circleDrag(event.getX() - OldX, event.getY() - OldY, "end");
					
				}
				
			}
		});
		
	}
	/*
	 *   将  整体 进行 移动 
	 */
	public NewCircle allSetXY(double x, double y) {
		
		circle.setCenterX(x);
		circle.setCenterY(y);
		if("END".equals(TYPE)) {
			inCircle.setCenterX(x);
			inCircle.setCenterY(y);
		}
		
		if("START".equals(TYPE)) {
			polygon.getPoints().remove(0, 6);
			polygon.getPoints().addAll(new Double[] {
					x - radius, y,
					x - radius - radius*2/3, y - radius*3/4,
					x - radius - radius*2/3, y + radius*3/4
			});
		}
		
		newState.setXY(x, y);
		
		return this;
		
	}
	
	
	/*
	 * 构造一个  初态
	 */
	public static NewCircle getStartCircle(String state) {
		NewCircle newCircle = new NewCircle("START", state);

		
		
		return newCircle;
	}
	/*
	 *   返回 一个 终态
	 */
	public static NewCircle getEndCircle(String state) {
		NewCircle newCircle = new NewCircle("END", state);
		
		
		return newCircle;
	}
	
	/*
	 * 返回 一个  普通状态
	 */
	public static NewCircle getSimpleCircle(String state) {
		NewCircle newCircle = new NewCircle("SIMPLE", state);
		
		return newCircle;
	}

	/*
	 *  圆的 移动
	 */
	public void drag(double dragX, double dragY) {
		circle.setLayoutX(dragX + circle.getLayoutX());
		circle.setLayoutY(dragY + circle.getLayoutY());
		
		if("END".equals(TYPE)) {
			
			inCircle.setLayoutX(dragX + inCircle.getLayoutX());
			inCircle.setLayoutY(dragY + inCircle.getLayoutY());
			
		}else if("START".equals(TYPE)) {
			polygon.setLayoutX(dragX + polygon.getLayoutX());
			polygon.setLayoutY(dragY + polygon.getLayoutY());
		}
		
	}
	
	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public ArrayList<NewPath> getFrom() {
		return from;
	}


	public void setFrom(ArrayList<NewPath> from) {
		this.from = from;
	}


	public ArrayList<NewPath> getTo() {
		return to;
	}


	public void setTo(ArrayList<NewPath> to) {
		this.to = to;
	}


	public double getOldX() {
		return OldX;
	}

	public void setOldX(double oldX) {
		OldX = oldX;
	}

	public double getOldY() {
		return OldY;
	}

	public void setOldY(double oldY) {
		OldY = oldY;
	}


	public NewState getNewState() {
		return newState;
	}


	public void setNewState(NewState newState) {
		this.newState = newState;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}

	public Circle getInCircle() {
		return inCircle;
	}

	public void setInCircle(Circle inCircle) {
		this.inCircle = inCircle;
	}

	public double getInRadius() {
		return inRadius;
	}

	public void setInRadius(double inRadius) {
		this.inRadius = inRadius;
	}
	
	
	
	
}
