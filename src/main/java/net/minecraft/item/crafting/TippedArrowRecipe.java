package net.minecraft.item.crafting;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TippedArrowRecipe extends SpecialRecipe {
   public TippedArrowRecipe(ResourceLocation idIn) {
      super(idIn);
   }

   public boolean matches(CraftingInventory inv, World worldIn) {
      if (inv.getWidth() == 3 && inv.getHeight() == 3) {
         for(int i = 0; i < inv.getWidth(); ++i) {
            for(int j = 0; j < inv.getHeight(); ++j) {
               ItemStack itemstack = inv.getStackInSlot(i + j * inv.getWidth());
               if (itemstack.isEmpty()) {
                  return false;
               }

               Item item = itemstack.getItem();
               if (i == 1 && j == 1) {
                  if (item != Items.LINGERING_POTION) {
                     return false;
                  }
               } else if (item != Items.ARROW) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public ItemStack getCraftingResult(CraftingInventory inv) {
      ItemStack itemstack = inv.getStackInSlot(1 + inv.getWidth());
      if (itemstack.getItem() != Items.LINGERING_POTION) {
         return ItemStack.EMPTY;
      } else {
         ItemStack itemstack1 = new ItemStack(Items.TIPPED_ARROW, 8);
         PotionUtils.addPotionToItemStack(itemstack1, PotionUtils.getPotionFromItem(itemstack));
         PotionUtils.appendEffects(itemstack1, PotionUtils.getFullEffectsFromItem(itemstack));
         return itemstack1;
      }
   }

   @OnlyIn(Dist.CLIENT)
   public boolean canFit(int width, int height) {
      return width >= 2 && height >= 2;
   }

   public IRecipeSerializer<?> getSerializer() {
      return IRecipeSerializer.CRAFTING_SPECIAL_TIPPEDARROW;
   }
}
