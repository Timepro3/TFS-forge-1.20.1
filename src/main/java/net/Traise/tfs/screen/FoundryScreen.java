package net.Traise.tfs.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Traise.tfs.tfs;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FoundryScreen extends AbstractContainerScreen<FoundryMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(tfs.MOD_ID, "textures/gui/foundry_gui.png");

    private static final Component mb = Component.translatable("gui.tfs.mb");

    public FoundryScreen(FoundryMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - 176) / 2;
        int y = (height - 202) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, 176, 202);

        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        for (int i = 0; i < 3; i++) {
            guiGraphics.blit(TEXTURE, x + 30, y + 77 - menu.progress(i) + (i * 18), 216, 16 - menu.progress(i), 3,  menu.progress(i));
        }

        if (menu.burned()) {
            guiGraphics.blit(TEXTURE, x + 161, y + 60, 219, 0, 7, 7);
        }

        guiGraphics.blit(TEXTURE, x + 70, y + 103 - menu.fullness(), 176, 0, 40,  menu.fullness());
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, Component.literal( "Количество: " + menu.getAlloyCount() + "/10000" + mb.getString()), 5, -13, -12829636, false);
        guiGraphics.drawString(this.font, Component.literal("Содержит: " + menu.getAlloyName()), 5, -5, -12829636, false);

        for (int i = 0; i < menu.getSize(); i++) {
            if (menu.hawFluid(i)) {
                guiGraphics.drawString(this.font, Component.literal(menu.getFluidStackInSlot(i) + ":" + menu.getFluidAmount(i) + mb.getString() + " (§5" + menu.getPercent(i) + "%§0)"), 5, 3 + (8 * i), -12829636, false);
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
