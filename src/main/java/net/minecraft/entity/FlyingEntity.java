package net.minecraft.entity;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class FlyingEntity extends MobEntity {
   protected FlyingEntity(EntityType<? extends FlyingEntity> type, World worldIn) {
      super(type, worldIn);
   }

   public boolean onLivingFall(float distance, float damageMultiplier) {
      return false;
   }

   protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
   }

   public void travel(Vector3d travelVector) {
      if (this.isInWater()) {
         this.moveRelative(0.02F, travelVector);
         this.move(MoverType.SELF, this.getMotion());
         this.setMotion(this.getMotion().scale((double)0.8F));
      } else if (this.isInLava()) {
         this.moveRelative(0.02F, travelVector);
         this.move(MoverType.SELF, this.getMotion());
         this.setMotion(this.getMotion().scale(0.5D));
      } else {
         float f = 0.91F;
         if (this.onGround) {
            f = this.world.getBlockState(new BlockPos(this.getPosX(), this.getPosY() - 1.0D, this.getPosZ())).getBlock().getSlipperiness() * 0.91F;
         }

         float f1 = 0.16277137F / (f * f * f);
         f = 0.91F;
         if (this.onGround) {
            f = this.world.getBlockState(new BlockPos(this.getPosX(), this.getPosY() - 1.0D, this.getPosZ())).getBlock().getSlipperiness() * 0.91F;
         }

         this.moveRelative(this.onGround ? 0.1F * f1 : 0.02F, travelVector);
         this.move(MoverType.SELF, this.getMotion());
         this.setMotion(this.getMotion().scale((double)f));
      }

      this.func_233629_a_(this, false);
   }

   public boolean isOnLadder() {
      return false;
   }
}
