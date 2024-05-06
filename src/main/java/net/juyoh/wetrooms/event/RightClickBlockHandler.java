package net.juyoh.wetrooms.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.juyoh.wetrooms.WetRooms;
import net.juyoh.wetrooms.block.ModBlocks;
import net.juyoh.wetrooms.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RightClickBlockHandler implements UseBlockCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        if(!player.isSpectator() && !world.isClient()){
            HitResult lookingat = MinecraftClient.getInstance().crosshairTarget;
            if (lookingat != null) {
                BlockPos pos = ((BlockHitResult) lookingat).getBlockPos();
                //player.sendMessage(Text.literal(world.getBlockState(pos).toString()));
                if (world.getBlockState(pos).getBlock() == ModBlocks.RUBBER_LOG && player.getStackInHand(hand).isOf(Items.BUCKET)) {
                    player.setStackInHand(hand, new ItemStack(ModItems.LATEX_BUCKET));
                }
            }
        }

        return ActionResult.PASS;
    }
}
