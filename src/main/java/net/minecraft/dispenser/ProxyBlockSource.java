package net.minecraft.dispenser;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class ProxyBlockSource implements IBlockSource {
   private final ServerWorld world;
   private final BlockPos pos;

   public ProxyBlockSource(ServerWorld world, BlockPos pos) {
      this.world = world;
      this.pos = pos;
   }

   public ServerWorld getWorld() {
      return this.world;
   }

   public double getX() {
      return (double)this.pos.getX() + 0.5D;
   }

   public double getY() {
      return (double)this.pos.getY() + 0.5D;
   }

   public double getZ() {
      return (double)this.pos.getZ() + 0.5D;
   }

   public BlockPos getBlockPos() {
      return this.pos;
   }

   public BlockState getBlockState() {
      return this.world.getBlockState(this.pos);
   }

   public <T extends TileEntity> T getBlockTileEntity() {
      return (T)this.world.getTileEntity(this.pos);
   }
}
