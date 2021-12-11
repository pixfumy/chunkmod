package net.fabricmc.example.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.PlayerWorldManager;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.world.level.LevelInfo;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IntegratedServer.class)
public class ServerTickReorderMixin {
	private int ticks = 0;
	@Inject(method = "setupWorld()V", at = @At(value = "HEAD")) 
	public void advanceTicks(CallbackInfo ci) {
		this.ticks++;
	}
	
	@Inject(method = "setupWorld()V", at = @At(value = "INVOKE", 
			target = "Lnet/minecraft/server/PlayerManager;method_10411(I)V"), cancellable=true)
	public void load3RD(CallbackInfo ci) {
		if (this.ticks <= 10) {
			IntegratedServer.getServer().getPlayerManager().method_10411(3);
			ci.cancel();
		}
	}
}