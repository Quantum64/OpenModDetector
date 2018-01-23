package co.q64.omd.base.inject;

import co.q64.omd.base.annotation.BasePermissionNode;
import co.q64.omd.base.annotation.Mock;
import co.q64.omd.base.binders.ConstantPool;
import co.q64.omd.base.config.Configuration;
import co.q64.omd.base.config.MockConfiguration;
import co.q64.omd.base.util.MockSender;
import co.q64.omd.base.util.PlayerSender;
import co.q64.omd.base.util.Sender;
import dagger.Module;
import dagger.Provides;

@Module
public class ModDetectorModule {
	protected @Provides @Mock Sender getMockSender(MockSender sender) {
		return sender;
	}

	protected @Provides @Mock PlayerSender getMockPlayerSender(MockSender sender) {
		return sender;
	}

	protected @Provides @Mock Configuration getMockConfiguration(MockConfiguration mock) {
		return mock;
	}

	protected @Provides @BasePermissionNode String getBasePermissionNode() {
		return ConstantPool.BASE_PERMISSION_NODE;
	}
}
