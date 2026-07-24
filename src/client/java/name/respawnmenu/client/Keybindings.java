package name.respawnmenu.client;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class Keybindings {
    public static final KeyMapping OPEN_SCREEN = new KeyMapping(
            "key.example.open_screen",
            GLFW.GLFW_KEY_R,
            KeyMapping.Category.MISC
    );
}