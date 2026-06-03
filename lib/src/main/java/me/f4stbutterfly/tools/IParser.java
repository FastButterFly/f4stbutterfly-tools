package me.f4stbutterfly.tools;

public interface IParser<T> {
	public abstract T parse(String from) throws Exception;
}
