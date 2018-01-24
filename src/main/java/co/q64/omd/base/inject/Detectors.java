package co.q64.omd.base.inject;

import co.q64.omd.base.detection.Detector;
import co.q64.omd.base.detection.ForgeDetector;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public interface Detectors {
	public @Binds @IntoSet Detector getForgeDetector(ForgeDetector forgeDetector);
}
