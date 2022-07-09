import com.lei.Library;
import org.junit.Assert;

/**
 * @author lei
 * @create 2022-07-08-2:44 PM
 */
public class AutoFillTest {

    @org.junit.Test
    public void Should_Add_Current_Time_When_Time_Is_Null() {
        Library library = new Library("http://localhost:8686/api/events", 1);
        library.fillParameters(11.0f).fillParameters(22.1f).fillParameters(null).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }
        Library.LocationEvent event = library.message.payload.get(0);
        Long time = event.time;
        Assert.assertNotNull(time);
    }

    @org.junit.Test
    public void Should_Add_Current_Time_When_Time_Is_Ignored() {
        Library library = new Library("http://localhost:8686/api/events", 1);
        library.fillParameters(11.0f).fillParameters(22.1f).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }
        Library.LocationEvent event = library.message.payload.get(0);
        Long time = event.time;
        Assert.assertNotNull(time);
    }


    @org.junit.Test
    public void Should_Assign_Zeros_When_Scope_Param_Not_Appear() {
        Library library = new Library("http://localhost:8686/api/events");
        library.fillParameters(11.0f).fillParameters(22.1f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Float lat = library.message.payload.get(0).lat;
        Float lon = library.message.payload.get(0).lon;


    }
}
