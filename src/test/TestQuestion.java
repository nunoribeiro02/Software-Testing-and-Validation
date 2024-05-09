import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.*;


public class TestingQuestion{
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void questionTest1(){
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris"}, 0, "Capital", 0);
    }
    

    public void questionTest2(){
        // Arrange
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris", "Rome"}, 0, "Capitals", 1);

        //Act
        q.add("Geography");

        //Assert
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris", "Rome"});
        assertEqual(q.getWeight(), 1);
        assertEqual(q.grade(0), 1);
        assertEqual(q.getTopics(), {"Capitals", "Geography"});
    }
    
    public void questionTest3(){
        // Arrange
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris", "Rome", "Amsterdam"}, 0, "Geography", 15);
        q.add("Capitals");
        q.add("Cities");
        q.add("Europe");

        //Act and Assert
        assertEqual(q.getWeight(), 15);
        assertEqual(q.grade(0), 15);
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris", "Rome", "Amsterdam"});
        assertEqual(q.getTopics(), {});
    }

    public void questionTest4(){
        // Arrange
        Question q = new Question("What is the Capital of Portugal?",  {"Lisbon", "London", "Paris", "Rome", "Amsterdam", "Brussels"},  0, "GeographyI", 10);
        q.add("Capitals");
        q.add("Cities");
        q.add("Europe");
        //Act and Assert

        assertThrows(InvalidOperationException.class, () -> q.setWeight(16));
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris", "Rome", "Amsterdam", "Brussels"});
    }

    public void questionTest5(){
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris", "Rome", "Amsterdam", "Brussels", "Athens"}, 0, "", 2);

        //Act and Assert

        assertThrows(InvalidOperationException.class, () -> q.setWeight(16));
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris", "Rome", "Amsterdam", "Brussels", "Athens"});
    }

    public void questionTest5(){
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris", "Rome", "Amsterdam", "Brussels", "Athens"}, 0, "", 2);

        //Act and Assert
        assertThrows(InvalidOperationException.class, () -> q.setWeight(16));
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris", "Rome", "Amsterdam", "Brussels", "Athens"});
    }

    public void questionTest5(){
        // Arrange
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris", "Rome", "Amsterdam", "Brussels", "Athens"}, 0, "", 2);

        //Act and Assert

        assertThrows(InvalidOperationException.class, () -> q.setWeight(16));
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris", "Rome", "Amsterdam", "Brussels", "Athens"});
    }
}   