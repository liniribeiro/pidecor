package com.aliniribeiro.admin.api.common.exceptions;

public class InternalExceptions {

    public ExceptionType exception;
    public String message;
    public String object;

    public InternalExceptions() {
    }

    /**
     * This constructor allows initialization of all fields, required and optional.
     */
    public InternalExceptions(ExceptionType exception) {
        this.exception = exception;
        this.message = exception.getDescription();
    }

    public InternalExceptions(ExceptionType exception, String object) {
        this.exception = exception;
        this.message = exception.getDescription();
        this.object = object;
    }

    @Override
    public int hashCode() {
        int ret = 1;
        if (exception != null) {
            ret = 31 * ret + exception.hashCode();
        }
        if (message != null) {
            ret = 31 * ret + message.hashCode();
        }
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InternalExceptions)) {
            return false;
        }
        InternalExceptions other = (InternalExceptions) obj;
        if ((exception == null) != (other.exception == null)) {
            return false;
        }
        if ((exception != null) && !exception.equals(other.exception)) {
            return false;
        }
        if ((message == null) != (other.message == null)) {
            return false;
        }
        if ((message != null) && !message.equals(other.message)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(" [");
        sb.append("exception=").append(exception == null ? "null" : exception).append(", ");
        sb.append("message=").append(message == null ? "null" : message);
        sb.append(']');
        return sb.toString();
    }

}
