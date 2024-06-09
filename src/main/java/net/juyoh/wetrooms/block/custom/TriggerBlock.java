package net.juyoh.wetrooms.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;

public class TriggerBlock extends Block {
    public static final IntProperty VARIANT = Properties.LEVEL_1_8;
    public TriggerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(VARIANT, 1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(VARIANT);
    }
}
