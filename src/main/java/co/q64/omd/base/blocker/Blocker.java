package co.q64.omd.base.blocker;

import java.util.Collections;
import java.util.List;

import co.q64.omd.base.util.PlayerSender;

public interface Blocker {
	public void processJoin(PlayerSender player);
	
	public default List<String> getOutgoingChannels() {
		return Collections.emptyList();
	}
}
