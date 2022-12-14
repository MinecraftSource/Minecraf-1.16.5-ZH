package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.IServerPlayNetHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CClickWindowPacket implements IPacket<IServerPlayNetHandler> {
   private int windowId;
   private int slotId;
   private int packedClickData;
   private short actionNumber;
   private ItemStack clickedItem = ItemStack.EMPTY;
   private ClickType mode;

   public CClickWindowPacket() {
   }

   @OnlyIn(Dist.CLIENT)
   public CClickWindowPacket(int windowIdIn, int slotIdIn, int usedButtonIn, ClickType modeIn, ItemStack clickedItemIn, short actionNumberIn) {
      this.windowId = windowIdIn;
      this.slotId = slotIdIn;
      this.packedClickData = usedButtonIn;
      this.clickedItem = clickedItemIn.copy();
      this.actionNumber = actionNumberIn;
      this.mode = modeIn;
   }

   public void processPacket(IServerPlayNetHandler handler) {
      handler.processClickWindow(this);
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.windowId = buf.readByte();
      this.slotId = buf.readShort();
      this.packedClickData = buf.readByte();
      this.actionNumber = buf.readShort();
      this.mode = buf.readEnumValue(ClickType.class);
      this.clickedItem = buf.readItemStack();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeShort(this.slotId);
      buf.writeByte(this.packedClickData);
      buf.writeShort(this.actionNumber);
      buf.writeEnumValue(this.mode);
      buf.writeItemStack(this.clickedItem);
   }

   public int getWindowId() {
      return this.windowId;
   }

   public int getSlotId() {
      return this.slotId;
   }

   public int getUsedButton() {
      return this.packedClickData;
   }

   public short getActionNumber() {
      return this.actionNumber;
   }

   public ItemStack getClickedItem() {
      return this.clickedItem;
   }

   public ClickType getClickType() {
      return this.mode;
   }
}
