package xidian.software.com.nfa;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class NewTest {

	private Label label;
	private String trans;
	
	private NewPath path;
	
	
	public NewTest() {
		// TODO Auto-generated constructor stub
		label = new Label();
		label.setFont(new Font("Arial", 20));
	}


	public Label getLabel() {
		return label;
	}


	public void setLabel(Label label) {
		this.label = label;
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
