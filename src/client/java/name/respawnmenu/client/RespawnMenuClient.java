package name.respawnmenu.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.network.chat.Component;

public class RespawnMenuClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (Keybindings.OPEN_SCREEN.consumeClick()) {
				client.setScreenAndShow(new RespawnScreen(Component.empty()));
			}
		});
	}
}