import com.lei.Library;
import org.junit.Assert;

/**
 * @author lei
 * @create 2022-07-08-2:45 PM
 */
public class PricesionTest {

    @org.junit.Test
    public void Should_Round_Half_UP_When_Assume_Round_Half_Up_Rule() {
        Library library = new Library("http://localhost:8686/api/events", 2);
        library.fillParameters(1.235f).fillParameters(2.342f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Float lat = library.message.payload.get(0).lat;
        Float lon = library.message.payload.get(0).lon;

        boolean res = lat == 1.24f && lon == 2.34f;

        Assert.assertTrue(res);

    }

    @org.junit.Test
    public void Should_Cut_Precision_Into_Zero_Place_When_Scope_Param_Is_Zero() {
        Library library = new Library("http://localhost:8686/api/events", 0);
        library.fillParameters(1.23456780f).fillParameters(8.7654321f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Float lat = library.message.payload.get(0).lat;
        Float lon = library.message.payload.get(0).lon;

        boolean res = lat == 1f && lon == 9f;

        Assert.assertTrue(res);

    }

    @org.junit.Test
    public void Should_Cut_Precision_Into_One_Place_When_Scope_Param_Is_One() {
        Library library = new Library("http://localhost:8686/api/events", 1);
        library.fillParameters(1.23456780f).fillParameters(8.7654321f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Float lat = library.message.payload.get(0).lat;
        Float lon = library.message.payload.get(0).lon;

        boolean res = lat == 1.2f && lon == 8.8f;

        Assert.assertTrue(res);
    }

    @org.junit.Test
    public void Should_Cut_Precision_Into_Two_Place_When_Scope_Param_Is_Two() {
        Library library = new Library("http://localhost:8686/api/events", 2);
        library.fillParameters(1.23456780f).fillParameters(8.7654321f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Float lat = library.message.payload.get(0).lat;
        Float lon = library.message.payload.get(0).lon;

        boolean res = lat == 1.23f && lon == 8.77f;

        Assert.assertTrue(res);
    }

    @org.junit.Test
    public void Should_Cut_Precision_Into_Three_Place_When_Scope_Param_Is_Three() {
        Library library = new Library("http://localhost:8686/api/events", 3);
        library.fillParameters(1.23456780f).fillParameters(8.7654321f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Float lat = library.message.payload.get(0).lat;
        Float lon = library.message.payload.get(0).lon;

        boolean res = lat == 1.235f && lon == 8.765f;

        Assert.assertTrue(res);
    }

    @org.junit.Test
    public void Should_Cut_Precision_Into_Four_Place_When_Scope_Param_Is_Four() {
        Library library = new Library("http://localhost:8686/api/events", 4);
        library.fillParameters(1.23456780f).fillParameters(8.7654321f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Float lat = library.message.payload.get(0).lat;
        Float lon = library.message.payload.get(0).lon;

        boolean res = lat == 1.2346f && lon == 8.7654f;

        Assert.assertTrue(res);
    }

    @org.junit.Test
    public void Should_Cut_Precision_Into_Five_Place_When_Scope_Param_Is_Five() {
        Library library = new Library("http://localhost:8686/api/events", 5);
        library.fillParameters(1.23456780f).fillParameters(8.7654321f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Float lat = library.message.payload.get(0).lat;
        Float lon = library.message.payload.get(0).lon;

        boolean res = lat == 1.23457f && lon == 8.76543f;

        Assert.assertTrue(res);
    }

    @org.junit.Test
    public void Should_Cut_Precision_Into_Five_Place_When_Scope_Param_Is_Six() {
        Library library = new Library("http://localhost:8686/api/events", 6);
        library.fillParameters(1.23456780f).fillParameters(8.7654321f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        Float lat = library.message.payload.get(0).lat;
        Float lon = library.message.payload.get(0).lon;

        boolean res = lat == 1.234568f && lon == 8.765432f;

        Assert.assertTrue(res);
    }
}
