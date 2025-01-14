package com.machina.util.server;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.ForgeHooks;

public class ItemStackHelper {

	public static CompoundNBT saveAllItems(CompoundNBT pTag, List<ItemStack> pList, String extra) {
		ListNBT listnbt = new ListNBT();

		for (int i = 0; i < pList.size(); ++i) {
			ItemStack itemstack = pList.get(i);
			if (!itemstack.isEmpty()) {
				CompoundNBT compoundnbt = new CompoundNBT();
				compoundnbt.putByte("Slot" + extra, (byte) i);
				itemstack.save(compoundnbt);
				listnbt.add(compoundnbt);
			}
		}

		pTag.put("Items" + extra, listnbt);

		return pTag;
	}

	public static void loadAllItems(CompoundNBT pTag, List<ItemStack> pList, String extra) {
		ListNBT listnbt = pTag.getList("Items" + extra, 10);

		for (int i = 0; i < listnbt.size(); ++i) {
			CompoundNBT compoundnbt = listnbt.getCompound(i);
			int j = compoundnbt.getByte("Slot" + extra) & 255;
			if (j >= 0 && j < pList.size()) {
				pList.set(j, ItemStack.of(compoundnbt));
			}
		}
	}

	public static int burnTime(ItemStack stack) {
		return ForgeHooks.getBurnTime(stack);
	}

	public static boolean isFuel(ItemStack stack) {
		return burnTime(stack) > 0;
	}

	public static boolean isBucket(ItemStack stack) {
		return stack.getItem() == Items.BUCKET;
	}
}
