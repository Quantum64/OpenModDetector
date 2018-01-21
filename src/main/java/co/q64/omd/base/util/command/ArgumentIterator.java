package co.q64.omd.base.util.command;

import java.util.Iterator;

public class ArgumentIterator implements Iterator<String> {
	private String[] args;
	private int index = 0;

	protected ArgumentIterator(String[] args) {
		this.args = args;
	}

	@Override
	public boolean hasNext() {
		return args.length > index;
	}

	@Override
	public String next() {
		index++;
		return args[index - 1];
	}
}
