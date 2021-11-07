package com.cy4.machina;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cy4.machina.api.capability.trait.CapabilityPlanetTrait;
import com.cy4.machina.config.MachinaConfig;
import com.cy4.machina.init.CommandInit;
import com.cy4.machina.starchart.pool.PlanetTraitPoolManager;
import com.cy4.machina.world.DynamicDimensionFactory;
import com.cy4.machina.world.DynamicDimensionHelper;
import com.cy4.machina.world.data.PlanetDimensionData;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(Machina.MOD_ID)
public class Machina {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "machina";
	public static final ResourceLocation MACHINA_ID = new ResourceLocation(MOD_ID, MOD_ID);

	public static final ItemGroup MACHINA_ITEM_GROUP = new ItemGroup(ItemGroup.TABS.length, "machinaItemGroup") {
		@Override
		public ItemStack makeIcon() {
			return ItemStack.EMPTY;
		}
	};

	public static PlanetTraitPoolManager TRAIT_POOL_MANAGER = new PlanetTraitPoolManager();

	public Machina() {
		GeckoLib.initialize();

		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		modBus.addListener(this::onCommonSetup);
		forgeBus.addListener(EventPriority.HIGH, this::onServerStart);
		forgeBus.addListener(EventPriority.HIGH, this::onRegisterCommands);

		ModLoadingContext.get().registerConfig(Type.COMMON, MachinaConfig.SPEC, "machina-common.toml");
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void onRegisterCommands(final RegisterCommandsEvent event) {
		CommandInit.registerCommands(event);
	}

	public void onCommonSetup(final FMLCommonSetupEvent event) {
		CapabilityPlanetTrait.register();
	}

	// Load all the worlds to ensure player spawn spots!
	@SuppressWarnings("resource")
	public void onServerStart(final FMLServerStartingEvent event) {
		PlanetDimensionData.getDefaultInstance(event.getServer()).dimensionIds.forEach(id -> {
			DynamicDimensionHelper.getOrCreateWorld(event.getServer(),
					RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(Machina.MOD_ID, id)),
					(server, key) -> DynamicDimensionFactory.createDimension(server, key));
		});
	}
}
