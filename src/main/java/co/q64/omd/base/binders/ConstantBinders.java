package co.q64.omd.base.binders;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

public interface ConstantBinders {
	//formatter:off
	public static @Qualifier @Retention(RUNTIME) @interface BasePermissionNode {}
	public static @Qualifier @Retention(RUNTIME) @interface ModListCommandName {}
	public static @Qualifier @Retention(RUNTIME) @interface ModHistoryCommandName {}
	public static @Qualifier @Retention(RUNTIME) @interface AllModsCommandName {}
	public static @Qualifier @Retention(RUNTIME) @interface ReloadCommandName {}
	public static @Qualifier @Retention(RUNTIME) @interface ModFiltersCommandName {}
	public static @Qualifier @Retention(RUNTIME) @interface Name {}
	public static @Qualifier @Retention(RUNTIME) @interface Author {}
	//formatter:on
}