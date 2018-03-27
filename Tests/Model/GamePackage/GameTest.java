package Model.GamePackage;

import static org.junit.Assert.*;

import Model.BoardPackage.Board;
import Model.BoardPackage.OwnableSquare;
import Model.BoardPackage.PropertySquare;
import org.junit.Test;

import java.util.ArrayList;

/*****************************************************************
 * JUnit tests for the Bank Class
 *
 * @author Dylan Kernohan
 * @version 3/25/2018
 *****************************************************************/
public class GameTest {

    Game game = new Game("res/board.txt", "res/community.txt", "res/chance.txt");

  /*****************************************************************
   * Test the getBank method
   *****************************************************************/
  @Test
  public void getBankTest() throws Exception {
      PropertySquare propertySquare = new PropertySquare("GVSU", 5, 200, 400,
              100, 200, 300, 400, 500,
              600, 2, 100, 500, 2);    ArrayList<OwnableSquare> properties = new ArrayList<OwnableSquare>(){{add(propertySquare);}};
      Bank bank = new Bank(properties);

      game.setBank(bank);
      assertTrue(game.getBank() == bank);
  }

  @Test
  public void setBankTest() throws Exception {
      PropertySquare propertySquare = new PropertySquare("Allendale", 5, 200, 400,
              100, 200, 300, 400, 500,
              600, 2, 100, 500, 2);    ArrayList<OwnableSquare> properties = new ArrayList<OwnableSquare>(){{add(propertySquare);}};
      Bank bank = new Bank(properties);

      game.setBank(bank);
      assertTrue(game.getBank() == bank);
  }

  @Test
  public void getPlayersTest() throws Exception {
      assertEquals(new ArrayList<Player>(), game.getPlayers());
  }

  @Test
  public void setPlayersTest() throws Exception {
    Player player = new Player("Dylan", "Top Hat", 1500);
    ArrayList<Player> players = new ArrayList<>();
    players.add(player);
    game.setPlayers(players);
    assertEquals(players, game.getPlayers());
  }

  @Test
  public void getDieOneTest() throws Exception {
    assertTrue(game.getDieOne().value >= 0);
    assertTrue(game.getDieOne().value <= 6);

  }

  @Test
  public void setDieOneTest() throws Exception {
      Die dieOne = new Die();
      game.setDieOne(dieOne);
      assertEquals(dieOne, game.getDieOne());
  }

  @Test
  public void getDieTwoTest(){
      assertTrue(game.getDieTwo().value >= 0);
      assertTrue(game.getDieTwo().value <= 6);
  }

  @Test
  public void setDieTwoTest(){
      Die dieTwo = new Die();
      game.setDieTwo(dieTwo);
      assertEquals(dieTwo, game.getDieTwo());
  }

  @Test
  public void getBoardTest(){
      Board board = new Board("res/board.txt");
      game.setBoard(board);
      assertEquals(board, game.getBoard());
  }

  @Test
  public void setBoardTest(){
      Board board = new Board("res/board.txt");
      game.setBoard(board);
      assertEquals(board, game.getBoard());
  }

  @Test
  public void getComunityChestDeckTest(){

  }

  @Test
  public void setComunityChestDeckTest(){

  }

  @Test
  public void getChanceDeckTest(){

  }

  @Test
  public void setChanceDeckTest(){

  }

  @Test
  public void getCurrentPlayerTest(){
    assertNull(game.getCurrentPlayer());
  }

  @Test
  public void setCurrentPlayerTest(){
      Player player = new Player("Dylan", "Top Hat", 1500);
      game.setCurrentPlayer(player);
      assertEquals(player, game.getCurrentPlayer());
  }

  @Test
  public void rollDiceTest(){
    game.rollDice();
      assertTrue(game.getDieOne().value >= 0);
      assertTrue(game.getDieOne().value <= 6);
      assertTrue(game.getDieTwo().value >= 0);
      assertTrue(game.getDieTwo().value <= 6);
  }

  @Test
  public void addPlayerTest(){
      game.addPlayer("Dylan","Top Hat");
      assertEquals("Dylan", game.getPlayers().get(0).getDisplayName());
  }

  @Test
  public void removePlayerTest(){
      Player player = new Player("Dylan", "Top Hat", 1500);
      ArrayList<Player> players = new ArrayList<>();
      players.add(player);
      game.removePlayer(player);
      assertTrue(game.getPlayers().isEmpty());

  }

