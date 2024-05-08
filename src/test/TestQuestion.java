import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.*;


public class TestingQuestion{
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void questionTest1(){
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon"}, 0, "", 0);
    }
    

    public void questionTest2(){
        // Arrange
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London"}, 0, "", 1);

        //Act
        

        //Assert
        assertEqual(q.getChoices(), {"Lisbon", "London"});
        assertEqual(q.getWeight(), 1);
    }
    
    public void questionTest3(){
        // Arrange
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris"}, 0, "", 15);
    
    
    }

    public void questionTest4(){
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris"}, 0, "", 10);

        //Act and Assert

        // InvalidOperationException?
        assertThrows(IllegalStateException.class, () -> q.setWeight(16));
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris"});
    }

}