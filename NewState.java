package xidian.software.com.nfa;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NewState {
	
	private Text text;
	private String state;
	
	private NewCircle circle;
	
	private double OldX;
	private double OldY;
	
	
	public NewState() {
		// TODO Auto-generated constructor stub
		text = new Text();
		text.setFont(new Font("Arial", 20));
		
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
				// TODO Auto-generated method stub
				// 移动 自身
				NewState.this.drag(event.getX() - OldX, event.getY() - OldY);
				//  移动  圆
				circle.drag(event.getX() - OldX, event.getY() - OldY);
				
				// 移动 线
				for(int i = 0; i < circle.getFrom().size(); i ++) {
					NewPath path = circle.getFrom().get(i);
					
					//  移动 线
					path.circleDrag(event.getX() - OldX, event.getY() - OldY, "start");

								
					
				}
				for(int i = 0; i < circle.getTo().size(); i ++) {
					NewPath path = circle.getTo().get(i);
					
					path.circleDrag(event.getX() - OldX, event.getY() - OldY, "end");
					
				}
				
			}
		});
		
	}

	
	public void drag(double dragX, double dragY) {
		text.setLayoutX(dragX + text.getLayoutX());
		text.setLayoutY(dragY + text.getLayoutY());
	}
	



	public Text getText() {
		return text;
	}


	public void setText(Text text) {
		this.text = text;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public NewCircle getCircle() {
		return circle;
	}


	public void setCircle(NewCircle circle) {
		this.circle = circle;
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
