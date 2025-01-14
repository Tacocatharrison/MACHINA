package com.machina.world.feature;

import java.util.List;

import org.apache.logging.log4j.Level;

import com.google.common.collect.ImmutableList;
import com.machina.Machina;
import com.machina.util.text.MachinaRL;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class ShipStructure extends Structure<NoFeatureConfig> {
	public ShipStructure(Codec<NoFeatureConfig> pCodec) {
		super(pCodec);
	}

	@Override
	public IStartFactory<NoFeatureConfig> getStartFactory() {
		return ShipStructure.Start::new;
	}

	@Override
	public GenerationStage.Decoration step() {
		return GenerationStage.Decoration.SURFACE_STRUCTURES;
	}

	private static final List<MobSpawnInfo.Spawners> STRUCTURE_MONSTERS = ImmutableList.of(
			new MobSpawnInfo.Spawners(EntityType.ILLUSIONER, 100, 4, 9),
			new MobSpawnInfo.Spawners(EntityType.VINDICATOR, 100, 4, 9));

	@Override
	public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
		return STRUCTURE_MONSTERS;
	}

	private static final List<MobSpawnInfo.Spawners> STRUCTURE_CREATURES = ImmutableList.of(
			new MobSpawnInfo.Spawners(EntityType.SHEEP, 30, 10, 15),
			new MobSpawnInfo.Spawners(EntityType.RABBIT, 100, 1, 2));

	@Override
	public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {
		return STRUCTURE_CREATURES;
	}

	@Override
	protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed,
			SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos,
			NoFeatureConfig featureConfig) {
		BlockPos centerOfChunk = new BlockPos(chunkX * 16, 0, chunkZ * 16);

		int landHeight = chunkGenerator.getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(),
				Heightmap.Type.WORLD_SURFACE_WG);

		IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ());

		BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));
		return topBlock.getFluidState().isEmpty();
	}

	public static class Start extends StructureStart<NoFeatureConfig> {
		public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ,
				MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
			super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
		}

		@Override
		public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator,
				TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {

			int x = chunkX * 16;
			int z = chunkZ * 16;

			BlockPos centerPos = new BlockPos(x, 0, z);
			JigsawManager.addPieces(dynamicRegistryManager,
					new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
							.get(new MachinaRL("ship/start")), 10),
					AbstractVillagePiece::new, chunkGenerator, templateManagerIn, centerPos, this.pieces, this.random,
					false, true);

			// Move everything up so it doesn't mess with the terrain..
			this.pieces.forEach(piece -> piece.move(0, 2, 0));

			Vector3i structureCenter = this.pieces.get(0).getBoundingBox().getCenter();
			int xOffset = centerPos.getX() - structureCenter.getX();
			int zOffset = centerPos.getZ() - structureCenter.getZ();
			for (StructurePiece structurePiece : this.pieces) {
				structurePiece.move(xOffset, 0, zOffset);
			}

			this.calculateBoundingBox();

			Machina.LOGGER.log(Level.DEBUG, "Ship at " + this.pieces.get(0).getBoundingBox().x0 + " "
					+ this.pieces.get(0).getBoundingBox().y0 + " " + this.pieces.get(0).getBoundingBox().z0);
		}

	}
}
