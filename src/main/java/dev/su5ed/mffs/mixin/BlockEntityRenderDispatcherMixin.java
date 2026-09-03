package dev.su5ed.mffs.mixin;

import dev.su5ed.mffs.blockentity.ForceFieldBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityRenderDispatcher.class)
public class BlockEntityRenderDispatcherMixin {
    @Inject(method = "tryExtractRenderState(Lnet/minecraft/world/level/block/entity/BlockEntity;FLnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;Lnet/minecraft/client/renderer/culling/Frustum;)Lnet/minecraft/client/renderer/blockentity/state/BlockEntityRenderState;", at = @At("HEAD"), cancellable = true)
    private static void tryExtractRenderStateMixin(BlockEntity blockEntity, float partialTicks, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress, @Nullable Frustum frustum, CallbackInfoReturnable<BlockEntityRenderState> cir) {
        if (blockEntity instanceof ForceFieldBlockEntity forceFieldBlockEntity) {
            if (forceFieldBlockEntity.getCamouflage() == null || !(forceFieldBlockEntity.getCamouflage().getBlock() instanceof EntityBlock)) {
                cir.setReturnValue(null);
            }
        }
    }
}
