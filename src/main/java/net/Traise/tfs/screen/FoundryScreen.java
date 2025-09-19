package net.Traise.tfs.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Traise.tfs.tfs;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeInventoryListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;

public class FoundryScreen extends AbstractContainerScreen<FoundryMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(tfs.MOD_ID, "textures/gui/foundry_gui.png");
    private static final Component mb = Component.translatable("gui.tfs.mb");
    protected int topPos;
    protected int leftPos;

    protected int imageWidth = 176;
    protected int imageHeight = 202;

    private boolean scrolling = false;

    private int startSlot = 0;
    private int countSlot = menu.getSize();
    private int maxCountSlot = 5;

    private int minSlider;
    private int maxSlider;
    private int sliderPosition;  // Initial position of the slider

    public FoundryScreen(FoundryMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.topPos = (height - imageHeight) / 2;
        this.leftPos = (width - imageWidth) / 2;

        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = this.leftPos;
        int y = this.topPos;

        if (sliderPosition < topPos + 4) {
            sliderPosition = topPos + 4;
        }

        minSlider = topPos + 4;
        maxSlider = topPos + 33 + 4;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, 176, 202);

        renderProgressArrow(guiGraphics, x, y);

        guiGraphics.blit(TEXTURE, x + 160, sliderPosition, 176, 32, 12, 15);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        for (int i = 0; i < 3; i++) {
            if (menu.burned(i)) {
                guiGraphics.blit(TEXTURE, x + 30, y + 77 - menu.progress(i) + (i * 18), 216, 16 - menu.progress(i), 3, menu.progress(i));

            } else if (menu.r(i)){
                guiGraphics.blit(TEXTURE, x + 30, y + 61 + (i * 18), 219, 0, 3, 16);

            }
        }

        guiGraphics.blit(TEXTURE, x + 158, y + 85 - menu.heat(), 222, 0, 12, 3);

        guiGraphics.blit(TEXTURE, x + 70, y + 103 - menu.fullness(), 176, 0, 40,  menu.fullness());
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        countSlot = menu.getSize();
        guiGraphics.drawString(this.font, Component.literal("Количество: " + menu.getAlloyCount() + "/10000" + mb.getString()), 5, -13, -12829636, false);
        guiGraphics.drawString(this.font, Component.literal("Содержит: " + menu.getAlloyName()), 5, -5, -12829636, false);
        int T = 0;
        if (startSlot < 0) {
            startSlot = 0;
        }

        for (int i = startSlot; i < menu.getSize() && i < startSlot + 4; i++) {
            if (menu.hawFluid(i)) {
                // Show only if the position isn't out of range based on slider value
                guiGraphics.drawString(this.font, Component.literal(menu.getFluidStackInSlot(i) + ":" + String.format("%.2f", menu.getFluidAmount(i)) + mb.getString() + " (§5" + String.format("%.2f", menu.getPercent(i)) + "%§0)"), 5, 3 + (8 * T), -12829636, false);
                T += 1;

            }
        }

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        if (countSlot > maxCountSlot) {
            sliderPosition -= (int) pDelta * 1;

            if (sliderPosition > maxSlider) {
                sliderPosition = maxSlider;
            } else if (sliderPosition < minSlider) {
                sliderPosition = minSlider;
            }
            startSlot = fullness(sliderPosition - minSlider, 31, countSlot - maxCountSlot);

        }

        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    private boolean insideScrollbar(double mouseX, double mouseY) {
        return mouseX >= 392 && mouseX <= 403 && mouseY <= 129 && mouseY >= 83;
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (pButton == 0) {
            if (this.insideScrollbar(pMouseX, pMouseY)) {
                this.scrolling = true;
                return true;
            } else {
                this.scrolling = false;
            }
        }

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (countSlot > maxCountSlot) {
            if (button == 0) { // Adjust for the slider's size
                if (this.scrolling) {
                    sliderPosition = (int) mouseY - 6;

                    if (sliderPosition > maxSlider) {
                        sliderPosition = maxSlider;
                    } else if (sliderPosition < minSlider) {
                        sliderPosition = minSlider;
                    }

                    startSlot = fullness(sliderPosition - minSlider, 31, countSlot - maxCountSlot);

                }
            }
        }
        // You might need additional logic here to prevent overshooting or snapping to certain values
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    public int fullness(int progress, int maxProgress, int progressArrowSize) {
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress: 0;
    }
}

