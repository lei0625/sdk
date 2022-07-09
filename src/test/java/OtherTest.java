import com.lei.Library;
import org.junit.Assert;


/**
 * @author lei
 * @create 2022-07-08-1:32 PM
 */
public class OtherTest {
    @org.junit.Test
    public void Should_Pass_Two_Location_Event_To_Server_When_Input_Twice() {
        Library library = new Library("http://localhost:8686/api/events", 6);
        library.fillParameters(1.23456780f).fillParameters(8.7654321f).fillParameters("test1");
        library.fillParameters(1.2345f).fillParameters(8.7654f).fillParameters("test2");
        try {
            library.log();
        } catch (Exception e) {

        }

        int size = library.message.payload.size();

        Assert.assertEquals(size, 2);
    }
}
