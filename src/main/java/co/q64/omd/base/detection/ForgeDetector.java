package co.q64.omd.base.detection;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;

import co.q64.omd.base.util.ModUpdater;
import co.q64.omd.base.util.PlayerSender;
import io.netty.buffer.Unpooled;

@Singleton
public class ForgeDetector implements Detector {
	protected @Inject ModUpdater updater;

	protected @Inject ForgeDetector() {}

	@Override
	public void processPluginMessage(PlayerSender player, String channel, byte[] message) {

	}

	@Override
	public void processJoin(PlayerSender player) {
		salutation(player);
		forgeServerPayload(player);
		modListRequestPayload(player);
	}

	@Override
	public List<String> getIncomingChannels() {
		return Arrays.asList("FML|HS");
	}

	@Override
	public List<String> getOutgoingChannels() {
		return Arrays.asList("FML|HS", "FML", "FML|MP", "FORGE");
	}

	private void salutation(PlayerSender player) {
		String salutation = Joiner.on('\0').join(Arrays.asList("FML|HS", "FML", "FML|MP", "FORGE"));
		byte[] message = Unpooled.wrappedBuffer(salutation.getBytes(Charsets.UTF_8)).array();
		player.sendPluginMessage("REGISTER", message);
	}

	private void forgeServerPayload(PlayerSender player) {
		ByteBuffer hello = ByteBuffer.allocate(6);
		hello.put((byte) 0);
		hello.put((byte) 2);
		hello.putInt(0);
		player.sendPluginMessage("FML|HS", hello.array());
	}

	private void modListRequestPayload(PlayerSender player) {
		ByteBuffer mods = ByteBuffer.allocate(5);
		mods.put((byte) 2);
		mods.putInt(0);
		player.sendPluginMessage("FML|HS", mods.array());
	}
}
