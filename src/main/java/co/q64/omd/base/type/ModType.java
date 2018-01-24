package co.q64.omd.base.type;

import java.util.Optional;

public enum ModType {
	FORGE("forge"), CLIENT("client"), MISC("miscellaneous");

	private String name;

	private ModType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static Optional<ModType> of(String name) {
		for (ModType type : values()) {
			if (type.getName().equals(name)) {
				return Optional.of(type);
			}
		}
		return Optional.empty();
	}
}
