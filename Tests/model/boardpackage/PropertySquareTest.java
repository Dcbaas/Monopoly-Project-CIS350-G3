package model.boardpackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/*****************************************************************
 * JUnit tests for the PropertySquare Class
 *
 * @author Dylan Kernohan
 * @version 2/21/2018
 *****************************************************************/
public class PropertySquareTest {

  PropertySquare propertySquare = new PropertySquare("GVSU", 5, 200, 400,
      100, 200, 300, 400, 500,
      600, 2, 100, 500, 2);

  /*****************************************************************
   * Test the getBaseRent method
   *****************************************************************/
  @Test
  public void getBaseRentTest() {
    assertEquals(100, propertySquare.getBaseRent());
  }

  /*****************************************************************
   * Test the getOneHouseRent method
   *****************************************************************/
  @Test
  public void getOneHouseRentTest() {
    assertEquals(200, propertySquare.getOneHouseRent());
  }

  /*****************************************************************
   * Test the getTwoHouseRent method
   *****************************************************************/
  @Test
  public void getTwoHouseRentTest() {
    assertEquals(300, propertySquare.getTwoHouseRent());
  }

  /*****************************************************************
   * Test the getThreeHouseRent method
   *****************************************************************/
  @Test
  public void getThreeHouseRentTest() {
    assertEquals(400, propertySquare.getThreeHouseRent());
  }

  /*****************************************************************
   * Test the getFOUR_HOUSE_RENT method
   *****************************************************************/
  @Test
  public void getFOUR_HOUSE_RENTTest() {
    assertEquals(500, propertySquare.getFOUR_HOUSE_RENT());
  }

  /*****************************************************************
   * Test the getHotelRent method
   *****************************************************************/
  @Test
  public void getHotelRentTest() {
    assertEquals(600, propertySquare.getHotelRent());
  }

  /*****************************************************************
   * Test the getCurrentRent method
   *****************************************************************/
  @Test
  public void getCurrentRentTest() {
    assertEquals(300, propertySquare.getCurrentRent());

  }

  /*****************************************************************
   * Test the getNumHoused method
   *****************************************************************/
  @Test
  public void getNumHousesTest() {
    assertEquals(2, propertySquare.getNumHouses());

  }

  /*****************************************************************
   * Test the setNumHouses method
   *****************************************************************/
  @Test
  public void setNumHousesTest() {
    propertySquare.setNumHouses(0);
    assertEquals(0, propertySquare.getNumHouses());

  }

  /*****************************************************************
   * Test the isHasHotel method
   *****************************************************************/
  @Test
  public void isHasHotelTest() {
    assertFalse(propertySquare.isHasHotel());
  }

  /*****************************************************************
   * Test the setHasHotel method
   *****************************************************************/
  @Test
  public void setHasHotelTest() {
    propertySquare.setHasHotel(true);
    assertTrue(propertySquare.isHasHotel());
  }

  /*****************************************************************
   * Test the getHouseCost method
   *****************************************************************/
  @Test
  public void getHouseCostTest() {
    assertEquals(100, propertySquare.getHouseCost());
  }

  /*****************************************************************
   * Test the getHotelCost method
   *****************************************************************/
  @Test
  public void getHotelCostTest() {
    assertEquals(500, propertySquare.getHotelCost());
  }
}