package xidian.software.com.nfa;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class NewCircle {
	
	private Circle circle;
	private ArrayList<NewPath> from;
	private ArrayList<NewPath> to;
	private ArrayList<NewPath> self;
	
	private NewState newState;

	private double OldX;
	private double OldY;
	
	public NewCircle() {
		// TODO Auto-generated constructor stub
		circle = new Circle();
		from = new ArrayList<NewPath>();
		to = new ArrayList<NewPath>();
		self = new ArrayList<NewPath>();
		
		
		circle.setCenterX(20.0);
		circle.setCenterY(20.0);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.WHITE);
		circle.setRadius(28.28);
		
		
		
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
		
		
		
	}

	/*
	 *  圆的 移动
	 */
	public void drag(double dragX, double dragY) {
		circle.setLayoutX(dragX + circle.getLayoutX());
		circle.setLayoutY(dragY + circle.getLayoutY());
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


	public ArrayList<NewPath> getSelf() {
		return self;
	}


	public void setSelf(ArrayList<NewPath> self) {
		this.self = self;
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
	
	
	
}
