package io.github.haykam821.resourcetrees.block.sapling;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;

public class ResourceSaplingBlock extends SaplingBlock {
	public ResourceSaplingBlock(Block logBlock, Block leavesBlock) {
		super(new ResourceSaplingGenerator(logBlock, leavesBlock), FabricBlockSettings.copy(Blocks.OAK_SAPLING));
	}
}