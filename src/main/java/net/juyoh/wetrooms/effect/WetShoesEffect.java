package net.juyoh.wetrooms.effect;

import net.juyoh.wetrooms.WetRoomsClient;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.text.Text;

import java.util.Iterator;
import java.util.Map;

import static net.juyoh.wetrooms.WetRooms.WETSHOES;


public class WetShoesEffect extends StatusEffect {
    public WetShoesEffect() {
        super(StatusEffectCategory.NEUTRAL, // whether beneficial or harmful for entities
                0x29B6F6); // color in RGB;
    }

    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity.getWorld().isClient()) {
            //Wet camera shader on

        }
        entity.sendMessage(Text.literal("you moist"));

    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if(entity.getWorld().isClient()) {
            //Wet camera shader off

        }
        entity.sendMessage(Text.literal("you dry now"));

    }
}