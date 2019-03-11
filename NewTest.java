package xidian.software.com.nfa;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/*
 * NFA 输入 字符
 */
public class NewTest {

	private Text text;
	private String trans;
	
	private NewPath path;
	
	
	public NewTest() {
		// TODO Auto-generated constructor stub
		text = new Text();
		text.setFont(new Font("Arial", 20));
		
		text.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(path.getPath().getStrokeWidth() == 5.0) {
					path.getPath().setStrokeWidth(1.0);
				}else {
					path.getPath().setStrokeWidth(5.0);
				}
			}
		});
		
		text.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				//  线 移动
				path.drag(event.getSceneX(), event.getSceneY());
			}
		});
	}

	/*
	 * 移动 文本
	 */
	public void move(double x, double y) {
		
		text.setLayoutX(x);
		text.setLayoutY(y);
		
	}
	



	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public String getTrans() {
		return trans;
	}


	public void setTrans(String trans) {
		this.trans = trans;
	}


	public NewPath getPath() {
		return path;
	}


	public void setPath(NewPath path) {
		this.path = path;
	}
	
	
	
	
}
