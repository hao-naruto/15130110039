package xidian.software.com.nfa;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class NewCircle {
	
	private Circle circle;
	private Text text;
	private ArrayList<NewPath> from;
	private ArrayList<NewPath> to;
	private ArrayList<NewPath> self;
	
	

	private double OldX;
	private double OldY;
	
	public NewCircle() {
		// TODO Auto-generated constructor stub
		circle = new Circle();
		text = new Text();
		from = new ArrayList<NewPath>();
		to = new ArrayList<NewPath>();
		self = new ArrayList<NewPath>();
		
		
		circle.setCenterX(20.0);
		circle.setCenterY(20.0);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.WHITE);
		circle.setRadius(28.28);
		
		
		
		// 文本框
		text.setText("a");
		text.setLayoutX(18.0);
		text.setLayoutY(22.0);


		text.setStyle("-fx-border-width:1;-fx-border-color:black;-fx-control-inner-background:#ffff96;");// 设置文本框样式
				
		
		
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
				

				
				// 移动透明长方形
				circle.setLayoutX(event.getX() - OldX + circle.getLayoutX());
				circle.setLayoutY(event.getY() - OldY + circle.getLayoutY());
				// 移动文本框
				text.setLayoutX(event.getX() - OldX + text.getLayoutX());
				text.setLayoutY(event.getY() - OldY + text.getLayoutY());
				// 移动 线
				for(int i = 0; i < from.size(); i ++) {
					NewPath path = from.get(i);
					
					path.getMoveTo().setX(path.getMoveTo().getX() + event.getX() - OldX);
					path.getMoveTo().setY(path.getMoveTo().getY() + event.getY() - OldY);
					
					
					path.getPath().getElements().remove(path.getLineTo5());
					path.getPath().getElements().remove(path.getLineTo4());
					path.getPath().getElements().remove(path.getLineTo3());
					path.getPath().getElements().remove(path.getLineTo2());
					path.getPath().getElements().remove(path.getLineTo1());
					path.getPath().getElements().remove(path.getMoveTo());
					
					path.getPath().getElements().add(path.getMoveTo());
					path.getPath().getElements().add(path.getLineTo1());
					
					double ax = path.getMoveTo().getX();
					double ay = path.getMoveTo().getY();
					double bx = path.getLineTo1().getX();
					double by = path.getLineTo1().getY();
					
					double axx = (double)28.28*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ax;
					double ayy = (double)28.28*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ay;
					double bxx = -(double)28.28*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +bx;
					double byy = -(double)28.28*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +by;

					path.getLineTo2().setX(bxx);
					path.getLineTo2().setY(byy);
					path.getPath().getElements().add(path.getLineTo2());
					
					
					double bxxx = 10*(Math.cos(15.0)*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) - Math.sin(15.0)*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));
					double byyy = 10*(Math.cos(15.0)*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) + Math.sin(15.0)*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));
					
					path.getLineTo3().setX(bxx + bxxx);
					path.getLineTo3().setY(byy + byyy);
					path.getPath().getElements().add(path.getLineTo3());
					
			
					path.getLineTo4().setX(bxx);
					path.getLineTo4().setY(byy);
					path.getPath().getElements().add(path.getLineTo4());

					
					bxxx = 10*(Math.cos(15.0)*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) + Math.sin(15.0)*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));
					byyy = 10*(Math.cos(15.0)*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) - Math.sin(15.0)*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));

					path.getLineTo5().setX(bxx + bxxx);
					path.getLineTo5().setY(byy + byyy);
					path.getPath().getElements().add(path.getLineTo5());
					
								
					
				}
				for(int i = 0; i < to.size(); i ++) {
					NewPath path = to.get(i);
					
					path.getLineTo1().setX(path.getLineTo1().getX() + event.getX() - OldX);
					path.getLineTo1().setY(path.getLineTo1().getY() + event.getY() - OldY);
					
					path.getPath().getElements().remove(path.getLineTo5());
					path.getPath().getElements().remove(path.getLineTo4());
					path.getPath().getElements().remove(path.getLineTo3());
					path.getPath().getElements().remove(path.getLineTo2());
					path.getPath().getElements().remove(path.getLineTo1());
					path.getPath().getElements().remove(path.getMoveTo());
					
					path.getPath().getElements().add(path.getMoveTo());
					path.getPath().getElements().add(path.getLineTo1());
					
					double ax = path.getMoveTo().getX();
					double ay = path.getMoveTo().getY();
					double bx = path.getLineTo1().getX();
					double by = path.getLineTo1().getY();
					
					double axx = (double)28.28*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ax;
					double ayy = (double)28.28*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +ay;
					double bxx = -(double)28.28*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +bx;
					double byy = -(double)28.28*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) +by;

					path.getLineTo2().setX(bxx);
					path.getLineTo2().setY(byy);
					path.getPath().getElements().add(path.getLineTo2());
					
					
					double bxxx = 10*(Math.cos(15.0)*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) - Math.sin(15.0)*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));
					double byyy = 10*(Math.cos(15.0)*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) + Math.sin(15.0)*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));
					
					path.getLineTo3().setX(bxx + bxxx);
					path.getLineTo3().setY(byy + byyy);
					path.getPath().getElements().add(path.getLineTo3());
					
			
					path.getLineTo4().setX(bxx);
					path.getLineTo4().setY(byy);
					path.getPath().getElements().add(path.getLineTo4());

					
					bxxx = 10*(Math.cos(15.0)*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) + Math.sin(15.0)*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));
					byyy = 10*(Math.cos(15.0)*(by-ay)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)) - Math.sin(15.0)*(bx-ax)/Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));

					path.getLineTo5().setX(bxx + bxxx);
					path.getLineTo5().setY(byy + byyy);
					path.getPath().getElements().add(path.getLineTo5());
					
				}
				
			}
		});
		
		
		
		text.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				OldX = event.getX();
				OldY = event.getY();

			}
		});
		
		
		text.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				// 移动透明长方形
				circle.setLayoutX(event.getX() - OldX + circle.getLayoutX());
				circle.setLayoutY(event.getY() - OldY + circle.getLayoutY());
				// 移动文本框
				text.setLayoutX(event.getX() - OldX + text.getLayoutX());
				text.setLayoutY(event.getY() - OldY + text.getLayoutY());
				
				// 移动 线
				for(int i = 0; i < from.size(); i ++) {
					
				}
				for(int i = 0; i < to.size(); i ++) {
					
				}
				
			}
		});
		
	}

	
	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
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
	
}
