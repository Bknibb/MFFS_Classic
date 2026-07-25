package dev.su5ed.mffs.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.su5ed.mffs.block.ForceFieldBlockImpl;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerExplosion.class)
public class ServerExplosionMixin {
    @WrapOperation(method = "getSeenPercent", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;clip(Lnet/minecraft/world/level/ClipContext;)Lnet/minecraft/world/phys/BlockHitResult;"))
    private static BlockHitResult wrapExplosionCollisionShape(Level level, ClipContext context, Operation<BlockHitResult> original) {
        return ScopedValue.where(ForceFieldBlockImpl.EXPLOSION_COLLISION, true).call(() -> original.call(level, context));
    }
}
