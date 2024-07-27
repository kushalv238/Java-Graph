package graph;

/**
 * Thrown when an application attempts to use a negative weight in a
 * case where only non-negative weights are allowed.
 * <p>
 * Applications should throw instances of this class to indicate
 * illegal use of negative weights in some graph algorithms.
 *
 * @since   1.0
 */
public class NegativeWeightException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = 6159739578135045789L;

    /**
     * Constructs a {@code NegativeWeightException} with no detail message.
     */
    public NegativeWeightException() {
        super();
    }

    /**
     * Constructs a {@code NegativeWeightException} with the specified
     * detail message.
     *
     * @param   message   the detail message.
     */
    public NegativeWeightException(String message) {
        super(message);
    }

    // Additional state tracking can be added here if necessary.

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return super.fillInStackTrace();
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * <p> If a non-null message was supplied in a constructor it is
     * returned. Otherwise, {@code null} is returned.
     *
     * @return the detail message string, which may be {@code null}.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
