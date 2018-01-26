package co.q64.omd.base.inject;

import co.q64.omd.base.annotation.Mock;
import co.q64.omd.base.binders.ConstantBinders.AllModsCommandName;
import co.q64.omd.base.binders.ConstantBinders.Author;
import co.q64.omd.base.binders.ConstantBinders.BasePermissionNode;
import co.q64.omd.base.binders.ConstantBinders.ModFiltersCommandName;
import co.q64.omd.base.binders.ConstantBinders.ModHistoryCommandName;
import co.q64.omd.base.binders.ConstantBinders.ModListCommandName;
import co.q64.omd.base.binders.ConstantBinders.Name;
import co.q64.omd.base.binders.ConstantBinders.ReloadCommandName;
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

	protected @Provides @ModListCommandName String getModListCommandName() {
		return ConstantPool.MODLIST_COMMAND;
	}

	protected @Provides @ModHistoryCommandName String getModHistoryCommandName() {
		return ConstantPool.MODHISTORY_COMMAND;
	}

	protected @Provides @AllModsCommandName String getAllModsCommandName() {
		return ConstantPool.ALLMODS_COMMAND;
	}

	protected @Provides @ReloadCommandName String getReloadCommandName() {
		return ConstantPool.RELOAD_COMMAND;
	}

	protected @Provides @ModFiltersCommandName String getModFiltersCommandName() {
		return ConstantPool.MODFILTERS_COMMAND;
	}

	protected @Provides @Name String getPluginName() {
		return ConstantPool.NAME;
	}

	protected @Provides @Author String getPluginAuthor() {
		return ConstantPool.AUTHOR;
	}
}
