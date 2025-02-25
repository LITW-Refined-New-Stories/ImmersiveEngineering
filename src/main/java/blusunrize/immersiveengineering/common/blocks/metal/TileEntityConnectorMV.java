package blusunrize.immersiveengineering.common.blocks.metal;

import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

import blusunrize.immersiveengineering.api.energy.IImmersiveConnectable;
import blusunrize.immersiveengineering.api.energy.ImmersiveNetHandler.Connection;
import blusunrize.immersiveengineering.api.energy.WireType;

public class TileEntityConnectorMV extends TileEntityConnectorLV {

    @Override
    protected boolean canTakeMV() {
        return true;
    }

    @Override
    protected boolean canTakeLV() {
        return false;
    }

    @Override
    public Vec3 getRaytraceOffset(IImmersiveConnectable link) {
        ForgeDirection fd = ForgeDirection.getOrientation(facing)
            .getOpposite();
        return Vec3.createVectorHelper(.5 + fd.offsetX * .125, .5 + fd.offsetY * .125, .5 + fd.offsetZ * .125);
    }

    @Override
    public Vec3 getConnectionOffset(Connection con) {
        ForgeDirection fd = ForgeDirection.getOrientation(facing)
            .getOpposite();
        double conRadius = con.cableType.getRenderDiameter() / 2;
        return Vec3.createVectorHelper(
            .5 + fd.offsetX * (.0625 - conRadius),
            .5 + fd.offsetY * (.0625 - conRadius),
            .5 + fd.offsetZ * (.0625 - conRadius));
    }

    @Override
    int getRenderRadiusIncrease() {
        return WireType.ELECTRUM.getMaxLength();
    }

    @Override
    public int getMaxInput() {
        return connectorInputValues[1];
    }

    @Override
    public int getMaxOutput() {
        return connectorInputValues[1];
    }
}
