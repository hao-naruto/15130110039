package xidian.software.com.nfa;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/*
 * NFA 输入 字符
 */
public class NewText {

	private Text text;
	private String trans;
	
	private NewPath path;
	
	private double fontSize = 20.0;
	private String fontType = "Arial";
	
	public NewText() {
		// TODO Auto-generated constructor stub
		text = new Text();
		text.setFont(new Font(fontType, fontSize));
		
		text.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(path.getPath().getStrokeWidth() == path.getStrokeWidth()) {
					path.getPath().setStrokeWidth(1.0);
				}else {
					path.getPath().setStrokeWidth(path.getStrokeWidth());
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
		text.setLayoutY(y - fontSize/4);
		
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

	public double getFontSize() {
		return fontSize;
	}

	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontType() {
		return fontType;
	}

	public void setFontType(String fontType) {
		this.fontType = fontType;
	}
	
	
	
	
	
}
