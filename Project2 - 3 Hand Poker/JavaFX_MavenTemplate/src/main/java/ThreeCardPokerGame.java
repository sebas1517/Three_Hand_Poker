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

        // Adding card image
		Image dealerBack = new Image("BackofCard.png", 60, 100, true, true);
		ImageView dealerBackView = new ImageView(dealerBack);

		Image dealerBack2 = new Image("BackofCard.png", 60, 100, true, true);
		ImageView dealerBackView2 = new ImageView(dealerBack);

		Image dealerBack3 = new Image("BackofCard.png", 60, 100, true, true);
		ImageView dealerBackView3 = new ImageView(dealerBack);

		// dealer cards
		HBox dealerCard1 = new HBox(dealerBackView);
		HBox dealerCard2 = new HBox(dealerBackView2);
		HBox dealerCard3 = new HBox(dealerBackView3);

		HBox dealerHand = new HBox(dealerCard1, dealerCard2, dealerCard3);
	
		VBox dealerBox;
		Text dealerText = new Text("Dealer");
		HBox dealerCards = new HBox();
		dealerBox = new VBox(dealerText, dealerCards, dealerHand);
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

		// Player one's box
		VBox playerOneBox;
		Text pOne = new Text("Player 1");
		Text pOneWinnings = new Text("Total Winnings: ");
		
		HBox pOneCards = new HBox(); // player 1 cards
		
		Button decAnteOne = new Button("-");
		int pOneAnteBet = 5;
		TextField anteAmountOne = new TextField();
		anteAmountOne.setText(Integer.toString(pOneAnteBet));
		anteAmountOne.setAlignment(Pos.CENTER);
		anteAmountOne.setDisable(true);
		Button incAnteOne = new Button("+");
		HBox pOneAnte = new HBox(decAnteOne, anteAmountOne, incAnteOne);
		pOneAnte.setSpacing(5);
		
		Button decPPOne = new Button("-");
		int pOnePPBet = 5;
		TextField ppAmountOne = new TextField();
		ppAmountOne.setText(Integer.toString(pOnePPBet));
		ppAmountOne.setAlignment(Pos.CENTER);
		ppAmountOne.setDisable(true);
		Button incPPOne = new Button("+");
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
		Text pTwoWinnings = new Text("Total Winnings: ");
		
		HBox pTwoCards = new HBox(); // player 1 cards
		
		Button decAnteTwo = new Button("-");
		int pTwoAnteBet = 5;
		TextField anteAmountTwo = new TextField();
		anteAmountTwo.setText(Integer.toString(pTwoAnteBet));
		anteAmountTwo.setAlignment(Pos.CENTER);
		anteAmountTwo.setDisable(true);
		Button incAnteTwo = new Button("+");
		HBox pTwoAnte = new HBox(decAnteTwo, anteAmountTwo, incAnteTwo);
		pTwoAnte.setSpacing(5);
		
		Button decPPTwo = new Button("-");
		int pTwoPPBet = 5;
		TextField ppAmountTwo = new TextField();
		ppAmountTwo.setText(Integer.toString(pTwoPPBet));
		ppAmountTwo.setAlignment(Pos.CENTER);
		ppAmountTwo.setDisable(true);
		Button incPPTwo = new Button("+");
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

