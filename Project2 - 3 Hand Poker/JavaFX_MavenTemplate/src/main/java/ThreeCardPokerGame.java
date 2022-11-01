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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ThreeCardPokerGame extends Application {

    Player playerOne;
    Player playerTwo;
    Dealer theDealer;

    public static void main(String[] args) {
    	launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
		playerOne = new Player();
		playerTwo = new Player();
		theDealer = new Dealer();
    	primaryStage.setTitle("Three Card Poker");
    	
    	playerOne.totalWinnings = 0;
    	playerTwo.totalWinnings = 0;
    	
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

        // Adding card image
		Image dealerBack = new Image("BackofCard.png", 100, 160, true, true);
		ImageView dealerBackView = new ImageView(dealerBack);

		Image dealerBack2 = new Image("BackofCard.png", 100, 160, true, true);
		ImageView dealerBackView2 = new ImageView(dealerBack);

		Image dealerBack3 = new Image("BackofCard.png", 100, 160, true, true);
		ImageView dealerBackView3 = new ImageView(dealerBack);

		// dealer cards
		HBox dealerHand = new HBox(dealerBackView, dealerBackView2, dealerBackView3);
	
		VBox dealerBox;
		Text dealerText = new Text("Dealer");
		HBox dealerCards = new HBox();
		dealerBox = new VBox(dealerText, dealerCards, dealerHand);
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

		// Player one's box
		VBox playerOneBox;
		Text pOne = new Text("Player 1");
		Text pOneWinnings = new Text("Total Winnings: " + playerOne.totalWinnings);
		
		HBox pOneCards = new HBox(); // player 1 cards
		
		Button decAnteOne = new Button("-");
		TextField anteAmountOne = new TextField();
		anteAmountOne.setText(Integer.toString(playerOne.anteBet));
		decAnteOne.setOnAction(e -> {
			playerOne.anteBet--;
			if (playerOne.anteBet < 5) {
				playerOne.anteBet = 5;
			}
			anteAmountOne.setText(Integer.toString(playerOne.anteBet));
		});
		anteAmountOne.setAlignment(Pos.CENTER);
		anteAmountOne.setDisable(true);
		Button incAnteOne = new Button("+");
		incAnteOne.setOnAction(e -> {
			playerOne.anteBet++;
			if (playerOne.anteBet > 25) {
				playerOne.anteBet = 25;
			}
			anteAmountOne.setText(Integer.toString(playerOne.anteBet));
		});
		HBox pOneAnte = new HBox(decAnteOne, anteAmountOne, incAnteOne);
		pOneAnte.setSpacing(5);

		TextField ppAmountOne = new TextField();
		ppAmountOne.setText(Integer.toString(playerOne.pairPlusBet));
		Button decPPOne = new Button("-");
		decPPOne.setOnAction(e -> {
			playerOne.pairPlusBet--;
			if (playerOne.pairPlusBet < 5) {
				playerOne.pairPlusBet = 5;
			}
			ppAmountOne.setText(Integer.toString(playerOne.pairPlusBet));
		});
		ppAmountOne.setAlignment(Pos.CENTER);
		ppAmountOne.setDisable(true);
		Button incPPOne = new Button("+");
		incPPOne.setOnAction(e -> {
			playerOne.pairPlusBet++;
			if (playerOne.pairPlusBet > 25) {
				playerOne.pairPlusBet = 25;
			}
			ppAmountOne.setText(Integer.toString(playerOne.pairPlusBet));
		});
		HBox pOnePP = new HBox(decPPOne, ppAmountOne, incPPOne);
		pOnePP.setSpacing(5);

		// Adding card image
		Image p1cardBack = new Image("BackofCard.png", 60, 100, true, true);
		ImageView p1cardBackView = new ImageView(p1cardBack);

		Image p1cardBack2 = new Image("BackofCard.png", 60, 100, true, true);
		ImageView p1cardBackView2 = new ImageView(p1cardBack);

		Image p1cardBack3 = new Image("BackofCard.png", 60, 100, true, true);
		ImageView p1cardBackView3 = new ImageView(p1cardBack);

		// Player one cards
//		HBox p1Card1 = new HBox(p1cardBackView);
//		HBox p1Card2 = new HBox(p1cardBackView2);
//		HBox p1Card3 = new HBox(p1cardBackView3);

		HBox p1Hand = new HBox(p1cardBackView, p1cardBackView2, p1cardBackView3);
		playerOneBox = new VBox(pOne, p1Hand, ante, pOneAnte, pp, pOnePP, pOneWinnings);
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
		Text pTwoWinnings = new Text("Total Winnings: " + playerTwo.totalWinnings);
		
		HBox pTwoCards = new HBox(); // player 1 cards
		
		Button decAnteTwo = new Button("-");
		TextField anteAmountTwo = new TextField();
		anteAmountTwo.setText(Integer.toString(playerTwo.anteBet));
		decAnteTwo.setOnAction(e -> {
			playerTwo.anteBet--;
			if (playerTwo.anteBet < 5) {
				playerTwo.anteBet = 5;
			}
			anteAmountTwo.setText(Integer.toString(playerTwo.anteBet));
		});
		anteAmountTwo.setAlignment(Pos.CENTER);
		anteAmountTwo.setDisable(true);
		Button incAnteTwo = new Button("+");
		incAnteTwo.setOnAction(e -> {
			playerTwo.anteBet++;
			if (playerTwo.anteBet > 25) {
				playerTwo.anteBet = 25;
			}
			anteAmountTwo.setText(Integer.toString(playerTwo.anteBet));
		});
		HBox pTwoAnte = new HBox(decAnteTwo, anteAmountTwo, incAnteTwo);
		pTwoAnte.setSpacing(5);

		TextField ppAmountTwo = new TextField();
		ppAmountTwo.setText(Integer.toString(playerTwo.pairPlusBet));
		Button decPPTwo = new Button("-");
		decPPTwo.setOnAction(e -> {
			playerTwo.pairPlusBet--;
			if (playerTwo.pairPlusBet < 5) {
				playerTwo.pairPlusBet = 5;
			}
			ppAmountTwo.setText(Integer.toString(playerTwo.pairPlusBet));
		});
		ppAmountTwo.setAlignment(Pos.CENTER);
		ppAmountTwo.setDisable(true);
		Button incPPTwo = new Button("+");
		incPPTwo.setOnAction(e -> {
			playerTwo.pairPlusBet++;
			if (playerTwo.pairPlusBet > 25) {
				playerTwo.pairPlusBet = 25;
			}
			ppAmountTwo.setText(Integer.toString(playerTwo.pairPlusBet));
		});
		HBox pTwoPP = new HBox(decPPTwo, ppAmountTwo, incPPTwo);

		// Adding card image
		Image p2cardBack = new Image("BackofCard.png", 60, 100, true, true);
		ImageView p2cardBackView = new ImageView(p2cardBack);

		Image p2cardBack2 = new Image("BackofCard.png", 60, 100, true, true);
		ImageView p2cardBackView2 = new ImageView(p2cardBack);

		Image p2cardBack3 = new Image("BackofCard.png", 60, 100, true, true);
		ImageView p2cardBackView3 = new ImageView(p2cardBack);

		// Player two cards
//		HBox p2Card1 = new HBox(p2cardBackView);
//		HBox p2Card2 = new HBox(p2cardBackView2);
//		HBox p2Card3 = new HBox(p2cardBackView3);

		HBox p2Hand = new HBox(p2cardBackView, p2cardBackView2, p2cardBackView3);
		
		pTwoPP.setSpacing(5);
		playerTwoBox = new VBox(pTwo, p2Hand, anteTwo, pTwoAnte, ppTwo, pTwoPP, pTwoWinnings);
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

