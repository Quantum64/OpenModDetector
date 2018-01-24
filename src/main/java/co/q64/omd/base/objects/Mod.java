package co.q64.omd.base.objects;

import java.util.Optional;
import java.util.regex.Pattern;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import co.q64.omd.base.type.ModType;
import co.q64.omd.base.util.ArgumentIterator;
import co.q64.omd.base.util.ArgumentIteratorDefinition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AutoFactory
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Mod {
	private String name;
	private String version;
	private ModType type;
	private long login;

	protected Mod(String name, String version, ModType type) {
		this(name, version, type, System.currentTimeMillis());
	}

	protected Mod(String name, ModType type) {
		this(name, "", type);
	}

	protected Mod(@Provided ArgumentIteratorDefinition factory, String data) {
		this("Unknown", ModType.MISC);
		ArgumentIterator iterator = factory.create(data.split(Pattern.quote("&")));
		if (iterator.hasNext()) {
			this.name = iterator.next();
		}
		if (iterator.hasNext()) {
			this.version = iterator.next();
		}
		if (iterator.hasNext()) {
			Optional<ModType> opt = ModType.of(iterator.next());
			if (opt.isPresent()) {
				this.type = opt.get();
			}
		}
		if (iterator.hasNext()) {
			try {
				this.login = Long.parseLong(iterator.next());
			} catch (NumberFormatException e) {
				// Ignored
			}
		}
	}

	public String encode() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}
}
