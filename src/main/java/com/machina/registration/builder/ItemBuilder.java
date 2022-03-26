package com.machina.registration.builder;

import com.machina.registration.Registration;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.util.NonNullFunction;

public class ItemBuilder<T extends Item> implements IBuilder<Item, T> {

	private final NonNullFunction<Item.Properties, T> factory;
	private ItemGroup tab = ItemGroup.TAB_MISC;
	
	protected ItemBuilder(NonNullFunction<Item.Properties, T> factory) {
		this.factory = factory;
	}
	
	public static Item basicItem() {
		return new ItemBuilder<>(Item::new).tab(Registration.MACHINA_ITEM_GROUP).build();
	}
	
	public static <T extends Item> T basicItem(NonNullFunction<Item.Properties, T> factory) {
		return new ItemBuilder<>(factory).tab(Registration.MACHINA_ITEM_GROUP).build();
	}
	
	public static <T extends Item> ItemBuilder<T> create(NonNullFunction<Item.Properties, T> factory) {
		return new ItemBuilder<>(factory);
	}
	
	@Override
	public T build() {
		return factory.apply(getProperties());
	}
	
	public ItemBuilder<T> tab(ItemGroup tab) {
		this.tab = tab;
		return this;
	}
	
	public Properties getProperties() {
		return new Properties().tab(tab);
	}

}