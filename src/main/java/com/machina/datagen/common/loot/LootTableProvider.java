package com.machina.datagen.common.loot;

import com.machina.registration.init.BlockInit;

import net.minecraft.data.DataGenerator;

public class LootTableProvider extends BaseLootTableProvider {

	public LootTableProvider(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected void addTables() {
		dropSelf(BlockInit.SHIP_CONSOLE.get());
		dropSelf(BlockInit.PRESSURIZED_CHAMBER.get());
		dropSelf(BlockInit.COMPONENT_ANALYZER.get());
		dropSelf(BlockInit.STEEL_BLOCK.get());
		dropSelf(BlockInit.STEEL_CHASSIS.get());
		dropSelf(BlockInit.ALUMINUM_BLOCK.get());
		dropSelf(BlockInit.ALUMINUM_ORE.get());
		dropSelf(BlockInit.COPPER_BLOCK.get());
		dropSelf(BlockInit.COPPER_ORE.get());
		dropSelf(BlockInit.IRON_CHASSIS.get());
		dropSelf(BlockInit.BATTERY.get());
		dropSelf(BlockInit.CABLE.get());
		dropSelf(BlockInit.REINFORCED_TILE.get());
		dropSelf(BlockInit.TANK.get());
		dropSelf(BlockInit.ATMOSPHERIC_SEPARATOR.get());
		dropSelf(BlockInit.TEMPERATURE_REGULATOR.get());
		dropSelf(BlockInit.PRESSURIZED_CHAMBER.get());
		dropSelf(BlockInit.FUEL_STORAGE_UNIT.get());
		dropSelf(BlockInit.FURNACE_GENERATOR.get());
		dropSelf(BlockInit.STATE_CONVERTER.get());
	}
}