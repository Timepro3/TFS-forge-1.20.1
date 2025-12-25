package net.Traise.tfs.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Traise.tfs.tfs;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class RevolverScreen extends AbstractContainerScreen<RevolverMenu> {
    private final int x, y, z;
    private final Player entity;
    Button button_ld;


    private static final ResourceLocation TEXTURE =
            new ResourceLocation(tfs.MOD_ID, "textures/gui/revolver_gui.png");

    public RevolverScreen(RevolverMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.x = pMenu.x;
        this.y = pMenu.y;
        this.z = pMenu.z;
        this.entity = pMenu.entity;

    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
       /* button_ld = Button.builder(Component.translatable(String.valueOf(TEXTURE)), e -> {
            if (true) {
                tfs.PACKET_HANDLER.sendToServer(new RevolverButtonMessage(0, x, y, z));
                RevolverButtonMessage.handleButtonAction(entity, 0, x, y, z);
            }
        }).bounds(this.leftPos + 72, this.topPos + 48, 35, 20).build();
        guistate.put("button:button_ld", button_ld);
        this.addRenderableWidget(button_ld);*/
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
