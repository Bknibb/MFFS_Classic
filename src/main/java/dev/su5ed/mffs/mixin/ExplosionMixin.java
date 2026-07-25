package dev.su5ed.mffs.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.su5ed.mffs.block.ForceFieldBlockImpl;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Explosion.class)
public class ExplosionMixin {
    @WrapOperation(method = "getSeenPercent", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;clip(Lnet/minecraft/world/level/ClipContext;)Lnet/minecraft/world/phys/BlockHitResult;"))
    private static BlockHitResult wrapExplosionCollisionShape(Level level, ClipContext context, Operation<BlockHitResult> original) {
        ForceFieldBlockImpl.EXPLOSION_COLLISION.set(true);
        BlockHitResult result = original.call(level, context);
        ForceFieldBlockImpl.EXPLOSION_COLLISION.set(false);
        return result;
    }
}
