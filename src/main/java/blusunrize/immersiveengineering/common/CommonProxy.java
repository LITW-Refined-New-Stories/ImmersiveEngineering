package blusunrize.immersiveengineering.common;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mojang.authlib.GameProfile;

import blusunrize.immersiveengineering.common.blocks.metal.TileEntityArcFurnace;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityAssembler;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityBucketWheel;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityConveyorSorter;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityCrusher;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityFermenter;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntityRefinery;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntitySqueezer;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityBlastFurnace;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityCokeOven;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityModWorkbench;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenCrate;
import blusunrize.immersiveengineering.common.gui.ContainerArcFurnace;
import blusunrize.immersiveengineering.common.gui.ContainerAssembler;
import blusunrize.immersiveengineering.common.gui.ContainerBlastFurnace;
import blusunrize.immersiveengineering.common.gui.ContainerCokeOven;
import blusunrize.immersiveengineering.common.gui.ContainerCrate;
import blusunrize.immersiveengineering.common.gui.ContainerFermenter;
import blusunrize.immersiveengineering.common.gui.ContainerModWorkbench;
import blusunrize.immersiveengineering.common.gui.ContainerRefinery;
import blusunrize.immersiveengineering.common.gui.ContainerRevolver;
import blusunrize.immersiveengineering.common.gui.ContainerSorter;
import blusunrize.immersiveengineering.common.gui.ContainerSqueezer;
import blusunrize.immersiveengineering.common.gui.ContainerToolbox;
import blusunrize.immersiveengineering.common.items.ItemRevolver;
import blusunrize.immersiveengineering.common.items.ItemToolbox;
import blusunrize.immersiveengineering.common.util.Lib;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

    public void init() {}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (ID == Lib.GUIID_CokeOven && te instanceof TileEntityCokeOven)
            return new ContainerCokeOven(player.inventory, (TileEntityCokeOven) te);
        if (ID == Lib.GUIID_BlastFurnace && te instanceof TileEntityBlastFurnace)
            return new ContainerBlastFurnace(player.inventory, (TileEntityBlastFurnace) te);
        if (ID == Lib.GUIID_Revolver && player.getCurrentEquippedItem() != null
            && player.getCurrentEquippedItem()
                .getItem() instanceof ItemRevolver)
            return new ContainerRevolver(player.inventory, world);
        if (ID == Lib.GUIID_WoodenCrate && te instanceof TileEntityWoodenCrate)
            return new ContainerCrate(player.inventory, (TileEntityWoodenCrate) te);
        if (ID == Lib.GUIID_Squeezer && te instanceof TileEntitySqueezer)
            return new ContainerSqueezer(player.inventory, (TileEntitySqueezer) te);
        if (ID == Lib.GUIID_Fermenter && te instanceof TileEntityFermenter)
            return new ContainerFermenter(player.inventory, (TileEntityFermenter) te);
        if (ID == Lib.GUIID_Sorter && te instanceof TileEntityConveyorSorter)
            return new ContainerSorter(player.inventory, (TileEntityConveyorSorter) te);
        if (ID == Lib.GUIID_Refinery && te instanceof TileEntityRefinery)
            return new ContainerRefinery(player.inventory, (TileEntityRefinery) te);
        // if(ID==Lib.GUIID_Workbench && player.getCurrentEquippedItem()!=null &&
        // player.getCurrentEquippedItem().getItem() instanceof ItemDrill)
        // return new ContainerDrill(player.inventory, world);
        if (ID == Lib.GUIID_Workbench && te instanceof TileEntityModWorkbench)
            return new ContainerModWorkbench(player.inventory, (TileEntityModWorkbench) te);
        if (ID == Lib.GUIID_ArcFurnace && te instanceof TileEntityArcFurnace)
            return new ContainerArcFurnace(player.inventory, (TileEntityArcFurnace) te);
        if (ID == Lib.GUIID_Assembler && te instanceof TileEntityAssembler)
            return new ContainerAssembler(player.inventory, (TileEntityAssembler) te);
        if (ID == Lib.GUIID_Toolbox && player.getCurrentEquippedItem() != null
            && player.getCurrentEquippedItem()
                .getItem() instanceof ItemToolbox)
            return new ContainerToolbox(player.inventory, world);
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public void postInit() {}

    public void serverStarting() {}

    public void handleTileSound(String soundName, TileEntity tile, boolean tileActive, float volume, float pitch) {}

    public void stopTileSound(String soundName, TileEntity tile) {}

    public void spawnCrusherFX(TileEntityCrusher tile, ItemStack stack) {}

    public void spawnBucketWheelFX(TileEntityBucketWheel tile, ItemStack stack) {}

    public void spawnSparkFX(World world, double x, double y, double z, double mx, double my, double mz) {}

    public void spawnRedstoneFX(World world, double x, double y, double z, double mx, double my, double mz, float size,
        float r, float g, float b) {}

    public void draw3DBlockCauldron() {}

    public String[] splitStringOnWidth(String s, int w) {
        return new String[] { s };
    }

    public World getClientWorld() {
        return null;
    }

    public String getNameFromUUID(String uuid) {
        return MinecraftServer.getServer()
            .func_147130_as()
            .fillProfileProperties(
                new GameProfile(
                    UUID.fromString(uuid.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5")),
                    null),
                false)
            .getName();
    }
}
