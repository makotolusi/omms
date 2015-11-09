package m.w.core.exception;

public class AppException extends RuntimeException{
	private static final long serialVersionUID = -1586465704615352546L;

	public AppException() {
		super();
	}

	public AppException(String msg, Throwable err) {
		super(msg, err);
	}

	public AppException(String msg) {
		super(msg);
	}

	public AppException(Throwable err) {
		super(err);
	}
}
