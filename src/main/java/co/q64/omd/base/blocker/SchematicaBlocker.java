package co.q64.omd.base.blocker;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.q64.omd.base.perm.PermissionFactory;
import co.q64.omd.base.util.PlayerSender;

@Singleton
public class SchematicaBlocker implements Blocker {
	protected @Inject BlockerConfigFactory factory;
	protected @Inject PermissionFactory permissions;

	protected @Inject SchematicaBlocker() {}

	@Override
	public void processJoin(PlayerSender player) {
		BlockerConfig config = factory.create();
		ByteBuffer buffer = ByteBuffer.allocate(4);
		boolean printer = config.enabled("schematica-printer") && !permissions.create("bypass.schematica.printer").has(player);
		boolean save = config.enabled("schematica-save") && !permissions.create("bypass.schematica.save").has(player);
		boolean load = config.enabled("schematica-load") && !permissions.create("bypass.schematica.load").has(player);
		buffer.put((byte) 0);
		buffer.put((byte) (printer ? 1 : 0));
		buffer.put((byte) (save ? 1 : 0));
		buffer.put((byte) (load ? 1 : 0));
		player.sendPluginMessage("schematica", buffer.array());
	}

	@Override
	public List<String> getOutgoingChannels() {
		return Arrays.asList("schematica");
	}
}
