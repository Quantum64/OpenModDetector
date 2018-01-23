package co.q64.omd.base.util;

public interface Logger {
	public void info(String message);

	public void error(String message);

	public void error(String message, Throwable t);

	public void warn(String message);

	public void warn(String message, Throwable t);
}
