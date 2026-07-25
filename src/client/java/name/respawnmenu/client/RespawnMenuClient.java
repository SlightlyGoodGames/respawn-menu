package name.respawnmenu.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.network.chat.Component;

import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

public class RespawnMenuClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (Keybindings.OPEN_MENU.consumeClick()) {
				client.setScreenAndShow(new RespawnScreen(Component.empty()));
			}
		});
		KeyMappingHelper.registerKeyMapping(Keybindings.OPEN_MENU);
	}
}