package net.juyoh.wetrooms.block.custom;

import net.juyoh.wetrooms.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MarbleBlock extends Block {
    public static final BooleanProperty UP = Properties.UP;
    public static final BooleanProperty DOWN = Properties.DOWN;
    public static final BooleanProperty NORTH = Properties.NORTH;
    public static final BooleanProperty SOUTH = Properties.SOUTH;
    public static final BooleanProperty EAST = Properties.EAST;
    public static final BooleanProperty WEST = Properties.WEST;

    public MarbleBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(UP,Boolean.FALSE).with(DOWN,Boolean.FALSE).with(NORTH,Boolean.FALSE).with(SOUTH,Boolean.FALSE).with(EAST,Boolean.FALSE).with(WEST,Boolean.FALSE));
    }




    private boolean shouldConnectTo(BlockState state) {
        Block block = state.getBlock();
        return block.getDefaultState() == ModBlocks.MARBLE_BLOCK.getDefaultState() && block != Blocks.AIR;
    }
    private void updateState(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);

        if (shouldConnectTo(world.getBlockState(pos.up(1)))) {
            world.setBlockState(pos, state.with(UP, Boolean.TRUE));
        }
        if (shouldConnectTo(world.getBlockState(pos.down(1)))) {
            world.setBlockState(pos, state.with(DOWN, Boolean.TRUE));
        }
        if (shouldConnectTo(world.getBlockState(pos.north(1)))) {
            world.setBlockState(pos, state.with(NORTH, Boolean.TRUE));
        }
        if (shouldConnectTo(world.getBlockState(pos.south(1)))) {
            world.setBlockState(pos, state.with(SOUTH, Boolean.TRUE));
        }
        if (shouldConnectTo(world.getBlockState(pos.east(1)))) {
            world.setBlockState(pos, state.with(EAST, Boolean.TRUE));
        }
        if (shouldConnectTo(world.getBlockState(pos.west(1)))) {
            world.setBlockState(pos, state.with(WEST, Boolean.TRUE));
        }
    }
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        updateState(world, pos);
    }
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        updateState(world, pos);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, SOUTH, EAST, WEST);
    }
}
