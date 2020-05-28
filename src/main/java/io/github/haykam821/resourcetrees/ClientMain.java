package io.github.haykam821.resourcetrees;

import io.github.haykam821.resourcetrees.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;

public class ClientMain implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		for (ModBlocks modBlock : ModBlocks.values()) {
			// Render layers
			BlockRenderLayerMap.INSTANCE.putBlock(modBlock.logBlock, RenderLayer.getCutoutMipped());
			BlockRenderLayerMap.INSTANCE.putBlock(modBlock.leavesBlock, RenderLayer.getCutoutMipped());
			BlockRenderLayerMap.INSTANCE.putBlock(modBlock.saplingBlock, RenderLayer.getCutout());

			// Color providers
			ColorProviderRegistry.BLOCK.register((blockState, view, pos, tintIndex) -> {
				return modBlock.color;
			}, modBlock.leavesBlock, modBlock.logBlock, modBlock.saplingBlock);
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return modBlock.color;
			}, modBlock.leavesItem, modBlock.logItem, modBlock.saplingItem);
		}
	}
}