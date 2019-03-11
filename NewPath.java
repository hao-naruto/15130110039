package xidian.software.com.nfa;

import javafx.scene.control.Label;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;

public class NewPath {
	
	private String TYPE = "LINE";
	
	private Path path;
	private MoveTo moveTo;
	
	private QuadCurveTo quadCurveTo;
	
	private LineTo lineTo1;
	private LineTo lineTo2;
	private LineTo lineTo3;
	private LineTo lineTo4;
	private LineTo lineTo5;
	
	private Label symbol;
	
	private NewCircle fromCircle;
	private NewCircle toCircle;
	
	
	public NewPath() {
		// TODO Auto-generated constructor stub
		path = new Path();
		moveTo = new MoveTo();
		lineTo1 = new LineTo();
		lineTo2 = new LineTo();
		lineTo3 = new LineTo();
		lineTo4 = new LineTo();
		lineTo5 = new LineTo();
		
		quadCurveTo = new QuadCurveTo();
		
	}


	
	
	
	public QuadCurveTo getQuadCurveTo() {
		return quadCurveTo;
	}





	public void setQuadCurveTo(QuadCurveTo quadCurveTo) {
		this.quadCurveTo = quadCurveTo;
	}





	public String getTYPE() {
		return TYPE;
	}





	public void setTYPE(String tYPE) {
		TYPE = tYPE;
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


	public LineTo getLineTo1() {
		return lineTo1;
	}


	public void setLineTo1(LineTo lineTo1) {
		this.lineTo1 = lineTo1;
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
	
	
	

}
