package prswe2.ss21.ue02.download.jayunit;

@SuppressWarnings("serial")
public class TestFailedException extends Exception {

    private Class<?> errorClass;

    public TestFailedException(String message, Class<?> errorClass) {
        super(message);
        this.errorClass = errorClass;
    }

}
