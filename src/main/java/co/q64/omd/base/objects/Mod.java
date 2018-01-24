package co.q64.omd.base.objects;

import com.google.auto.factory.AutoFactory;

import co.q64.omd.base.type.ModType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@AutoFactory
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Value
public class Mod {
	private String name;
	private String version;
	private ModType type;
	private long login;
	private boolean current;

	protected Mod(String name, String version, ModType type) {
		this(name, version, type, System.currentTimeMillis(), true);
	}

	protected Mod(String name, ModType type) {
		this(name, "", type);
	}

}
