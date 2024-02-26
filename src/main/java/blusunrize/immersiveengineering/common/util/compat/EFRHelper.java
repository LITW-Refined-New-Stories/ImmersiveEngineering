package blusunrize.immersiveengineering.common.util.compat;

import blusunrize.immersiveengineering.common.IEContent;
import ganymedes01.etfuturum.api.DeepslateOreRegistry;
import ganymedes01.etfuturum.ModBlocks;

public class EFRHelper extends IECompatModule {

    @Override
    public void preInit() {
    }

    @Override
    public void init() {
        // Register deepslate ores as they get not automatically added via ETF for some reason
        DeepslateOreRegistry.addOre(IEContent.blockOres, ModBlocks.DEEPSLATE_COPPER_ORE.get());
        DeepslateOreRegistry.addOre(IEContent.blockOres, 1, ModBlocks.MODDED_DEEPSLATE_ORE.get(), 0);
        DeepslateOreRegistry.addOre(IEContent.blockOres, 2, ModBlocks.MODDED_DEEPSLATE_ORE.get(), 3);
        DeepslateOreRegistry.addOre(IEContent.blockOres, 3, ModBlocks.MODDED_DEEPSLATE_ORE.get(), 2);
        DeepslateOreRegistry.addOre(IEContent.blockOres, 4, ModBlocks.MODDED_DEEPSLATE_ORE.get(), 4);
    }

    @Override
    public void postInit() {
    }
}
