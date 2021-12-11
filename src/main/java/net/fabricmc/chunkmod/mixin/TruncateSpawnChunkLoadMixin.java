package net.fabricmc.example.mixin;

import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.screen.world.EditWorldScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;

import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MinecraftServer.class)
public class TruncateSpawnChunkLoadMixin {
	private final int NUM_CHUNKS = 48;
	@ModifyConstant(method = "prepareWorlds", constant = @Constant(intValue = -192, ordinal=0))
	public int prepareWorldsMixin1(int val) {
		return -NUM_CHUNKS;
	}
	@ModifyConstant(method = "prepareWorlds", constant = @Constant(intValue = -192, ordinal=1))
	public int prepareWorldsMixin2(int val) {
		return -NUM_CHUNKS;
	}
	@ModifyConstant(method = "prepareWorlds", constant = @Constant(intValue = 192, ordinal=0))
	public int prepareWorldsMixin3(int val) {
		return NUM_CHUNKS;
	}
	@ModifyConstant(method = "prepareWorlds", constant = @Constant(intValue = 192, ordinal=1))
	public int prepareWorldsMixin4(int val) {
		return NUM_CHUNKS;
	}
}