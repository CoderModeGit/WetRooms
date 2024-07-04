package net.juyoh.wetrooms.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class CutsceneP2Effect extends StatusEffect {
    public CutsceneP2Effect() {
        super(StatusEffectCategory.NEUTRAL, 0x29B6F6);
    }

    @Override
    public void applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.setHeadYaw(pLivingEntity.getYaw() - 1);

        super.applyUpdateEffect(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }
}