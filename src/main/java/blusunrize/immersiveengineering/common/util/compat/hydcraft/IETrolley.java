package blusunrize.immersiveengineering.common.util.compat.hydcraft;

import java.util.ArrayList;

import k4unl.minecraft.Hydraulicraft.api.IHarvesterTrolley;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import blusunrize.immersiveengineering.common.IEContent;

public class IETrolley implements IHarvesterTrolley
{

	@Override
	public boolean canHarvest(World world, int x, int y, int z)
	{
		return world.getBlock(x, y, z).equals(IEContent.blockCrop) && world.getBlock(x, y-1, z).equals(IEContent.blockCrop);
	}

	@Override
	public boolean canPlant(World world, int x, int y, int z, ItemStack stack)
	{
		Block soil = world.getBlock(x, y-1, z);
		return (soil.canSustainPlant(world, x, y-1, z, ForgeDirection.UP, (IPlantable)IEContent.itemSeeds) && world.isAirBlock(x, y, z) && (soil.isFertile(world, x, y-1, z)));
	}

	@Override
	public Block getBlockForSeed(ItemStack stack)
	{
		return IEContent.blockCrop;
	}

	@Override
	public ArrayList<ItemStack> getHandlingSeeds()
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(IEContent.itemSeeds));
		return ret;
	}

	@Override
	public String getName()
	{
		return "ieCrop";
	}

	@Override
	public int getPlantHeight(World world, int x, int y, int z)
	{
		return 2;
	}

	static ResourceLocation texture = new ResourceLocation("immersiveengineering:textures/models/hcTrolley.png");
	@Override
	public ResourceLocation getTexture()
	{
		return texture;
	}
}