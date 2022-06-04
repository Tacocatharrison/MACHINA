package com.machina.block.tile;

import com.machina.block.container.BatteryContainer;
import com.machina.energy.EnergyDefinition;
import com.machina.registration.init.TileEntityTypesInit;
import com.machina.util.text.StringUtils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;

public class BatteryTileEntity extends EnergyTileEntity implements INamedContainerProvider {

	public BatteryTileEntity() {
		super(TileEntityTypesInit.BATTERY.get(), new EnergyDefinition(1000000, 1000, 1000));
	}

	@Override
	public void tick() {
		recieveAll();
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player) {
		return new BatteryContainer(windowId, this);
	}

	@Override
	public ITextComponent getDisplayName() {
		return StringUtils.toComp("Battery");
	}

}