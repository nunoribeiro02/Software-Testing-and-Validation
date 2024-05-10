import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.*;


public class TestQuestion{
    
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
        assertEqual(q.getTopics(), {"Capitals", "Geography", "Europe"});
    }
    
    public void questionTest3(){
        // Arrange
        Question q = new Question("Capital of Portugal?", {"London", "Paris", "Lisbon", "Rome", "Amsterdam"}, 2, "Geography", 9);
        q.add("Capitals");
        q.add("Cities");
        q.add("Europe");

        //Act
        q.setWeight(15);

        // Assert
        assertEqual(q.getWeight(), 15);
        assertEqual(q.grade(2), 15);
        assertEqual(q.getChoices(), {"London", "Paris", "Lisbon", "Rome", "Amsterdam"});
        assertEqual(q.getTopics(), {"Geography", "Capitals", "Cities", "Europe"});
    }

    public void questionTest4(){
        // Arrange
        Question q = new Question("What is the biggest city in Portugal?",  {"London", "Paris", "Rome", "Amsterdam", "Brussels", "Lisbon"},  5, "GeographyI", 10);
        q.add("Capitals");

        //Act and Assert
        assertThrows(InvalidOperationException.class, () -> q.setWeight(16));
        assertEqual(q.getChoices(), {"London", "Paris", "Rome", "Amsterdam", "Brussels", "Lisbon"});
        assertEqual(q.getTopics(), {"GeographyI", "Capitals"});
        assertEqual(q.grade(2), 0);
    }

    public void questionTest5(){
        // Arrange
        Question q = new Question("City in Portugal?", {"London", "Paris", "Rome", "Lisbon", "Amsterdam", "Brussels", "Athens"}, 3, "Cities", 2);
        q.add("Europe");
        q.add("Capitals");
        q.add("Geography");
        //Act
        q.remove("Geography");

        //Act and Assert
        assertEqual(q.grade(3), 2);
        assertEqual(q.getTopics(), {"Cities", "Europe", "Capitals"});
        assertEqual(q.getWeight(), 2);
        assertEqual(q.getChoices(), {"London", "Paris", "Rome", "Lisbon", "Amsterdam", "Brussels", "Athens"});
    }

    public void questionTest6(){
        Question q = new Question("City in Portugal?", {"Lisbon", "London", "Paris"}, 0, "Cities", 3);

        //Act and Assert
        assertThrows(InvalidOperationException.class, () -> q.add("World"));
        assertEqual(q.getChoices(), {"Lisbon", "London", "Paris"});
        assertEqual(q.getWeight(), 3);
        assertEqual(q.grade(0), 3);

    }

    public void questionTest7(){
        // Arrange
        Question q = new Question("What is the Capital of Portugal?", {"London", "Lisbon", "Paris", "Rome"}, 1, "GeographyII", 4);
        q.add("Europe");
        q.add("Capitals");
        q.add("Cities");

        // Act 
        q.add("Culture");
        
        // Assert
        assertEqual(q.getTopics(), {"GeographyII", "Europe", "Capitals", "Cities", "Culture"});
        assertEqual(q.getWeight(), 4);
        assertEqual(q.getChoices(), {"London"," Lisbon", "Paris", "Rome"});
        assertEqual(q.grade(2), 0);
    }

    public void questionTest8(){
        // Arrange
        Question q = new Question("Most famous city of Portugal?", {"London", "Paris", "Rome", "Amsterdam", "Lisbon"}, 4, "GeographyIII", 5);
        q.add("Europe");
        q.add("Capitals");
        q.add("Cities");
        q.add("Culture");

        //Act and Assert
        assertThrows(InvalidOperationException.class, () -> q.add("SixthCategory"));
        assertEqual(q.getChoices(), {"London", "Paris", "Rome", "Amsterdam", "Lisbon"});
        assertEqual(q.grade(4), 5);
        assertEqual(q.getWeight(), 5);
    }
    
}   