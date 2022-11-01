//test
// edit to check if i can pull
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to Project #2");

		Menu exit = new Menu("Exit");
		Menu freshStart = new Menu("Fresh Start");
		Menu newLook = new Menu("NewLook");
		MenuBar menuBar = new MenuBar(exit, freshStart, newLook);
		

		Rectangle rect = new Rectangle (100, 40, 100, 100);
		rect.setArcHeight(50);
		rect.setArcWidth(50);
		rect.setFill(Color.VIOLET);

		RotateTransition rt = new RotateTransition(Duration.millis(5000), rect);
		rt.setByAngle(270);
		rt.setCycleCount(4);
		rt.setAutoReverse(true);
		SequentialTransition seqTransition = new SequentialTransition (
				new PauseTransition(Duration.millis(500)), rt);
		seqTransition.play();

		FadeTransition ft = new FadeTransition(Duration.millis(5000), rect);
		ft.setFromValue(1.0);
		ft.setToValue(0.3);
		ft.setCycleCount(4);
		ft.setAutoReverse(true);

		ft.play();
		BorderPane root = new BorderPane();
		root.setCenter(rect);

		Scene scene = new Scene(root, 700,700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

import java.util.Iterator;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ThreeCardPokerGame extends Application {

    Player playerOne;
    Player playerTwo;
    Dealer theDealer;
    
    public static void main(String[] args) {
    	launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	primaryStage.setTitle("Three Card Poker");
    	
    	int pOneWonAmt = 0;
    	int pTwoWonAmt = 0;
    	
    	//  Top part of UI
    	HBox topHorizontal;
    	MenuItem exit = new MenuItem("Exit");
		// close game
		exit.setOnAction( e -> {
			primaryStage.close();
		});
		MenuItem freshStart = new MenuItem("Fresh Start");
		MenuItem newLook = new MenuItem("NewLook");
		Menu menu = new Menu("Menu");
		menu.getItems().add(exit);
		menu.getItems().add(freshStart);
		menu.getItems().add(newLook);

		MenuBar menuBar = new MenuBar(menu);


		VBox dealerBox;
		Text dealerText = new Text("Dealer");
		HBox dealerCards = new HBox();
		dealerBox = new VBox(dealerText, dealerCards);
		dealerBox.setPadding(new Insets(0, 150, 0, 150));
		dealerBox.setStyle("-fx-border-color: black;"+"-fx-border-width: 3;"+"-fx-border-insets: 5");
		ListView<String> displayQueueItems = new ListView<String>();
		ObservableList<String> storeQueueItemsInListView;
		
		topHorizontal = new HBox(menuBar, dealerBox, displayQueueItems);
		topHorizontal.setSpacing(100);


		// Center part of UI
		HBox centerBox;
		
		
		Text ante = new Text("Ante");
		Text pp = new Text("Pair+");
		Text anteTwo = new Text("Ante");
		Text ppTwo = new Text("Pair+");

		//  Player one's box
		VBox playerOneBox;
		Text pOne = new Text("Player 1");
		Text pOneWinnings = new Text("Total Winnings: ");
		
		HBox pOneCards = new HBox(); // player 1 cards
		
		Button decAnteOne = new Button("-");
		int pOneAnteBet = 5;
		TextField anteAmountOne = new TextField();
		anteAmountOne.setDisable(true);
		Button incAnteOne = new Button("+");
		HBox pOneAnte = new HBox(decAnteOne, anteAmountOne, incAnteOne);
		pOneAnte.setSpacing(5);
		
		Button decPPOne = new Button("-");
		int pOnePPBet = 5;
		TextField ppAmountOne = new TextField();
		ppAmountOne.setDisable(true);
		Button incPPOne = new Button("+");
		HBox pOnePP = new HBox(decPPOne, ppAmountOne, incPPOne);
		pOnePP.setSpacing(5);
		
		playerOneBox = new VBox(pOne, pOneCards, ante, pOneAnte, pp, pOnePP, pOneWinnings);
		playerOneBox.setAlignment(Pos.TOP_CENTER);
		playerOneBox.setStyle("-fx-border-color: blue;"+"-fx-border-width: 3;"+"-fx-border-insets: 5");
		playerOneBox.setPadding(new Insets(0, 50, 0, 50));
		playerOneBox.setSpacing(10);
		
		// Middle Buttons
		Button deal = new Button("Deal");
		Button rebet = new Button("Rebet");
		VBox midButtons = new VBox(deal, rebet);
		midButtons.setAlignment(Pos.CENTER);
		midButtons.setPadding(new Insets(0, 20, 0, 20));
		midButtons.setSpacing(40);
		
		//  Player two's box
		VBox playerTwoBox;
		Text pTwo = new Text("Player 2");
		Text pTwoWinnings = new Text("Total Winnings: ");
		
		HBox pTwoCards = new HBox(); // player 1 cards
		
		Button decAnteTwo = new Button("-");
		int pTwoAnteBet = 5;
		TextField anteAmountTwo = new TextField();
		anteAmountTwo.setDisable(true);
		Button incAnteTwo = new Button("+");
		HBox pTwoAnte = new HBox(decAnteTwo, anteAmountTwo, incAnteTwo);
		pTwoAnte.setSpacing(5);
		
		Button decPPTwo = new Button("-");
		int pTwoPPBet = 5;
		TextField ppAmountTwo = new TextField();
		ppAmountTwo.setDisable(true);
		Button incPPTwo = new Button("+");
		HBox pTwoPP = new HBox(decPPTwo, ppAmountTwo, incPPTwo);
		
		pTwoPP.setSpacing(5);

		
		playerTwoBox = new VBox(pTwo, pTwoCards, anteTwo, pTwoAnte, ppTwo, pTwoPP, pTwoWinnings);
		playerTwoBox.setAlignment(Pos.TOP_CENTER);
		playerTwoBox.setStyle("-fx-border-color: blue;"+"-fx-border-width: 3;"+"-fx-border-insets: 5");
		playerTwoBox.setPadding(new Insets(0, 50, 0, 50));
		playerTwoBox.setSpacing(10);
		centerBox = new HBox(playerOneBox, midButtons, playerTwoBox);
		
//		printListBtn.setOnAction(e-> {displayQueueItems.getItems().removeAll(storeQueueItemsInListView);
//		storeQueueItemsInListView.clear();
//		Iterator<String> i = myQueue.createIterator();
//		while(i.hasNext()) { 
//			storeQueueItemsInListView.add(i.next());
//		}
//		  displayQueueItems.setItems(storeQueueItemsInListView);});
		
    	BorderPane root = new BorderPane(centerBox, topHorizontal, null, null, null);
    	Scene scene = new Scene(root, 1000, 800);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
}


