package dev.su5ed.mffs.mixin;

import dev.su5ed.mffs.blockentity.ForceFieldBlockEntity;
import net.minecraft.client.renderer.chunk.SectionCompiler;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SectionCompiler.class)
public class SectionCompilerMixin {
    @Inject(method = "handleBlockEntity", at = @At("HEAD"), cancellable = true)
    private void handleBlockEntityMixin(SectionCompiler.Results results, BlockEntity blockEntity, CallbackInfo ci) {
        if (blockEntity instanceof ForceFieldBlockEntity forceFieldBlockEntity) {
            if (forceFieldBlockEntity.getCamouflage() == null || !(forceFieldBlockEntity.getCamouflage().getBlock() instanceof EntityBlock)) {
                ci.cancel();
            }
        }
    }
}
