package net.juyoh.wetrooms.sound;

import net.juyoh.wetrooms.WetRooms;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModSounds {


    public static final SoundEvent WETROOMS_TILES_STEP = registerSoundEvent("wetrooms_tiles_step");

    public static final BlockSoundGroup WETROOMS_TILE_SOUNDS = new BlockSoundGroup(2f, 1f, SoundEvents.BLOCK_METAL_BREAK, ModSounds.WETROOMS_TILES_STEP, SoundEvents.BLOCK_METAL_PLACE, SoundEvents.BLOCK_METAL_HIT, ModSounds.WETROOMS_TILES_STEP);
    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(WetRooms.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static void registerSounds() {
        WetRooms.LOGGER.info("Registering sounds for " + WetRooms.MOD_ID);
    }
}
