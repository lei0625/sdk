import com.lei.Library;
import org.junit.Assert;

/**
 * @author lei
 * @create 2022-07-08-2:42 PM
 */
public class ReturnStatusCodeTest {

    @org.junit.Test
    public void Should_Return_200_When_All_Params_Appear_With_Correct_Type() {
        Library library = new Library("http://localhost:8686/api/events", 1);
        library.fillParameters(11.0f).fillParameters(22.1f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        library.log();
        Assert.assertEquals(library.response.getStatusLine().getStatusCode(), 200);
    }

    @org.junit.Test
    public void Should_Return_406_When_Lat_Is_Null() {
        Library library = new Library("http://localhost:8686/api/events", 1);
        library.fillParameters(null).fillParameters(22.1f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Assert.assertEquals(library.response.getStatusLine().getStatusCode(), 406);
    }

    @org.junit.Test
    public void Should_Return_406_When_Lat_Is_Wrong_Type() {
        Library library = new Library("http://localhost:8686/api/events", 1);
        library.fillParameters(12).fillParameters(22.1f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Assert.assertEquals(library.response.getStatusLine().getStatusCode(), 406);
    }

    @org.junit.Test
    public void Should_Return_406_When_Lon_Is_Null() {
        Library library = new Library("http://localhost:8686/api/events", 1);
        library.fillParameters(11.0f).fillParameters(null).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Assert.assertEquals(library.response.getStatusLine().getStatusCode(), 406);
    }


    @org.junit.Test
    public void Should_Return_406_When_Lon_Is_Wrong_Type() {
        Library library = new Library("http://localhost:8686/api/events", 1);
        library.fillParameters(11.0f).fillParameters(12).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Assert.assertEquals(library.response.getStatusLine().getStatusCode(), 406);
    }

    @org.junit.Test
    public void Should_Return_406_When_Time_Is_Null() {
        Library library = new Library("http://localhost:8686/api/events", 1);
        library.fillParameters(11.0f).fillParameters(22.1f).fillParameters(null).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Assert.assertEquals(library.response.getStatusLine().getStatusCode(), 406);
    }

    @org.junit.Test
    public void Should_Return_406_When_Time_Is_Wrong_Type() {
        Library library = new Library("http://localhost:8686/api/events", 1);
        library.fillParameters(11.0f).fillParameters(22.1f).fillParameters(12.1).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Assert.assertEquals(library.response.getStatusLine().getStatusCode(), 406);
    }
}
