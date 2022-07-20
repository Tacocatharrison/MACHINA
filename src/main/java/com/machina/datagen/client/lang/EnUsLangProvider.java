package com.machina.datagen.client.lang;

import com.machina.registration.init.BlockInit;
import com.machina.registration.init.FluidInit;
import com.machina.registration.init.ItemInit;
import com.machina.registration.init.AttributeInit;
import com.machina.registration.init.TraitInit;

import net.minecraft.data.DataGenerator;

public class EnUsLangProvider extends BaseLangProvider {

	public EnUsLangProvider(DataGenerator gen) {
		super(gen, "en_us");
	}

	@Override
	protected void addTranslations() {

		// Blocks
		add(BlockInit.SHIP_CONSOLE.get(), "Ship Console");
		add(BlockInit.ATMOSPHERIC_SEPARATOR.get(), "Atmospheric Separator");
		add(BlockInit.CARGO_CRATE.get(), "Cargo Crate");
		add(BlockInit.COMPONENT_ANALYZER.get(), "Component Analyzer");
		add(BlockInit.PUZZLE_BLOCK.get(), "Puzzle Block");
		add(BlockInit.PRESSURIZED_CHAMBER.get(), "Pressurized Chamber");
		add(BlockInit.CABLE.get(), "Cable");
		add(BlockInit.TANK.get(), "Tank");
		add(BlockInit.BATTERY.get(), "Battery");
		add(BlockInit.CREATIVE_BATTERY.get(), "Creative Battery");
		add(BlockInit.STEEL_BLOCK.get(), "Steel Block");
		add(BlockInit.ALUMINUM_BLOCK.get(), "Aluminum Block");
		add(BlockInit.ALUMINUM_ORE.get(), "Aluminum Ore");
		add(BlockInit.STEEL_CHASSIS.get(), "Steel Chassis");
		add(BlockInit.IRON_CHASSIS.get(), "Iron Chassis");
		add(BlockInit.ALIEN_STONE.get(), "Alien Stone");
		add(BlockInit.ALIEN_STONE_SLAB.get(), "Alien Stone Slab");
		add(BlockInit.ALIEN_STONE_STAIRS.get(), "Alien Stone Stairs");
		add(BlockInit.TWILIGHT_DIRT.get(), "Twilight Dirt");
		add(BlockInit.TWILIGHT_DIRT_SLAB.get(), "Twilight Dirt Slab");
		add(BlockInit.TWILIGHT_DIRT_STAIRS.get(), "Twilight Stairs");
		add(BlockInit.WASTELAND_DIRT.get(), "Wasteland Dirt");
		add(BlockInit.WASTELAND_DIRT_SLAB.get(), "Wasteland Dirt Slab");
		add(BlockInit.WASTELAND_DIRT_STAIRS.get(), "Wasteland Dirt Stairs");
		add(BlockInit.WASTELAND_SAND.get(), "Wasteland Sand");
		add(BlockInit.WASTELAND_SANDSTONE.get(), "Wasteland Sandstone");
		add(BlockInit.WASTELAND_SANDSTONE_SLAB.get(), "Wasteland Sandstone Slab");
		add(BlockInit.WASTELAND_SANDSTONE_STAIRS.get(), "Wasteland Sandstone Stairs");
		add(BlockInit.WASTELAND_SANDSTONE_WALL.get(), "Wasteland Sandstone Wall");
		add(BlockInit.REINFORCED_TILE.get(), "Reinforced Tile");

		// Items
		add(ItemInit.WRENCH.get(), "Wrench");
		add(ItemInit.SHIP_COMPONENT.get(), "Ship Component");
		add(ItemInit.SCANNER.get(), "Scanner");
		add(ItemInit.REINFORCED_STICK.get(), "Reinforced Stick");
		add(ItemInit.STEEL_INGOT.get(), "Steel Ingot");
		add(ItemInit.STEEL_NUGGET.get(), "Steel Nugget");
		add(ItemInit.ALUMINUM_INGOT.get(), "Aluminum Ingot");
		add(ItemInit.ALUMINUM_NUGGET.get(), "Aluminum Nugget");
		add(ItemInit.IRON_CATALYST.get(), "Iron Catalyst");
		add(ItemInit.PROCESSOR.get(), "Processor");
		add(ItemInit.SILICON.get(), "Silicon");
		add(ItemInit.TRANSISTOR.get(), "Transistor");

		// Fluids
		add(FluidInit.OXYGEN, "Oxygen", "Bucket");
		add(FluidInit.NITROGEN, "Nitrogen", "Bucket");
		add(FluidInit.AMMONIA, "Ammonia", "Bucket");
		add(FluidInit.CARBON_DIOXIDE, "Carbon Dioxide", "Bucket");
		add(FluidInit.HYDROGEN, "Hydrogen", "Bucket");
		add(FluidInit.LIQUID_HYDROGEN, "Liquid Hydrogen", "Bucket");
		add(FluidInit.LIQUID_AMMONIA, "Liquid Ammonia", "Bucket");

		// Traits
		add(TraitInit.WATER_WORLD, "Water World");
		add(TraitInit.CONTINENTAL, "Continental");
		add(TraitInit.MOUNTAINOUS, "Mountainous");
		add(TraitInit.HILLY, "Hilly");
		add(TraitInit.FLAT, "Flat");
		add(TraitInit.LAKES, "Lakes");
		add(TraitInit.FROZEN, "Frozen");
		add(TraitInit.ISLANDS, "Islands");

		// Attributes
		add(AttributeInit.ATMOSPHERIC_PRESSURE, "Atmospheric Pressure");
		add(AttributeInit.BASE_BLOCKS, "Blocks");
		add(AttributeInit.CAVE_CHANCE, "Cave Density");
		add(AttributeInit.CAVE_LENGTH, "Cave Length");
		add(AttributeInit.CAVE_THICKNESS, "Cave Thickness");
		add(AttributeInit.CAVES_EXIST, "Caves");
		add(AttributeInit.FLUID_BLOCKS, "Fluids");
		add(AttributeInit.FOG_DENSITY, "Fog Density");
		add(AttributeInit.GRAVITY, "Gravity");
		add(AttributeInit.ISLAND_DENSITY, "Island Density");
		add(AttributeInit.PALETTE, "Color Palettte");
		add(AttributeInit.PLANET_NAME, "Planet Name");
		add(AttributeInit.PLANET_ICON, "Planet Icon");
		add(AttributeInit.SURF_BLOCKS, "Surface Blocks");
		add(AttributeInit.TEMPERATURE, "Temperature");

		// Item Groups
		addItemGroup("machinaItemGroup", "Machina");

		// Commands
		addCommandFeedback("planet_traits.add_trait.success", "Trait added!");
		addCommandFeedback("planet_traits.add_trait.duplicate", "This planet already has the trait %s!");
		addCommandFeedback("planet_traits.remove_trait.success", "Trait removed!");
		addCommandFeedback("planet_traits.remove_trait.not_existing", "This planet does not have the trait %s!");
		addCommandFeedback("planet_traits.list_traits.success", "This planet has the following traits: \n§6%s");
		addCommandFeedback("planet_traits.list_traits.no_traits", "This planet has no traits!");
		addCommandFeedback("planet_traits.not_planet", "This dimension is not a planet!");
		addCommandArgumentFeedback("planet_trait.invalid", "Invalid Planet Trait: \u00A76%s");

		// Ship Component
		addShipComponent("unidentified", "Unidentified");
		addShipComponent("reactor", "Reactor");
		addShipComponent("core", "Core");
		addShipComponent("thrusters", "Thrusters");
		addShipComponent("shields", "Shields");
		addShipComponent("life_support", "Life Support");

		// GUI
		addScreen("component_analyzer", "insert", "Insert Component");

		addScreen("cargo_crate", "open", "Right Click to Loot");

		addScreen("starchart", "noselect", "Please select a planet.");

		addScreen("scanner", "title", "Scanner");
		addScreen("scanner", "tab0", "1. Planet Traits");
		addScreen("scanner", "tab1", "2. Planet Info");
		addScreen("scanner", "tab2", "3. Cave Info");
		addScreen("scanner", "tab3", "4. Atmosphere Composition");
		addScreen("scanner", "location", "Location: ");
		addScreen("scanner", "nodata", "Data Unavailable.");
		addScreen("scanner", "nocave", "There are no caves on this planet!");

		addScreen("ship_console", "missing", "Missing Items");
		addScreen("ship_console", "obstructed", "Obstructed");
		addScreen("ship_console", "craft_ready", "Craft Ready");
		addScreen("ship_console", "crafting", "Crafting...");
		addScreen("ship_console", "craft", "Craft");
		addScreen("ship_console", "stage", "Stage");
		addScreen("ship_console", "await", "Awaiting Launch");

		addScreen("atmospheric_seperator", "producing", "Producing: ");
		addScreen("atmospheric_seperator", "no", "Not Producing ");
		addScreen("atmospheric_seperator", "not_enough", "Fluid Too Thin");

		addScreen("tank", "stored", "Stored: ");
		addScreen("tank", "none", "None ");
		
		addScreen("pressurized_chamber", "clear", "Clear");
		addScreen("pressurized_chamber", "start", "Start");
		addScreen("pressurized_chamber", "pause", "Pause");
		addScreen("pressurized_chamber", "waiting", "Awaiting input...");
		addScreen("pressurized_chamber", "ready", "Clear to Craft");
		addScreen("pressurized_chamber", "crafting", "In Progress...");
		addScreen("pressurized_chamber", "recipe", "Recipe Found: ");
		addScreen("pressurized_chamber", "no_recipe", "No Recipe Found");
		addScreen("pressurized_chamber", "extract", "Use Fluid Hopper To Extract");

		// Terminal
		addTerminalCommand("clear", "Clears the console.");

		addTerminalCommand("help", "Shows a list of commands.");
		addTerminalFeedback("help", "prompt", "Type 'help' to see a list of commands.");
		addTerminalFeedback("help", "list", "The following is a list of commands:");
		addTerminalFeedback("help", "unrecognised", "Unrecognised command: ");

		addTerminalCommand("neofetch", "Displays info about the OS.");
		addTerminalFeedback("neofetch", "mach_os", "OS: MachOS CyberSec Edition");
		addTerminalFeedback("neofetch", "mach_cpu", "CPU: SM-908172U @ 64.0 MHz");
		addTerminalFeedback("neofetch", "mach_status", "Status: LISTENING");
		addTerminalFeedback("neofetch", "star_os", "OS: StarOS - Your Journey Awaits.");
		addTerminalFeedback("neofetch", "star_cpu", "CPU: LC-00012S @ 1.7 GHz");
		addTerminalFeedback("neofetch", "star_status", "Status: IDLE");

		addTerminalCommand("unlock", "Lifts cargo security.");
		addTerminalFeedback("unlock", "already_complete", "Cargo security already lifted.");
		addTerminalFeedback("unlock", "verification_needed", "Security verification needed.");
		addTerminalFeedback("unlock", "translate", "Translate: ");
		addTerminalFeedback("unlock", "incorrect", "Incorrect.");
		addTerminalFeedback("unlock", "permission_granted", "Permission granted.");

		addTerminalCommand("destination", "Set the destination.");
		addTerminalFeedback("destination", "loading", "Initializing Starchart...");
		addTerminalFeedback("destination", "set", "Destination set: ");

		addTerminalCommand("fuel", "Check fuel status.");
		addTerminalFeedback("fuel", "stored", "Fuel stored: ");

		addTerminalCommand("refuel", "Refuel the ship.");
		addTerminalFeedback("refuel", "info", "Place fuel barrel adjacent to the console.");
		addTerminalFeedback("refuel", "await", "Press ENTER to confirm.");
		addTerminalFeedback("refuel", "progress", "Refuelling...");
		addTerminalFeedback("refuel", "complete", "Refuel complete!");
		addTerminalFeedback("refuel", "gain", "Gained: ");
	}
}
