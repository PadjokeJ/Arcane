package dev.padjokej.mixin;

import com.mojang.blaze3d.resource.CrossFrameResourcePool;
import dev.padjokej.Arcane;
import dev.padjokej.registry.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.PostChain;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class RenderShimmerShaderMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    @Final
    private CrossFrameResourcePool resourcePool;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", ordinal = 0))
    private void injected(CallbackInfo ci) {
        if (minecraft.player != null && minecraft.player.hasEffect(ModEffects.SHIMMER)) {
            PostChain postChain = minecraft.getShaderManager().getPostChain(Arcane.id("shimmer"), LevelTargetBundle.MAIN_TARGETS);
            if (postChain != null) {
                postChain.process(minecraft.getMainRenderTarget(), resourcePool);
            }
        }
    }
}
