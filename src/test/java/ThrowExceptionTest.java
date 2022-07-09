import com.lei.Library;

/**
 * @author lei
 * @create 2022-07-08-2:46 PM
 */
public class ThrowExceptionTest{


        @org.junit.Test(expected = RuntimeException.class)
        public void Should_Throw_Runtime_Exception_When_log_without_Filling_Params() {

            Library library = new Library("http://localhost:8686/api/events", 6);
            library.log();

        }

        @org.junit.Test(expected = RuntimeException.class)
        public void Should_Throw_Runtime_Exception_When_Return_Status_Is_Not_200() {
            Library library = new Library("http://localhost:8686/api/events", 6);
            library.fillParameters(1.23456780f).fillParameters(8.7654321f).fillParameters("test");

            library.log();


        }
}
