import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.*;

public class ConcordanceGUI extends Application  {

	// contain the potential file to read from and export 
	File inputFile, 
	selectedOutputFile;  	

	// header
	String [] selectionLabels = {"Create Concordance from File", 
	"Create Concordance from Text"};  

	// array of radio buttons
	RadioButton[] radioButtons;   

	// buttons to control input
	Button createConcordanceButton;  
	Button selectInput;  			
	Button selectOutput;  
	Button clearButt;    
	Button exitButt;        

	// creates box to hold user input
	TextArea displayTextArea;    

	// object of datamanager class
	ConcordanceDataManager mgr = new ConcordanceDataManager(); 


	public void start(Stage stage) {

		
		// name the buttons
		createConcordanceButton = new Button("Create Concordance");		
		
		selectInput = new Button("select Input File");
		
		selectOutput = new Button("select Output File");	
		
		clearButt = new Button("clear");		
		
		exitButt = new Button("exit");	


		//events occur once hit the buttons 
		createConcordanceButton.setOnAction(new CreateConcordanceEventHandler());
		
		selectInput.setOnAction(new SelectInputEventHandler());
		
		selectOutput.setOnAction(new SelectOutputEventHandler());
		
		clearButt.setOnAction(new ClearEventHandler());
		
		exitButt.setOnAction(new ExitEventHandler());


		// text fields 
		displayTextArea = new TextArea();
		
		displayTextArea.setPrefWidth(750);
		
		displayTextArea.setPrefHeight(300);
		
		displayTextArea.setPadding(new Insets(10,10,10,10));
		
		displayTextArea.setDisable(true);


		 
		HBox concordanceRadioBox = new HBox(20);
		concordanceRadioBox.setAlignment(Pos.CENTER_LEFT);
		 
		ToggleGroup radiosGroup = new ToggleGroup();
		radioButtons = new RadioButton[selectionLabels.length];
 
		for( int i = 0; i < radioButtons.length; i++) 
		{
			radioButtons[i] = new RadioButton(selectionLabels[i]);
			
			radioButtons[i].setToggleGroup(radiosGroup);
			
			radioButtons[i].setOnAction(e-> ButtonClicked(e));
			
		}

		// Add the radio buttons to the children of the employeeRadioBox horizontal box
		concordanceRadioBox.getChildren().addAll(radioButtons);

	
		TitledPane radioBoxPane = new TitledPane("Create Concordance from:",concordanceRadioBox  );
		radioBoxPane.setCollapsible(false);


		TitledPane displayTextPane = new TitledPane("enter text: ",displayTextArea  );
		displayTextPane.setCollapsible(false);

 
		StackPane stack = new StackPane();
		stack.getChildren().addAll(displayTextPane);


		// box that organizes buttons side by side 
		HBox buttonPane = new HBox();
		
		buttonPane.setAlignment(Pos.CENTER);
		
		buttonPane.setPadding(new Insets(0,6,0,6));
		
		buttonPane.getChildren().addAll(createConcordanceButton,selectInput,selectOutput,clearButt,exitButt);		


		// box that aligns the radio  buttons on the top and input area
		VBox contentPane = new VBox();
		
		contentPane.setAlignment(Pos.CENTER);
		
		contentPane.getChildren().addAll(radioBoxPane,displayTextPane,buttonPane);


		// borderpane to center gui and contain Hbox and Vbox's
		BorderPane displayPane = new BorderPane();
		
		displayPane.setCenter(contentPane);


		// displays onto scene and sets title
		Scene scene = new Scene(displayPane);
		
		stage.setTitle("concordance creator");
		
		stage.setScene(scene);
		
		stage.show();	
	}

	// handler for making buttons visible  
	public void ButtonClicked(ActionEvent e) {

		// if create from file is clicked then disable the oposing button until an input and output file
		// is chosen
		if (e.getSource() == radioButtons[0]) {

			displayTextArea.setVisible(false);
			
			selectInput.setDisable(false);
			
			selectOutput.setDisable(false);
			
			displayTextArea.setDisable(true);
			
		
			createConcordanceButton.setDisable(true);
			
		}

		else if (e.getSource() == radioButtons[1]) {	
			
			selectInput.setDisable(true);
			
			selectOutput.setDisable(true);
			
			displayTextArea.setVisible(true);
			
			createConcordanceButton.setDisable(false);
			
			displayTextArea.setDisable(false);
		}
	}

	
	// Performs the concordance on file or text inputted by user 
	class CreateConcordanceEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {

			String selectedPosition = null;

			//check which button was selected and make concordance 
			for(RadioButton rb : radioButtons) {
				if(rb.isSelected()) {
					selectedPosition = rb.getText();
				}	
			}
			
			
			// create concordance file is selected read from the file and make concordance
			if(selectedPosition.equals("Create Concordance from File")) {
				// if no file, throw exception
				try {
					
					// call method from condordance data manager 
					mgr.createConcordanceFile(inputFile, selectedOutputFile);				
				}
				catch (FileNotFoundException e) {
					
					e.printStackTrace();
					
				}	
			}

			
			// if create concordance from text is pressed then make concordance from text area box
			else if(selectedPosition.equals("Create Concordance from Text")) {

				ArrayList<String> data = new ArrayList<String>();
				
				String textData = displayTextArea.getText();
				
				String concordanceText = "";
				
				// Call concordance data manager method createconcordancearray  
				data = mgr.createConcordanceArray(textData);

				for( int i = 0; i < data.size(); i++)
				{
					
					concordanceText += data.get(i);	
					
				}
				
				displayTextArea.setText(concordanceText);
			}
		}
	}

	// prompt user to input file name to export concordance 
	class SelectInputEventHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {			
			// Will display a window box allowing the user to select a file from their computer to open, in order to read its data. 
			JFileChooser fileChooser = new JFileChooser();
			
			int status = fileChooser.showOpenDialog(null);

			if (status == JFileChooser.APPROVE_OPTION) 
			{
				
				inputFile = fileChooser.getSelectedFile();
				
			}	

			// when output file is selected enable create concordance
			if(selectedOutputFile != null) {
				createConcordanceButton.setDisable(false);
			}
		}
	}
	
	// prompts user to select output file with concordance arranged words
	class SelectOutputEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {

			// displays window to select file to send information to
			JFileChooser fileChooser = new JFileChooser();
			
			int status = fileChooser.showOpenDialog(null);

			if (status == JFileChooser.APPROVE_OPTION) 
			{
				
				selectedOutputFile = fileChooser.getSelectedFile();
				
			}

			// input file is selected so enable the create concordance
			if(inputFile != null) 
			{
				
				createConcordanceButton.setDisable(false);
				
			}
		}
	}

	// erases within text box
	class ClearEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			displayTextArea.clear();
			
		}
	}

	// leaves application
	class ExitEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			System.exit(0);		
			
		}
	}

	// Starts gui 
	public static void main(String[] args) {
		
		launch(args);
		
	}
}
