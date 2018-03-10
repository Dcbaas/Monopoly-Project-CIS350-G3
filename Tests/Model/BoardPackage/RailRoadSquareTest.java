package Model.BoardPackage;

import org.junit.Test;

import static org.junit.Assert.*;

/*****************************************************************
 * JUnit tests for the RailRoadSquare Class
 *
 * @author Dylan Kernohan
 * @version 2/21/2018
 *****************************************************************/
public class RailRoadSquareTest {

    RailRoadSquare railRoadSquare = new RailRoadSquare("Kings Cross", 10, 200, 100,
            200, 300, 400, 400, 2);

    /*****************************************************************
     * Test the getBASE_RENT method
     *****************************************************************/
    @Test
    public void getBASE_RENTTest() {
        assertEquals(100, railRoadSquare.getBASE_RENT());
    }

    /*****************************************************************
     * Test the getTWO_RENT method
     *****************************************************************/
    @Test
    public void getTWO_RENTTest() {
        assertEquals(200, railRoadSquare.getTWO_RENT());
    }

    /*****************************************************************
     * Test the getTHREE_RENT method
     *****************************************************************/
    @Test
    public void getTHREE_RENTTest() {
        assertEquals(300, railRoadSquare.getTHREE_RENT());
    }

    /*****************************************************************
     * Test the getFOUR)RENT method
     *****************************************************************/
    @Test
    public void getFOUR_RENTTest() {
        assertEquals(400, railRoadSquare.getFOUR_RENT());
    }
}