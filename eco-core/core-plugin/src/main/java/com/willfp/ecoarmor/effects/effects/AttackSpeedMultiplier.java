package com.willfp.ecoarmor.effects.effects;

import com.willfp.ecoarmor.effects.Effect;
import com.willfp.ecoarmor.sets.util.ArmorUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AttackSpeedMultiplier extends Effect<Double> {
    public AttackSpeedMultiplier() {
        super("attack-speed-multiplier", Double.class);
    }

    @Override
    protected void onEnable(@NotNull final Player player) {
        AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        assert maxHealth != null;

        Double multiplier = ArmorUtils.getEffectStrength(player, this);

        if (multiplier == null) {
            return;
        }

        AttributeModifier modifier = new AttributeModifier(this.getUuid(), "attack-speed-multiplier", 1 - multiplier, AttributeModifier.Operation.MULTIPLY_SCALAR_1);
        if (!maxHealth.getModifiers().contains(modifier)) {
            maxHealth.addModifier(modifier);
        }
    }

    @Override
    protected void onDisable(@NotNull final Player player) {
        AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        assert maxHealth != null;

        maxHealth.removeModifier(new AttributeModifier(this.getUuid(), "attack-speed-multiplier", 0, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
    }
}
