package net.minecraft.util;

import net.minecraft.util.math.vector.Orientation;

public enum Mirror {
   NONE(Orientation.IDENTITY),
   LEFT_RIGHT(Orientation.INVERT_Z),
   FRONT_BACK(Orientation.INVERT_X);

   private final Orientation orientation;

   private Mirror(Orientation orientation) {
      this.orientation = orientation;
   }

   public int mirrorRotation(int rotationIn, int rotationCount) {
      int i = rotationCount / 2;
      int j = rotationIn > i ? rotationIn - rotationCount : rotationIn;
      switch(this) {
      case FRONT_BACK:
         return (rotationCount - j) % rotationCount;
      case LEFT_RIGHT:
         return (i - j + rotationCount) % rotationCount;
      default:
         return rotationIn;
      }
   }

   public Rotation toRotation(Direction facing) {
      Direction.Axis direction$axis = facing.getAxis();
      return (this != LEFT_RIGHT || direction$axis != Direction.Axis.Z) && (this != FRONT_BACK || direction$axis != Direction.Axis.X) ? Rotation.NONE : Rotation.CLOCKWISE_180;
   }

   public Direction mirror(Direction facing) {
      if (this == FRONT_BACK && facing.getAxis() == Direction.Axis.X) {
         return facing.getOpposite();
      } else {
         return this == LEFT_RIGHT && facing.getAxis() == Direction.Axis.Z ? facing.getOpposite() : facing;
      }
   }

   public Orientation getOrientation() {
      return this.orientation;
   }
}
