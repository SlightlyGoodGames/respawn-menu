package name.respawnmenu.client;

import name.respawnmenu.network.InstantRespawnPayload;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class RespawnScreen extends Screen {
    private final String RESPAWN_TEXT = "Would you like to respawn?";
    private final String YES_TEXT = "Yes";
    private final String NO_TEXT = "No";

    public RespawnScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int buttonWidth = 120;
        int buttonHeight = 20;
        int x = (this.width - buttonWidth) / 4;
        int y = (this.height - buttonHeight) * 4 / 5;

        this.addRenderableWidget(Button.builder(Component.literal(YES_TEXT), _ -> {
            net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.send(new InstantRespawnPayload());
            this.minecraft.setScreenAndShow(null);
        }).bounds(x, y, buttonWidth, buttonHeight).build());

        x = (this.width - buttonWidth) * 3 / 4;

        this.addRenderableWidget(Button.builder(Component.literal(NO_TEXT), _ -> {
            this.minecraft.setScreenAndShow(null);
        }).bounds(x, y, buttonWidth, buttonHeight).build());
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.text(this.font, RESPAWN_TEXT,getCentredX(RESPAWN_TEXT,2), height/5, 0xFFFFFFFF, true);
    }

    private int getCentredX(String str,int placeOnScreen) {
        return ((this.width - this.font.width(str)) / placeOnScreen);
    }
}