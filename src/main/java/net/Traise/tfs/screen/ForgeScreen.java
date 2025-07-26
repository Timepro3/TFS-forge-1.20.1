package net.Traise.tfs.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Traise.tfs.tfs;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ForgeScreen extends AbstractContainerScreen<ForgeMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(tfs.MOD_ID, "textures/gui/forge_gui.png");

    public ForgeScreen(ForgeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.presence()) {
            guiGraphics.blit(TEXTURE, x + 98, y + 31, 184, 0, 14, 8);
        }
        if (menu.pouringOut()) {
            guiGraphics.blit(TEXTURE, x + 134, y + 54, 176, 0, 8, 14);
        }
        if (menu.temp1() != -1) {
            guiGraphics.blit(TEXTURE, x + 80, y + 7, 216, menu.temp1() * 2, 14, 2);
        }
        if (menu.temp2() != -1) {
            guiGraphics.blit(TEXTURE, x + 98, y + 7, 216, menu.temp2() * 2, 14, 2);
        }
        if (menu.temp3() != -1) {
            guiGraphics.blit(TEXTURE, x + 116, y + 7, 216, menu.temp3() * 2, 14, 2);
        }
        guiGraphics.blit(TEXTURE, x + 151, y + 46 - menu.thermometer(), 198, 0, 12, 3);
        guiGraphics.blit(TEXTURE, x + 85, y + 77 - menu.fullness(), 176, 14, 40,  menu.fullness());

    }

    private int Ty = 0;

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        Ty = 8;
        Component lit = Component.literal("Неизвестно");
        if (menu.Kol() == 0){
            lit = Component.literal("");
        } else if (menu.Copper() >= 95) {
            lit = Component.literal("Медь");
        } else if (menu.Iron() >= 95) {
            lit = Component.literal("Железо");
        } else if (menu.Tin() >= 95) {
            lit = Component.literal("Олово");
        } else if (menu.Zinc() >= 95) {
            lit = Component.literal("Цинк");
        } else if (menu.Gold() >= 95) {
            lit = Component.literal("Золото");
        } else if (menu.Silver() >= 95) {
            lit = Component.literal("Серебро");
        } else if (menu.Copper() >= 85 && menu.Tin() >= 5) {
            lit = Component.literal("Бронза");
        } else if (menu.Copper() >= 65 && menu.Zinc() >= 25) {
            lit = Component.literal("Латунь");
        } else if (menu.Iron() >= 85 && menu.Carbon() > 5) {
            lit = Component.literal("Сталь");
        } else if (menu.Iron() >= 75 && menu.Carbon() > 15) {
            lit = Component.literal("Чугун");
        }

        guiGraphics.drawString(this.font, lit, 3, 5, -12829636, false);

        int h = 13;

        if (menu.Kol() == 0) {h = 5;}

        guiGraphics.drawString(this.font, Component.literal(menu.Kol() + "/" + menu.MKol() + "мл"), 3, h, -12829636, false);

        if (menu.thermometer() <= 0) {
            guiGraphics.drawString(this.font, Component.literal("§cхолодно"), 84, 2, -12829636, false);
        }

        if (menu.Copper() > 0) {
            guiGraphics.drawString(this.font, Component.literal("Mедь"), 3, 13 + Ty, -12829636, false);
            guiGraphics.drawString(this.font, Component.literal(menu.Copper() + "%"), 41, 13 + Ty, -12829636, false);
            Ty = Ty + 8;
        }
        if (menu.Iron() > 0) {
            guiGraphics.drawString(this.font, Component.literal("Железо"), 3, 13 + Ty, -12829636, false);
            guiGraphics.drawString(this.font, Component.literal(menu.Iron() + "%"), 41, 13 + Ty, -12829636, false);
            Ty = Ty + 8;
        }
        if (menu.Tin() > 0) {
            guiGraphics.drawString(this.font, Component.literal("Олово"), 3, 13 + Ty, -12829636, false);
            guiGraphics.drawString(this.font, Component.literal(menu.Tin() + "%"), 41, 13 + Ty, -12829636, false);
            Ty = Ty + 8;
        }
        if (menu.Zinc() > 0) {
            guiGraphics.drawString(this.font, Component.literal("Цинк"), 3, 13 + Ty, -12829636, false);
            guiGraphics.drawString(this.font, Component.literal(menu.Zinc() + "%"), 41, 13 + Ty, -12829636, false);
            Ty = Ty + 8;
        }
        if (menu.Gold() > 0) {
            guiGraphics.drawString(this.font, Component.literal("Золото"), 3, 13 + Ty, -12829636, false);
            guiGraphics.drawString(this.font, Component.literal(menu.Gold() + "%"), 41, 13 + Ty, -12829636, false);
            Ty = Ty + 8;
        }
        if (menu.Silver() > 0) {
            guiGraphics.drawString(this.font, Component.literal("Сереб"), 3, 13 + Ty, -12829636, false);
            guiGraphics.drawString(this.font, Component.literal(menu.Silver() + "%"), 41, 13 + Ty, -12829636, false);
            Ty = Ty + 8;
        }
        if (menu.Carbon() > 0) {
            guiGraphics.drawString(this.font, Component.literal("Уголь"), 3, 13 + Ty, -12829636, false);
            guiGraphics.drawString(this.font, Component.literal(menu.Carbon() + "%"), 41, 13 + Ty, -12829636, false);
            Ty = Ty + 8;
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
