package xidian.software.com.nfa;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;

public class NewTextLine {

	private Line line;
	
	private Label symbol;
	
	private NewCircle fromCircle;
	private NewCircle toCircle;
	
	private Line lLine;
	private Line rLine;
	
	public NewTextLine() {
		// TODO Auto-generated constructor stub
		
		line = new Line();
		lLine = new Line();
		rLine = new Line();
		
	}

	public Line getlLine() {
		return lLine;
	}

	public void setlLine(Line lLine) {
		this.lLine = lLine;
	}

	public Line getrLine() {
		return rLine;
	}

	public void setrLine(Line rLine) {
		this.rLine = rLine;
	}


	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public Label getSymbol() {
		return symbol;
	}
	public void setSymbol(Label symbol) {
		this.symbol = symbol;
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
	
	
	
}
