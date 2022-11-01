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
		Menu menu = new Menu("Options");
		menu.getItems().add(exit);
		menu.getItems().add(freshStart);
		menu.getItems().add(newLook);

		MenuBar menuBar = new MenuBar(menu);

        // Adding card image
		Image dealerCardBack1 = new Image("BackofCard.png", 100, 160, true, true);
		Image dealerCardBack2 = new Image("BackofCard.png", 100, 160, true, true);
		Image dealerCardBack3 = new Image("BackofCard.png", 100, 160, true, true);
		
		ImageView[] dealerCardViews = {new ImageView(dealerCardBack1),
				new ImageView(dealerCardBack2), new ImageView(dealerCardBack3)};
		Image[] dealerCards = {null, null, null};

		// dealer cards
		HBox dealerHand = new HBox(dealerCardViews[0], dealerCardViews[1], dealerCardViews[2]);
		dealerHand.setSpacing(5);
		
		VBox dealerBox;
		Text dealerText = new Text("Dealer");
		dealerBox = new VBox(dealerText, dealerHand);
		dealerBox.setPadding(new Insets(0, 20, 0, 20));
		dealerBox.setStyle("-fx-border-color: black;"+"-fx-border-width: 3;"+"-fx-border-insets: 5");
		ListView<String> displayQueueItems = new ListView<String>();
		ObservableList<String> storeQueueItemsInListView;
		
		topHorizontal = new HBox(menuBar, dealerBox, displayQueueItems);
		topHorizontal.setSpacing(155);


		// Center part of UI
		HBox centerBox;
		
		
		Text ante = new Text("Ante");
		Text pp = new Text("Pair+");
		Text ante2 = new Text("Ante");
		Text pp2 = new Text("Pair+");

		// Player one's box
		VBox player1Box;
		Text p1 = new Text("Player 1");
		Text p1Winnings = new Text("Total Winnings: " + playerOne.totalWinnings);
		
		Button decAnte1 = new Button("-");
		TextField anteAmount1 = new TextField();
		anteAmount1.setText(Integer.toString(playerOne.anteBet));
		decAnte1.setOnAction(e -> {
			playerOne.anteBet--;
			if (playerOne.anteBet < 5) {
				playerOne.anteBet = 5;
			}
			anteAmount1.setText(Integer.toString(playerOne.anteBet));
		});
		anteAmount1.setAlignment(Pos.CENTER);
		anteAmount1.setDisable(true);
		Button incAnte1 = new Button("+");
		incAnte1.setOnAction(e -> {
			playerOne.anteBet++;
			if (playerOne.anteBet > 25) {
				playerOne.anteBet = 25;
			}
			anteAmount1.setText(Integer.toString(playerOne.anteBet));
		});
		HBox p1Ante = new HBox(decAnte1, anteAmount1, incAnte1);
		p1Ante.setAlignment(Pos.CENTER);
		p1Ante.setSpacing(5);

		TextField ppAmount1 = new TextField();
		ppAmount1.setText(Integer.toString(playerOne.pairPlusBet));
		Button decPP1 = new Button("-");
		decPP1.setOnAction(e -> {
			playerOne.pairPlusBet--;
			if (playerOne.pairPlusBet < 5) {
				playerOne.pairPlusBet = 5;
			}
			ppAmount1.setText(Integer.toString(playerOne.pairPlusBet));
		});
		ppAmount1.setAlignment(Pos.CENTER);
		ppAmount1.setDisable(true);
		Button incPP1 = new Button("+");
		incPP1.setOnAction(e -> {
			playerOne.pairPlusBet++;
			if (playerOne.pairPlusBet > 25) {
				playerOne.pairPlusBet = 25;
			}
			ppAmount1.setText(Integer.toString(playerOne.pairPlusBet));
		});
		HBox p1PP = new HBox(decPP1, ppAmount1, incPP1);
		p1PP.setAlignment(Pos.CENTER);
		p1PP.setSpacing(5);
		
		Button p1Play = new Button("PLAY");
		Button p1Fold = new Button("FOLD");
		HBox p1PF = new HBox(p1Play, p1Fold);
		p1PF.setAlignment(Pos.CENTER);
		p1PF.setSpacing(30);
		
		// Card Back
		Image p1CardBack1 = new Image("BackofCard.png", 100, 160, true, true);
		Image p1CardBack2 = new Image("BackofCard.png", 100, 160, true, true);
		Image p1CardBack3 = new Image("BackofCard.png", 100, 160, true, true);
		
		ImageView[] p1CardViews = {new ImageView(p1CardBack1), new ImageView(p1CardBack2), new ImageView(p1CardBack3)};
		Image[] p1Cards = {null, null, null};


		HBox p1Hand = new HBox(p1CardViews[0], p1CardViews[1], p1CardViews[2]);
		p1Hand.setSpacing(5);
		player1Box = new VBox(p1, p1Hand, ante, p1Ante, pp, p1PP, p1PF, p1Winnings);
		player1Box.setAlignment(Pos.TOP_CENTER);
		player1Box.setStyle("-fx-border-color: blue;"+"-fx-border-width: 3;"+"-fx-border-insets: 5");
		player1Box.setPadding(new Insets(0, 50, 0, 50));
		player1Box.setSpacing(10);
		// Player 1 ends
		
		// Middle Buttons
		Button deal = new Button("Deal");
		Button rebet = new Button("Rebet");
		VBox midButtons = new VBox(deal, rebet);
		midButtons.setAlignment(Pos.CENTER);
		midButtons.setPadding(new Insets(0, 20, 0, 20));
		midButtons.setSpacing(40);
		
		//  Player two's box
		VBox player2Box;
		Text p2 = new Text("Player 2");
		Text p2Winnings = new Text("Total Winnings: " + playerTwo.totalWinnings);
		
		Button decAnte2 = new Button("-");
		TextField anteAmount2 = new TextField();
		anteAmount2.setText(Integer.toString(playerTwo.anteBet));
		decAnte2.setOnAction(e -> {
			playerTwo.anteBet--;
			if (playerTwo.anteBet < 5) {
				playerTwo.anteBet = 5;
			}
			anteAmount2.setText(Integer.toString(playerTwo.anteBet));
		});
		anteAmount2.setAlignment(Pos.CENTER);
		anteAmount2.setDisable(true);
		Button incAnte2 = new Button("+");
		incAnte2.setOnAction(e -> {
			playerTwo.anteBet++;
			if (playerTwo.anteBet > 25) {
				playerTwo.anteBet = 25;
			}
			anteAmount2.setText(Integer.toString(playerTwo.anteBet));
		});
		HBox p2Ante = new HBox(decAnte2, anteAmount2, incAnte2);
		p2Ante.setAlignment(Pos.CENTER);
		p2Ante.setSpacing(5);

		TextField ppAmount2 = new TextField();
		ppAmount2.setText(Integer.toString(playerTwo.pairPlusBet));
		Button decPP2 = new Button("-");
		decPP2.setOnAction(e -> {
			playerTwo.pairPlusBet--;
			if (playerTwo.pairPlusBet < 5) {
				playerTwo.pairPlusBet = 5;
			}
			ppAmount2.setText(Integer.toString(playerTwo.pairPlusBet));
		});
		ppAmount2.setAlignment(Pos.CENTER);
		ppAmount2.setDisable(true);
		Button incPP2 = new Button("+");
		incPP2.setOnAction(e -> {
			playerTwo.pairPlusBet++;
			if (playerTwo.pairPlusBet > 25) {
				playerTwo.pairPlusBet = 25;
			}
			ppAmount2.setText(Integer.toString(playerTwo.pairPlusBet));
		});
		HBox p2PP = new HBox(decPP2, ppAmount2, incPP2);
		p2PP.setAlignment(Pos.CENTER);
		Button p2Play = new Button("PLAY");
		Button p2Fold = new Button("FOLD");
		HBox p2PF = new HBox(p2Play, p2Fold);
		p2PF.setSpacing(30);
		p2PF.setAlignment(Pos.CENTER);

		// Card Back
		Image p2CardBack1 = new Image("BackofCard.png", 100, 160, true, true);

		Image p2CardBack2 = new Image("BackofCard.png", 100, 160, true, true);

		Image p2CardBack3 = new Image("BackofCard.png", 100, 160, true, true);
		ImageView[] p2CardViews = {new ImageView(p2CardBack1), new ImageView(p2CardBack2), new ImageView(p2CardBack3)};
		// Adding card image
		Image[] p2Cards = {null, null, null};

		HBox p2Hand = new HBox(p2CardViews[0], p2CardViews[1], p2CardViews[2]);
		p2Hand.setSpacing(5);
		p2PP.setSpacing(5);
		
		player2Box = new VBox(p2, p2Hand, ante2, p2Ante, pp2, p2PP, p2PF, p2Winnings);
		player2Box.setAlignment(Pos.TOP_CENTER);
		player2Box.setStyle("-fx-border-color: blue;"+"-fx-border-width: 3;"+"-fx-border-insets: 5");
		player2Box.setPadding(new Insets(0, 50, 0, 50));
		player2Box.setSpacing(10);
		// Player 2 ends
		
		deal.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				p1Play.setDisable(false);
				p2Play.setDisable(false);
				p1Fold.setDisable(false);
				p2Fold.setDisable(false);
				deal.setDisable(true);
				decAnte1.setDisable(true);
				incAnte1.setDisable(true);
				decPP1.setDisable(true);
				incPP1.setDisable(true);
				decAnte2.setDisable(true);
				incAnte2.setDisable(true);
				decPP2.setDisable(true);
				incPP2.setDisable(true);
				playerOne.hand = theDealer.dealHand();
				for (int i = 0; i < 3; i++) {
					Card curr = playerOne.hand.get(i);
					String s = String.format("%d_of_%c.png", curr.value, curr.suit);
					p1Cards[i] = new Image(s, 100, 160, true, true);
					p1CardViews[i].setImage(p1Cards[i]);
				}
				playerTwo.hand = theDealer.dealHand();
				for (int i = 0; i < 3; i++) {
					Card curr = playerTwo.hand.get(i);
					String s = String.format("%d_of_%c.png", curr.value, curr.suit);
					p2Cards[i] = new Image(s, 100, 160, true, true);
					p2CardViews[i].setImage(p2Cards[i]);
				}
				theDealer.dealersHand = theDealer.dealHand();
//				for (int i = 0; i < 3; i++) {
//					Card curr = theDealer.dealersHand.get(i);
//					String s = String.format("%d_of_%c.png", curr.value, curr.suit);
//					dealerCards[i] = new Image(s, 60, 100, true, true);
//					dealerCardViews[i].setImage(dealerCards[i]);
//				}
			}
		});
		
		p1Play.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				playerOne.playBet = playerOne.anteBet;
				
			}
		});
		
		p2Play.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				playerTwo.playBet = playerTwo.anteBet;
			}
		});
		
		p1Fold.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				playerOne.totalWinnings -= playerOne.anteBet + playerOne.pairPlusBet;
			}
		});
		
		p2Fold.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				playerTwo.totalWinnings -= playerTwo.anteBet + playerTwo.pairPlusBet;
			}
		});
		
		
		centerBox = new HBox(player1Box, midButtons, player2Box);
		// Center ends
		
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

