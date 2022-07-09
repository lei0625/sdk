import com.lei.Library;
import org.junit.Assert;

import java.util.List;

/**
 * @author lei
 * @create 2022-07-08-2:45 PM
 */
public class ErrorMessageTest {

    @org.junit.Test
    public void Should_Pass_Null_Error_To_Server_When_Any_Input_Is_Error() {
        Library library = new Library("http://localhost:8686/api/events", 6);
        library.fillParameters(null).fillParameters(8.7654321f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        boolean contains = false;

        List<Library.SDKError> errorMsgs = library.message.errorMsgs;

        for (Library.SDKError errorMsg : errorMsgs) {
            if (errorMsg.type.equals(Library.ErrorType.NULL_INPUT.toString())) {
                contains = true;
                break;
            }
        }

        Assert.assertTrue(contains);
    }

    @org.junit.Test
    public void Should_Pass_Wrong_Type_Error_To_Server_When_Any_Input_Is_Wrong_Type() {
        Library library = new Library("http://localhost:8686/api/events", 6);
        library.fillParameters(123).fillParameters(8.7654321f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        boolean contains = false;

        List<Library.SDKError> errorMsgs = library.message.errorMsgs;

        for (Library.SDKError errorMsg : errorMsgs) {
            if (errorMsg.type.equals(Library.ErrorType.WRONG_TYPE.toString())) {
                contains = true;
                break;
            }
        }

        Assert.assertTrue(contains);
    }

    @org.junit.Test
    public void Should_Pass_Lat_Absent_Error_To_Server_When_Lat_Input_Is_Absent() {
        Library library = new Library("http://localhost:8686/api/events", 6);
        library.fillParameters(null).fillParameters(null).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        boolean contains = false;

        List<Library.SDKError> errorMsgs = library.message.errorMsgs;

        for (Library.SDKError errorMsg : errorMsgs) {
            if (errorMsg.type.equals(Library.ErrorType.LAT_ABSENT.toString())) {
                contains = true;
                break;
            }
        }

        Assert.assertTrue(contains);
    }

    @org.junit.Test
    public void Should_Pass_Lon_Absent_Error_To_Server_When_Lon_Input_Is_Absent() {
        Library library = new Library("http://localhost:8686/api/events", 6);
        library.fillParameters(8.7654321f).fillParameters(null).fillParameters(System.currentTimeMillis()).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        boolean contains = false;

        List<Library.SDKError> errorMsgs = library.message.errorMsgs;

        for (Library.SDKError errorMsg : errorMsgs) {
            if (errorMsg.type.equals(Library.ErrorType.LON_ABSENT.toString())) {
                contains = true;
                break;
            }
        }

        Assert.assertTrue(contains);
    }

    @org.junit.Test
    public void Should_Pass_Time_Absent_Error_To_Server_When_Time_Input_Is_Absent() {
        Library library = new Library("http://localhost:8686/api/events", 6);
        library.fillParameters(1.23456780f).fillParameters(8.7654321f).fillParameters("test");
        try {
            library.log();
        } catch (Exception e) {

        }

        boolean contains = false;

        List<Library.SDKError> errorMsgs = library.message.errorMsgs;

        for (Library.SDKError errorMsg : errorMsgs) {
            if (errorMsg.type.equals(Library.ErrorType.TIME_ABSENT.toString())) {
                contains = true;
                break;
            }
        }

        Assert.assertTrue(contains);
    }

}
