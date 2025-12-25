package net.Traise.tfs.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class CuttingScreen extends AbstractContainerScreen<CuttingMenu> {
    private static final ResourceLocation texture = new ResourceLocation("tfs:textures/gui/cutting_gui.png");

    public CuttingMenu cuttingMenu;
    public Player player;

    public int map_Height;
    public int map_Width;
    public NonNullList<Boolean> map; // Добавляем поле для хранения ссылки на map

    public CuttingScreen(CuttingMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.player = inventory.player;
        this.cuttingMenu = container;
        this.map_Height = container.map_Height;
        this.map_Width = container.map_Width;
        this.map = container.getMap(); // Инициализируем поле ссылкой на map
    }

    @Override
    public void init() {
        this.topPos = (height - imageHeight) / 2;
        this.leftPos = (width - imageWidth) / 2;

        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);

        int x = this.leftPos;
        int y = this.topPos;
        guiGraphics.blit(texture, x, y, 0, 0, 176, 166);

        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        for (int Y = 0; Y < map_Height; Y++) {
            for (int X = 0; X < map_Width; X++) {
                if (map.get((map_Width * Y) + X)) {
                    guiGraphics.blit(texture, x + 14 + (15 * X), y + 5 + (15 * Y), 176, 0, 15, 15);
                }
            }
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        int X = (int) Math.floor((pMouseX - this.leftPos - 14) / 15);
        int Y = (int) Math.floor((pMouseY - this.topPos - 5) / 15);

        if (!(X < 0 || X > 4 || Y < 0 || Y > 4)) {
            if (map.get((map_Height * Y) + X)) {

                this.menu.clickMenuButton(this.minecraft.player, (map_Height * Y) + X);
                this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, (map_Height * Y) + X);
            }
        }

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        int X = (int) Math.floor((pMouseX - this.leftPos - 14) / 15);
        int Y = (int) Math.floor((pMouseY - this.topPos - 5) / 15);

        if (!(X < 0 || X > 4 || Y < 0 || Y > 4)) {
            if (map.get((map_Height * Y) + X)) {

                this.menu.clickMenuButton(this.minecraft.player, (map_Height * Y) + X);
                this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, (map_Height * Y) + X);
            }
        }

        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }
}