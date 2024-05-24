package net.juyoh.wetrooms.util;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class HomeData {
    public static void addHomePositionData(IEntityDataSaver player, BlockPos pos) {
        NbtCompound nbt = player.getPersistantData();
        IntArrayList PosArrayList = new IntArrayList();
        PosArrayList.add(pos.getX());
        PosArrayList.add(pos.getY());
        PosArrayList.add(pos.getZ());
        nbt.putIntArray("home_pos", PosArrayList);

    }

    public static BlockPos getHomePositionData(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistantData();

        return new BlockPos(nbt.getIntArray("home_pos")[0], nbt.getIntArray("home_pos")[1], nbt.getIntArray("home_pos")[2]);
    }
}
