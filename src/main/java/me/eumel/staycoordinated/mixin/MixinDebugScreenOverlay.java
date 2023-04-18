package me.eumel.staycoordinated.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Mixin(DebugScreenOverlay.class)
public class MixinDebugScreenOverlay {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "getGameInformation", at = @At("RETURN"), cancellable = true)
    private void stayCoordinated$onReturn(CallbackInfoReturnable<List<String>> cir) {
        if (minecraft.showOnlyReducedInfo()) {
            Entity cameraEntity = this.minecraft.getCameraEntity();
            if (cameraEntity == null) return;
            BlockPos blockPos = cameraEntity.blockPosition();
            ArrayList<String> list = new ArrayList<>(cir.getReturnValue());
            list.add(String.format(Locale.ROOT, "XYZ: %.3f / %.5f / %.3f", cameraEntity.getX(), cameraEntity.getY(), cameraEntity.getZ()));
            list.add(String.format(Locale.ROOT, "Block: %d %d %d", blockPos.getX(), blockPos.getY(), blockPos.getZ()));
            cir.setReturnValue(list);
        }
    }
}