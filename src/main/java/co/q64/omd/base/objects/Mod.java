package co.q64.omd.base.objects;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
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
	private boolean current;

	protected Mod(String name, String version, ModType type) {
		this(name, version, type, System.currentTimeMillis(), true);
	}

	protected Mod(String name, ModType type) {
		this(name, "", type);
	}

	protected Mod(@Provided ArgumentIteratorDefinition factory, String data) {
		this("Unknown", ModType.MISC);
		this.current = false;
		Decoder decoder = Base64.getDecoder();
		ArgumentIterator iterator = factory.create(data.split(Pattern.quote("#")));
		if (iterator.hasNext()) {
			this.name = new String(decoder.decode(iterator.next()), StandardCharsets.UTF_8);
		}
		if (iterator.hasNext()) {
			this.version = new String(decoder.decode(iterator.next()), StandardCharsets.UTF_8);
		}
		if (iterator.hasNext()) {
			Optional<ModType> opt = ModType.of(new String(decoder.decode(iterator.next()), StandardCharsets.UTF_8));
			if (opt.isPresent()) {
				this.type = opt.get();
			}
		}
		if (iterator.hasNext()) {
			try {
				this.login = Long.parseLong(new String(decoder.decode(iterator.next()), StandardCharsets.UTF_8));
			} catch (NumberFormatException e) {
				// Ignored
			}
		}
	}

	public String encode() {
		Encoder encoder = Base64.getEncoder();
		StringBuilder sb = new StringBuilder();
		sb.append(encoder.encodeToString(name.getBytes(StandardCharsets.UTF_8)));
		sb.append("#");
		sb.append(encoder.encodeToString(version.getBytes(StandardCharsets.UTF_8)));
		sb.append("#");
		sb.append(encoder.encodeToString(type.name().getBytes(StandardCharsets.UTF_8)));
		sb.append("#");
		sb.append(encoder.encodeToString(String.valueOf(login).getBytes(StandardCharsets.UTF_8)));
		return sb.toString();
	}
}
