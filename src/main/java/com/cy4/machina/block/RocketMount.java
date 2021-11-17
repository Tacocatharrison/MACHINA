package com.cy4.machina.block;


import com.cy4.machina.block.properties.ActivationState;
import com.cy4.machina.block.properties.RelayPosState;
import com.cy4.machina.init.BlockInit;
import com.cy4.machina.init.ItemInit;
import com.cy4.machina.init.TileEntityTypesInit;
import com.cy4.machina.tile_entity.RocketMountTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class RocketMount extends Block {
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    
    public static final EnumProperty<RelayPosState> RELAY_POS_STATE = EnumProperty.create("relay_pos_state", RelayPosState.class, RelayPosState.N_A, RelayPosState.NORTH, 
    		RelayPosState.NORTHEAST, RelayPosState.EAST, RelayPosState.SOUTHEAST, RelayPosState.SOUTH, RelayPosState.SOUTHWEST, RelayPosState.WEST, RelayPosState.NORTHWEST, RelayPosState.CENTER);
    

    public RocketMount(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(ACTIVATED, Boolean.FALSE)
                .setValue(RELAY_POS_STATE, RelayPosState.N_A));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(ACTIVATED, RELAY_POS_STATE);
    }
    
    
    
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypesInit.ROCKET_MOUNT_TILE.create();
    }
    
    
    
    @Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) 
	{
    	
    	if (player.getItemInHand(handIn).getItem() == ItemInit.WRENCH) {
    		if (checkForAllRelayBlocks(worldIn, pos)) {
    			//player.displayClientMessage(new StringTextComponent("Their all RELAYS!!!!!"), true);
    			
    			TileEntity te = worldIn.getBlockEntity(pos);
    			if (te != null && te instanceof RocketMountTile) {
    				RocketMountTile rocketMountTile = (RocketMountTile) te; 
    				//rocketMountTile.setInitialRefreshTime(60);
    				player.displayClientMessage(new StringTextComponent("RefreshTime is " + rocketMountTile.getRefreshTime()), true);
    			}
    			
    			//setAllRelayBlockPositions(worldIn, pos);
    			
    			
    		} else {
    			whichRelaysAreMissingMessages(worldIn, pos, player);
    		}
    		
    		
        }
    	
		return ActionResultType.SUCCESS;
	}

    
    
    
    
    
    
    
    public void whichRelaysAreMissingMessages(World worldIn, BlockPos pos, PlayerEntity player) {
    	if (!checkForNorthRelayBlock(worldIn, pos)) {
			player.displayClientMessage(new StringTextComponent("Missing North Relay"), true);
		} else if (!checkForNorthEastRelayBlock(worldIn, pos)) {
			player.displayClientMessage(new StringTextComponent("Missing North East Relay"), true);
		} else if (!checkForEastRelayBlock(worldIn, pos)) {
			player.displayClientMessage(new StringTextComponent("Missing East Relay"), true);
		} else if (!checkForSouthEastRelayBlock(worldIn, pos)) {
			player.displayClientMessage(new StringTextComponent("Missing South East Relay"), true);
		} else if (!checkForSouthRelayBlock(worldIn, pos)) {
			player.displayClientMessage(new StringTextComponent("Missing South Relay"), true);
		} else if (!checkForSouthWestRelayBlock(worldIn, pos)) {
			player.displayClientMessage(new StringTextComponent("Missing South West Relay"), true);
		} else if (!checkForWestRelayBlock(worldIn, pos)) {
			player.displayClientMessage(new StringTextComponent("Missing West Relay"), true);
		} else if (!checkForNorthWestRelayBlock(worldIn, pos)) {
			player.displayClientMessage(new StringTextComponent("Missing North West Relay"), true);
		}
    }
    
    public boolean checkForAllRelayBlocks(World worldIn, BlockPos pos) {
    	boolean northRelayPresent = checkForNorthRelayBlock(worldIn, pos);
    	boolean eastRelayPresent = checkForEastRelayBlock(worldIn, pos);
    	boolean southRelayPresent = checkForSouthRelayBlock(worldIn, pos);
    	boolean westRelayPresent = checkForWestRelayBlock(worldIn, pos);
    	boolean northEastRelayPresent = checkForNorthEastRelayBlock(worldIn, pos);
    	boolean southEastRelayPresent = checkForSouthEastRelayBlock(worldIn, pos);    	
    	boolean southWestRelayPresent = checkForSouthWestRelayBlock(worldIn, pos);    	
    	boolean northWestRelayPresent = checkForNorthWestRelayBlock(worldIn, pos);
    	
    	if (northRelayPresent && eastRelayPresent && southRelayPresent && westRelayPresent && northEastRelayPresent && southEastRelayPresent && southWestRelayPresent && northWestRelayPresent) {
    		return true;
    	} else {
    		return false; 
    	}
    }
    
    public boolean checkForNorthRelayBlock(World worldIn, BlockPos pos) {
    	BlockPos northBlockPos = pos.north(7).above();
    	boolean northRelayPresent = worldIn.getBlockState(northBlockPos).getBlock() == BlockInit.PAD_SIZE_RELAY;
    	if (northRelayPresent) { return true; } else { return false; }
    }
    
    public boolean checkForNorthEastRelayBlock(World worldIn, BlockPos pos) {
    	BlockPos northBlockPos = pos.north(7).above();
    	BlockPos northEastBlockPos = northBlockPos.east(7);
    	boolean northEastRelayPresent = worldIn.getBlockState(northEastBlockPos).getBlock() == BlockInit.PAD_SIZE_RELAY;
    	if (northEastRelayPresent) { return true; } else { return false;  }
    }
    
    public boolean checkForEastRelayBlock(World worldIn, BlockPos pos) {
    	BlockPos eastBlockPos = pos.east(7).above();
    	boolean eastRelayPresent = worldIn.getBlockState(eastBlockPos).getBlock() == BlockInit.PAD_SIZE_RELAY;
    	if (eastRelayPresent) { return true; } else { return false; }
    }
    
    public boolean checkForSouthEastRelayBlock(World worldIn, BlockPos pos) {
    	BlockPos southBlockPos = pos.south(7).above();
    	BlockPos southEastBlockPos = southBlockPos.east(7);
    	boolean southEastRelayPresent = worldIn.getBlockState(southEastBlockPos).getBlock() == BlockInit.PAD_SIZE_RELAY;
    	if (southEastRelayPresent) { return true; } else { return false; }
    }
    
    public boolean checkForSouthRelayBlock(World worldIn, BlockPos pos) {
    	BlockPos southBlockPos = pos.south(7).above();
    	boolean southRelayPresent = worldIn.getBlockState(southBlockPos).getBlock() == BlockInit.PAD_SIZE_RELAY;
    	if (southRelayPresent) { return true; } else { return false; }
    }
    
    public boolean checkForSouthWestRelayBlock(World worldIn, BlockPos pos) {
    	BlockPos southBlockPos = pos.south(7).above();
    	BlockPos southWestBlockPos = southBlockPos.west(7); 	
    	boolean southWestRelayPresent = worldIn.getBlockState(southWestBlockPos).getBlock() == BlockInit.PAD_SIZE_RELAY;    	
    	if (southWestRelayPresent) { return true; } else { return false; }
    }
    
    public boolean checkForWestRelayBlock(World worldIn, BlockPos pos) {
    	BlockPos westBlockPos = pos.west(7).above();
    	boolean westRelayPresent = worldIn.getBlockState(westBlockPos).getBlock() == BlockInit.PAD_SIZE_RELAY;
    	if (westRelayPresent) { return true; } else { return false; }
    }
    
    public boolean checkForNorthWestRelayBlock(World worldIn, BlockPos pos) {
    	BlockPos northBlockPos = pos.north(7).above();
    	BlockPos northWestBlockPos = northBlockPos.west(7);
    	boolean northWestRelayPresent = worldIn.getBlockState(northWestBlockPos).getBlock() == BlockInit.PAD_SIZE_RELAY;
    	if (northWestRelayPresent) { return true; } else { return false; }
    }
    
    public void setAllRelayBlockPositions(World worldIn, BlockPos pos) {
    	BlockPos northBlockPos = pos.north(7).above();
    	BlockPos eastBlockPos = pos.east(7).above();
    	BlockPos southBlockPos = pos.south(7).above();
    	BlockPos westBlockPos = pos.west(7).above();
    	BlockPos northEastBlockPos = northBlockPos.east(7);
    	BlockPos southEastBlockPos = southBlockPos.east(7);
    	BlockPos southWestBlockPos = southBlockPos.west(7);
    	BlockPos northWestBlockPos = northBlockPos.west(7);
    	
    	BlockState northRelayBlockState = worldIn.getBlockState(northBlockPos).setValue(PadSizeRelay.RELAY_POS_STATE, RelayPosState.NORTH).setValue(PadSizeRelay.ACTIVATION_STATE, ActivationState.WAITING);
    	BlockState northEastBlockState = worldIn.getBlockState(northEastBlockPos).setValue(PadSizeRelay.RELAY_POS_STATE, RelayPosState.NORTHEAST).setValue(PadSizeRelay.ACTIVATION_STATE, ActivationState.WAITING);
    	BlockState eastBlockState = worldIn.getBlockState(eastBlockPos).setValue(PadSizeRelay.RELAY_POS_STATE, RelayPosState.EAST).setValue(PadSizeRelay.ACTIVATION_STATE, ActivationState.WAITING);
    	BlockState southEastBlockState = worldIn.getBlockState(southEastBlockPos).setValue(PadSizeRelay.RELAY_POS_STATE, RelayPosState.SOUTHEAST).setValue(PadSizeRelay.ACTIVATION_STATE, ActivationState.WAITING);
    	BlockState southBlockState = worldIn.getBlockState(southBlockPos).setValue(PadSizeRelay.RELAY_POS_STATE, RelayPosState.SOUTH).setValue(PadSizeRelay.ACTIVATION_STATE, ActivationState.WAITING);
    	BlockState southWestBlockState = worldIn.getBlockState(southWestBlockPos).setValue(PadSizeRelay.RELAY_POS_STATE, RelayPosState.SOUTHWEST).setValue(PadSizeRelay.ACTIVATION_STATE, ActivationState.WAITING);
    	BlockState westBlockState = worldIn.getBlockState(westBlockPos).setValue(PadSizeRelay.RELAY_POS_STATE, RelayPosState.WEST).setValue(PadSizeRelay.ACTIVATION_STATE, ActivationState.WAITING);
    	BlockState northWestBlockState = worldIn.getBlockState(northWestBlockPos).setValue(PadSizeRelay.RELAY_POS_STATE, RelayPosState.NORTHWEST).setValue(PadSizeRelay.ACTIVATION_STATE, ActivationState.WAITING);
    	
		worldIn.setBlock(northBlockPos, northRelayBlockState, 10);
		worldIn.setBlock(northEastBlockPos, northEastBlockState, 10);
		worldIn.setBlock(eastBlockPos, eastBlockState, 10);
		worldIn.setBlock(southEastBlockPos, southEastBlockState, 10);
		worldIn.setBlock(southBlockPos, southBlockState, 10);
		worldIn.setBlock(southWestBlockPos, southWestBlockState, 10);
		worldIn.setBlock(westBlockPos, westBlockState, 10);
		worldIn.setBlock(northWestBlockPos, northWestBlockState, 10);
    }
}
