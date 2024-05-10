import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.*;


public class TestingQuestion{
    
    @Test(expectedExceptions = InvalidOperationException.class)
    public void questionTest1(){
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris"}, 0, "Capital", 0);
    }
    

    public void questionTest2(){
        // Arrange
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris", "Rome"}, 0, "Capitals", 1);

        //Act
        q.add("Geography");
        q.add("Europe");

        //Assert
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris", "Rome"});
        assertEqual(q.getWeight(), 1);
        assertEqual(q.grade(0), 1);
        assertEqual(q.getTopics(), {"Capitals", "Geography"});
    }
    
    public void questionTest3(){
        // Arrange
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris", "Rome", "Amsterdam"}, 0, "Geography", 9);
        q.add("Capitals");
        q.add("Cities");
        q.add("Europe");

        //Act
        q.setWeight(15);

        // Assert
        assertEqual(q.getWeight(), 15);
        assertEqual(q.grade(0), 15);
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris", "Rome", "Amsterdam"});
        assertEqual(q.getTopics(), {"Geography", "Capitals", "Cities", "Europe"});
    }

    public void questionTest4(){
        // Arrange
        Question q = new Question("What is the Capital of Portugal?",  {"Lisbon", "London", "Paris", "Rome", "Amsterdam", "Brussels"},  0, "GeographyI", 10);
        q.add("Capitals");

        //Act and Assert
        assertThrows(InvalidOperationException.class, () -> q.setWeight(16));
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris", "Rome", "Amsterdam", "Brussels"});
        assertEqual(q.getTopics(), {"GeographyI", "Capitals"});
        assertEqual(q.grade(2), 0);
    }

    public void questionTest5(){
        // Arrange
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris", "Rome", "Amsterdam", "Brussels", "Athens"}, 0, "Cities", 2);
        q.add("Europe");
        q.add("Capitals");
        q.add("Geography");
        //Act
        q.remove("Geography");

        //Act and Assert
        assertEqual(q.grade(0), 2);
        assertEqual(q.getTopics(), {"Cities", "Europe", "Capitals"});
        assertEqual(q.getWeight(), 2);
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris", "Rome", "Amsterdam", "Brussels", "Athens"});
    }

    public void questionTest6(){
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris"}, 0, "Cities", 3);

        //Act and Assert
        assertThrows(InvalidOperationException.class, () -> q.add("World"));
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris"});
        assertEqual(q.getWeight(), 3);
        assertEqual(q.grade(0), 3);

    }

    public void questionTest7(){
        // Arrange
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris", "Rome"}, 0, "GeographyII", 4);
        q.add("Europe");
        q.add("Capitals");
        q.add("Cities");

        // Act 
        q.add("Culture");
        
        // Assert
        assertEqual(q.getTopics(), {"GeographyII", "Europe", "Capitals", "Cities", "Culture"});
        assertEqual(q.getWeight(), 4);
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris", "Rome"});
        assertEqual(q.grade(0), 4);
    }

    public void questionTest8(){
        // Arrange
        Question q = new Question("What is the Capital of Portugal?", {"Lisbon", "London", "Paris", "Rome", "Amsterdam"}, 0, "GeographyIII", 5);
        q.add("Europe");
        q.add("Capitals");
        q.add("Cities");
        q.add("Culture");

        //Act and Assert
        assertThrows(InvalidOperationException.class, () -> q.add("SixthCategory"));
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris", "Rome", "Amsterdam"});
        assertEqual(q.grade(0), 5);
        assertEqual(q.getWeight(), 5);
    }
    
}   