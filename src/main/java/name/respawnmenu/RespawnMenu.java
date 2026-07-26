package name.respawnmenu;

import name.respawnmenu.network.InstantRespawnPayload;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import static net.minecraft.commands.Commands.literal;

public class RespawnMenu implements ModInitializer {
	public static final String MOD_ID = "respawn-menu";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		PayloadTypeRegistry.serverboundPlay().register(InstantRespawnPayload.TYPE, InstantRespawnPayload.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(InstantRespawnPayload.TYPE, (payload, context) -> {
			context.server().execute(() -> {
				ServerPlayer player = context.player();
                LOGGER.info("Respawning " + player.getName().getString() + "...");
				player.hurtServer(
						player.level(),
						player.level().damageSources().genericKill(),
						Float.MAX_VALUE
				);
            });
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(literal("respawn")
					.executes(context -> {
						ServerPlayer player = context.getSource().getPlayer();

						if(player==null){
							LOGGER.info("Could not respawn; player is null");
							return 0;
						}

						LOGGER.info("Respawning " + player.getName().getString() + "...");
						player.hurtServer(
								player.level(),
								player.level().damageSources().genericKill(),
								Float.MAX_VALUE
						);
						return 1;
					}));
		});


	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