  @Test
  public void nextTurnTest(){
      game.addPlayer("Dylan", "Top");
      game.addPlayer("Bob", "Hat");

      game.setCurrentPlayer(game.getPlayers().get(0));
      assertEquals("Dylan", game.getCurrentPlayer().getDisplayName());
      game.nextTurn();
      assertEquals("Bob", game.getCurrentPlayer().getDisplayName());
      game.nextTurn();
      assertEquals("Dylan", game.getCurrentPlayer().getDisplayName());
  }

  @Test
  public void setPlayerOrderTest(){
      game.addPlayer("Dylan", "Top");
      game.addPlayer("Bob", "Hat");
      game.addPlayer("Santiago", "Hat");
      game.addPlayer("David", "Hat");
      game.addPlayer("Dustin", "Hat");

      game.setPlayerOrder();
      assertTrue(!game.getPlayers().get(0).getDisplayName().equals("Dylan"));

  }

  @Test
  public void buyOwnableSquareTest(){
      Player player = new Player("Dylan", "Top Hat", 1500);
      Player player1 = new Player("Dylan", "Top Hat", 100);

      ArrayList<Player> players = new ArrayList<>();
      players.add(player);
      players.add(player1);
      game.setCurrentPlayer(player);
      assertTrue(game.buyOwnableSquare(game.getBoard().getOwnableSquare(1), player, 200 ));
      game.setCurrentPlayer(player1);
      assertFalse(game.buyOwnableSquare(game.getBoard().getOwnableSquare(1), player1, 200));
  }

  @Test
  public void movePlayerTest(){
      Player player = new Player("Dylan", "Top Hat", 1500);
      ArrayList<Player> players = new ArrayList<>();
      players.add(player);
      player.setPosition(0);
      game.movePlayer(player, 1,1);
      assertEquals(2, player.getPosition());
      player.setPosition(38);
      game.movePlayer(player, 1,1);
      assertEquals(0, player.getPosition());
  }

  @Test
  public void sellOwnableSquareTest(){
      Player player = new Player("Dylan", "Top Hat", 1500);
      Player player1 = new Player("Santiago", "Top Hat", 1500);
      ArrayList<Player> players = new ArrayList<>();
      players.add(player);
      PropertySquare propertySquare = new PropertySquare("GVSU", 5, 200, 400,
              100, 200, 300, 400, 500,
              600, 2, 100, 500, 2);
      propertySquare.setOwner(player1);
      player1.getPropertiesOwned().add(propertySquare);
      assertTrue(game.sellOwnableSquare(propertySquare, player));

      player.setWallet(100);
      propertySquare.setOwner(player1);
      player1.getPropertiesOwned().add(propertySquare);
      assertFalse(game.sellOwnableSquare(propertySquare, player));

  }

  @Test
  public void mortgagePropertyTest(){
      Player player = new Player("Dylan", "Top Hat", 1500);
      ArrayList<Player> players = new ArrayList<>();
      players.add(player);
      game.getBoard().getOwnableSquare(1).setOwner(player);
      game.mortgageProperty(game.getBoard().getOwnableSquare(1));
      assertTrue(game.getBoard().getOwnableSquare(1).isMortgaged());
      assertTrue(player.getWallet() > 1500);
  }

  @Test
  public void payMortgageTest(){
      Player player = new Player("Dylan", "Top Hat", 1500);
      ArrayList<Player> players = new ArrayList<>();
      players.add(player);
      game.getBoard().getOwnableSquare(1).setOwner(player);
      game.mortgageProperty(game.getBoard().getOwnableSquare(1));
      game.payMortgage(game.getBoard().getOwnableSquare(1));
      assertFalse(game.getBoard().getOwnableSquare(1).isMortgaged());
  }

  @Test
  public void drawCardTest(){

  }

  @Test
  public void useCardTest(){

  }

  @Test
  public void collectFeeTest(){

  }

  @Test
  public void rolledPairTest(){

  }

  @Test
  public void sendPlayerToJailTest(){

  }

  @Test
  public void checkIfOwnableTest(){

  }

  @Test
  public void getCurrentPlayerLocationTest() {

  }

  @Test
  public void collectFee1Test() {

  }

  @Test
  public void shouldPlayerBeTaxedTest() {

  }

}