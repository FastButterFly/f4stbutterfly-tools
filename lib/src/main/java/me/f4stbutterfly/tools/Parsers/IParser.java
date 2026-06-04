package me.f4stbutterfly.tools.Parsers;

public interface IParser<T> {
	public abstract T parse(String from) throws Exception;
}
