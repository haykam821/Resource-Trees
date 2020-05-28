package io.github.haykam821.resourcetrees;

import io.github.haykam821.resourcetrees.block.ModBlocks;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
	public static final String MOD_ID = "resourcetrees";

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
	}
}