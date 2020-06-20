package io.github.haykam821.resourcetrees.block.sapling;

import java.util.Random;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.world.gen.decorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class ResourceSaplingGenerator extends OakSaplingGenerator {
	private final TreeFeatureConfig treeConfig;
	private final TreeFeatureConfig beehiveTreeConfig;

	public ResourceSaplingGenerator(Block logBlock, Block leavesBlock) {
		super();

		SimpleBlockStateProvider logProvider = new SimpleBlockStateProvider(logBlock.getDefaultState());
		SimpleBlockStateProvider leavesProvider = new SimpleBlockStateProvider(leavesBlock.getDefaultState());

		this.treeConfig = (new TreeFeatureConfig.Builder(logProvider, leavesProvider, new BlobFoliagePlacer(2, 0, 0, 0, 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build();
		this.beehiveTreeConfig = this.treeConfig.setTreeDecorators(ImmutableList.of(new BeehiveTreeDecorator(0.05f)));
	}

	public ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean hasBeehives) {
		if (hasBeehives) {
			return Feature.TREE.configure(this.beehiveTreeConfig);
		}
		return Feature.TREE.configure(this.treeConfig);
	}
}