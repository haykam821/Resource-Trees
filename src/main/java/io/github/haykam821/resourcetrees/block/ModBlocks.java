package io.github.haykam821.resourcetrees.block;

import io.github.haykam821.resourcetrees.Main;
import io.github.haykam821.resourcetrees.block.sapling.ResourceSaplingBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum ModBlocks {
	DIRT("dirt", 0x74563E),
	CLAY("clay", 0xA2A7B0),
	SAND("sand", 0xD9CFA8),
	RED_SAND("red_sand", 0xBF7237),
	GRAVEL("gravel", 0x88817F),
	COTTON("cotton", 0xE1E4E4),
	BONE("bone", 0xC7C3A5),
	PETRIFIED("petrified", 0x616161),
	COAL("coal", 0x222222),
	IRON("iron", 0xE0E0E0),
	GOLD("gold", 0xEFCD4F),
	LAPIS("lapis", 0x213A8B),
	REDSTONE("redstone", 0x882211),
	DIAMOND("diamond", 0x7DEAE5),
	EMERALD("emerald", 0x5CC156),
	QUARTZ("quartz", 0xE9E2DB),
	NETHERRACK("netherrack", 0x713131),
	GLOWSTONE("glowstone", 0xF8D773),
	NETHERITE("netherite", 0x4B4042);

	public final int color;

	public PillarBlock logBlock;
	public BlockItem logItem;

	public LeavesBlock leavesBlock;
	public BlockItem leavesItem;

	public SaplingBlock saplingBlock;
	public BlockItem saplingItem;

	public FlowerPotBlock pottedSaplingBlock;

	public final Item acornItem;
	public final Item roastedAcornItem;

	private ModBlocks(String type, int color) {
		this.color = color;

		registerLog(new Identifier(Main.MOD_ID, type + "_log"));
		registerLeaves(new Identifier(Main.MOD_ID, type + "_leaves"));
		registerSapling(new Identifier(Main.MOD_ID, type + "_sapling"));
		registerPottedSapling(new Identifier(Main.MOD_ID, "potted_" + type + "_sapling"));

		// Acorns
		this.acornItem = registerAcorn(new Identifier(Main.MOD_ID, type + "_acorn"), new FoodComponent.Builder().hunger(1).saturationModifier(0.06f).build());
		this.roastedAcornItem = registerAcorn(new Identifier(Main.MOD_ID, "roasted_" + type + "_acorn"), new FoodComponent.Builder().hunger(3).saturationModifier(0.06f).build());
	}

	private void registerLog(Identifier id) {
		Block.Settings blockSettings = FabricBlockSettings.copy(Blocks.OAK_LOG);
		this.logBlock = new PillarBlock(blockSettings);
		Registry.register(Registry.BLOCK, id, this.logBlock);

		Item.Settings itemSettings = new Item.Settings().group(ItemGroup.BUILDING_BLOCKS);
		this.logItem = new BlockItem(this.logBlock, itemSettings);
		Registry.register(Registry.ITEM, id, this.logItem);
	}

	private void registerLeaves(Identifier id) {
		Block.Settings blockSettings = FabricBlockSettings.copy(Blocks.OAK_LEAVES);
		this.leavesBlock = new LeavesBlock(blockSettings);
		Registry.register(Registry.BLOCK, id, this.leavesBlock);

		Item.Settings itemSettings = new Item.Settings().group(ItemGroup.BUILDING_BLOCKS);
		this.leavesItem = new BlockItem(this.leavesBlock, itemSettings);
		Registry.register(Registry.ITEM, id, this.leavesItem);
	}

	private void registerSapling(Identifier id) {
		this.saplingBlock = new ResourceSaplingBlock(this.logBlock, this.leavesBlock);
		Registry.register(Registry.BLOCK, id, this.saplingBlock);

		Item.Settings itemSettings = new Item.Settings().group(ItemGroup.DECORATIONS);
		this.saplingItem = new BlockItem(this.saplingBlock, itemSettings);
		Registry.register(Registry.ITEM, id, this.saplingItem);
	}

	private void registerPottedSapling(Identifier id) {
		Block.Settings blockSettings = FabricBlockSettings.copy(Blocks.POTTED_OAK_SAPLING);
		this.pottedSaplingBlock = new FlowerPotBlock(this.saplingBlock, blockSettings);
		Registry.register(Registry.BLOCK, id, this.pottedSaplingBlock);
	}

	private Item registerAcorn(Identifier id, FoodComponent foodComponent) {
		Item.Settings itemSettings = new Item.Settings().group(ItemGroup.FOOD).food(foodComponent);
		return Registry.register(Registry.ITEM, id, new Item(itemSettings));
	}

	public static ModBlocks initialize() {
		return null;
	}
}