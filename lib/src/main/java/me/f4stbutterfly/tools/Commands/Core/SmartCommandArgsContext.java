package me.f4stbutterfly.tools.Commands.Core;

import java.util.ArrayList;
import java.util.List;

import me.f4stbutterfly.tools.Parsers.IParser;

public abstract class SmartCommandArgsContext {
	protected final List<SmartCommandArgument> commandArguments = new ArrayList<>();
	
	public final boolean isArgumentPresent(int arge, String[] args) {
		if(arge >= args.length) {
			return false;
		}

		return true;
	}

	public final <T> SmartCommandArgumentPassContext<T> getRequiredArgumentAsType(String argName, IParser<T> parser, String[] args) {
		SmartCommandArgumentPassContext<T> invalid = new SmartCommandArgumentPassContext<>();
		invalid.value = null;
		invalid.isValid = false;

		for(SmartCommandArgument arg : commandArguments) {
			if(arg.argName() == argName) {
				if(!isArgumentPresent(arg.argIndex(), args)) {
					invalid.Reason = "ARG_NOT_FOUND";
					return invalid;
				}
				
				try {
					T val = parser.parse(args[arg.argIndex()]);
					SmartCommandArgumentPassContext<T> r = new SmartCommandArgumentPassContext<>();
					r.isValid = true;
					r.Reason = "";
					r.value = val;
					return r;
				} catch (Exception e) {
					invalid.Reason = "ARG_PARSE_FAILED";
					return invalid;
				}
			}
		}

		invalid.Reason = "ARG_NOT_FOUND";
		return invalid;
	}

	public final <T> List<SmartCommandArgumentPassContext<T>> getArgumentAsList(String argName, IParser<T> parser, String[] args) {
		List<SmartCommandArgumentPassContext<T>> l = new ArrayList<>();
		for(SmartCommandArgument arg : commandArguments) {
			if(arg.argName() == argName) {
				if(!isArgumentPresent(arg.argIndex(), args)) return l;
				for (int i = arg.argIndex(); i<args.length; i++) {
					try {
						T val = parser.parse(args[i]);
						SmartCommandArgumentPassContext<T> r = new SmartCommandArgumentPassContext<>();
						r.isValid = true;
						r.Reason = "";
						r.value = val;
						l.add(r);
					} catch(Exception e) {
						SmartCommandArgumentPassContext<T> a = new SmartCommandArgumentPassContext<>();
						a.isValid = false;
						a.Reason = "ARG_PARSE_FAILED";
						a.value = null;
						l.add(a);
					}
				}
			}
		}

		return l;
	}

	public final String getArgsMsgForHelp() {
		StringBuilder build = new StringBuilder();

		commandArguments.forEach((arg) -> {
			if(arg.type() == SmartCommandArgumentType.Required) {
				if(arg.isList()) {
					build.append("<{" + arg.argName() + "}> ");
				} else {
					build.append("<" + arg.argName() + "> ");
				}
			} else {
				if(arg.isList()) {
					build.append("[{" + arg.argName() + "}] ");
				} else {
					build.append("[" + arg.argName() + "] ");
				}
			}
		});

		return build.toString();
	}
}
