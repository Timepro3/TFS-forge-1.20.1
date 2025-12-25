package net.Traise.tfs.recipe;

import net.Traise.tfs.tfs;
import net.Traise.tfs.tools.*;
import net.Traise.tfs.util.ModTags;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class ToolAssemblyRecipe extends CustomRecipe {
    public ToolAssemblyRecipe(ResourceLocation pId, CraftingBookCategory pCategory) {
        super(pId, pCategory);
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        boolean h = false, si = false, sr = false; int j = 0;
        for (int i = 0; i < pContainer.getContainerSize(); i ++) {
            if (pContainer.getItem(i).is(ModTags.Items.TOOL_PART)) {
                if (pContainer.getItem(i).is(ModTags.Items.TOOL_HEAD)) {
                    h = true;
                } else if (pContainer.getItem(i).is(ModTags.Items.TOOL_STICK)) {
                    si = true;
                } else if (pContainer.getItem(i).is(ModTags.Items.TOOL_STRING)) {
                    sr = true;
                } else return false;
                j ++;
            } else if (!pContainer.getItem(i).isEmpty()) {
                return false;
            }
        }
        return h && si && sr && j==3;
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ItemStack itemStack = ItemStack.EMPTY;
        for (int i = 0; i < pContainer.getContainerSize(); i ++) {
            if (pContainer.getItem(i).is(ModTags.Items.TOOL_HEAD)) {
                itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(tfs.MOD_ID, (pContainer.getItem(i).getItem().toString()).replace("_part", "")))).copy();
                break;
            }
        }

        if (itemStack.isEmpty()) {
            return itemStack;
        }

        NonNullList<TFSToolMaterial> mat = NonNullList.withSize(3, TFSToolMaterials.NONE.get());
        for (int i = 0; i < pContainer.getContainerSize(); i++) {
            if (pContainer.getItem(i).is(ModTags.Items.TOOL_PART)) {
                if (pContainer.getItem(i).getItem() instanceof TFSPartItem) {
                    if (pContainer.getItem(i).is(ModTags.Items.TOOL_HEAD)) {
                        mat.set(1, TFSPartItem.getMaterial(pContainer.getItem(i)));
                    } else if (pContainer.getItem(i).is(ModTags.Items.TOOL_STICK)) {
                        mat.set(0, TFSPartItem.getMaterial(pContainer.getItem(i)));
                    } else if (pContainer.getItem(i).is(ModTags.Items.TOOL_STRING)) {
                        mat.set(2, TFSPartItem.getMaterial(pContainer.getItem(i)));
                    }
                } else if (pContainer.getItem(i).is(Items.STICK)) {
                    mat.set(0, TFSToolMaterials.WOOD.get());
                } else {
                    if (pContainer.getItem(i).is(ModTags.Items.TOOL_HEAD)) {
                        mat.set(1, TFSRegistries.TOOL_MATERIAL.get().getValue(new ResourceLocation(tfs.MOD_ID, pContainer.getItem(i).getItem().toString())));
                    } else if (pContainer.getItem(i).is(ModTags.Items.TOOL_STICK)) {
                        mat.set(0, TFSRegistries.TOOL_MATERIAL.get().getValue(new ResourceLocation(tfs.MOD_ID, pContainer.getItem(i).getItem().toString())));
                    } else if (pContainer.getItem(i).is(ModTags.Items.TOOL_STRING)) {
                        mat.set(2, TFSRegistries.TOOL_MATERIAL.get().getValue(new ResourceLocation(tfs.MOD_ID, pContainer.getItem(i).getItem().toString())));
                    }
                }
            }
        }
        MaterialStorageHandler.save(itemStack, mat);

        return itemStack;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return TFSRecipes.TOOL_ASSEMBLY_SERIALIZER.get();
    }
}
