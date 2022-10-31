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
		dealerBox.setStyle("-fx-border-color: blue;"+"-fx-border-width: 3;"+"-fx-border-insets: 5");
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
		HBox pOneNameAndWon;
		Text pOneWinnings = new Text("Total Winnings: ");
		pOneNameAndWon = new HBox(pOne, pOneWinnings);
		
		HBox pOneCards = new HBox(); // player 1 cards
		HBox pOneBets; // ante and pair
		
		Button decAnteOne = new Button("-");
		TextField anteAmountOne = new TextField();
		anteAmountOne.setDisable(true);
		Button incAnteOne = new Button("+");
		HBox pOneAnteAmt = new HBox(decAnteOne, anteAmountOne, incAnteOne);
		VBox pOneAnte = new VBox(pOneAnteAmt, ante);
		pOneAnteAmt.setSpacing(5);
		
		Button decPPOne = new Button("-");
		TextField ppAmountOne = new TextField();
		ppAmountOne.setDisable(true);
		Button incPPOne = new Button("+");
		HBox pOnePPAmt = new HBox(decPPOne, ppAmountOne, incPPOne);
		VBox pOnePP = new VBox(pOnePPAmt, pp);
		pOneBets = new HBox(pOneAnte, pOnePP);
		pOnePPAmt.setSpacing(5);
		
		playerOneBox = new VBox(pOneNameAndWon, pOneCards, pOneBets);
		
		// Middle Buttons
		Button deal = new Button("Deal");
		Button rebet = new Button("Rebet");
		VBox midButtons = new VBox(deal, rebet);
		midButtons.setAlignment(Pos.BASELINE_CENTER);
		midButtons.setPadding(new Insets(0, 20, 0, 20));
		
		//  Player two's box
		VBox playerTwoBox;
		Text pTwo = new Text("Player 2");
		HBox pTwoNameAndWon;
		Text pTwoWinnings = new Text("Total Winnings: ");
		pTwoNameAndWon = new HBox(pTwo, pTwoWinnings);
		
		HBox pTwoCards = new HBox(); // player 1 cards
		HBox pTwoBets; // ante and pair
		
		Button decAnteTwo = new Button("-");
		TextField anteAmountTwo = new TextField();
		anteAmountTwo.setDisable(true);
		Button incAnteTwo = new Button("+");
		HBox pTwoAnteAmt = new HBox(decAnteTwo, anteAmountTwo, incAnteTwo);
		pTwoAnteAmt.setSpacing(5);
		VBox pTwoAnte = new VBox(pTwoAnteAmt, anteTwo);
		pTwoAnte.setSpacing(20);
		
		Button decPPTwo = new Button("-");
		TextField ppAmountTwo = new TextField();
		ppAmountTwo.setDisable(true);
		Button incPPTwo = new Button("+");
		HBox pTwoPPAmt = new HBox(decPPTwo, ppAmountTwo, incPPTwo);
		pTwoPPAmt.setSpacing(5);
		VBox pTwoPP = new VBox(pTwoPPAmt, ppTwo);
		pTwoBets = new HBox(pTwoAnte, pTwoPP);
		pTwoPP.setSpacing(20);
		
		playerTwoBox = new VBox(pTwoNameAndWon, pTwoCards, pTwoBets);
		
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

