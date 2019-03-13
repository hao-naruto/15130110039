package xidian.software.com.javaFX;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import xidian.software.com.nfa.NFA;

public class MyController implements Initializable {
	
	@FXML
	private Button myBotton;
	
	@FXML
	private TextField myTextField;
	
	@FXML
	private Pane pane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void toNfa(ActionEvent event) {
		
		
		
		
		
		String textString = myTextField.getText();
		
		System.out.println(textString);
		
		NFA simpleNFA = NFA.simpleNFA("B", "A", "a");
		
		
		
		//  将NFA中的元素 添加 到 PANE 上
		
		// 加 线
		for(int i = 0; i < simpleNFA.getPathList().size(); i++) {
			
			if(!"TO_OTHER".equals(simpleNFA.getPathList().get(i).getTYPE())) {
				continue;
			}
			pane.getChildren().add(simpleNFA.getPathList().get(i).getPath());
			
			pane.getChildren().add(simpleNFA.getPathList().get(i).getText().getText());
			
		}	
		// 加  圆
		for(int i = 0; i < simpleNFA.getCircleList().size(); i++) {
			
			pane.getChildren().add(simpleNFA.getCircleList().get(i).getCircle());
			
			if("END".equals(simpleNFA.getCircleList().get(i).getTYPE())) {
				pane.getChildren().add(simpleNFA.getCircleList().get(i).getInCircle());
			}else if("START".equals(simpleNFA.getCircleList().get(i).getTYPE())) {
				pane.getChildren().add(simpleNFA.getCircleList().get(i).getPolygon());
			}
			
			pane.getChildren().add(simpleNFA.getCircleList().get(i).getNewState().getText());
			
		}
		
		for(int i = 0; i < simpleNFA.getPathList().size(); i++) {
			
			if(!"TO_OTHER".equals(simpleNFA.getPathList().get(i).getTYPE())) {
				
				pane.getChildren().add(simpleNFA.getPathList().get(i).getPath());
				
				pane.getChildren().add(simpleNFA.getPathList().get(i).getText().getText());
				
			}
			
		}	

		
	}
	
	

}
