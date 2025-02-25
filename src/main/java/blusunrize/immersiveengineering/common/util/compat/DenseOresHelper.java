package blusunrize.immersiveengineering.common.util.compat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.item.ItemStack;

import blusunrize.immersiveengineering.api.crafting.ArcFurnaceRecipe;
import blusunrize.immersiveengineering.api.crafting.CrusherRecipe;
import blusunrize.immersiveengineering.common.util.IELogger;
import blusunrize.immersiveengineering.common.util.Utils;

public class DenseOresHelper extends IECompatModule {

    @Override
    public void preInit() {}

    @Override
    public void init() {}

    @Override
    public void postInit() {
        HashMap map = null;
        Field f_baseOreDictionary = null;
        Field f_oreDictionary = null;
        try {
            Class c_DenseOresRegistry = Class.forName("com.rwtema.denseores.DenseOresRegistry");
            Class c_DenseOre = Class.forName("com.rwtema.denseores.DenseOre");
            f_baseOreDictionary = c_DenseOre.getField("baseOreDictionary");
            f_oreDictionary = c_DenseOre.getField("oreDictionary");
            map = (HashMap) c_DenseOresRegistry.getField("ores")
                .get(null);
        } catch (Exception e) {}

        if (map != null && f_baseOreDictionary != null && f_oreDictionary != null) {
            ArrayList<CrusherRecipe> crushRecipes = new ArrayList<CrusherRecipe>();
            ArrayList<ArcFurnaceRecipe> arcRecipes = new ArrayList<ArcFurnaceRecipe>();
            for (Object o : map.values()) {
                try {
                    String baseOre = (String) f_baseOreDictionary.get(o);
                    String denseOre = (String) f_oreDictionary.get(o);

                    boolean c = false;
                    for (CrusherRecipe recipe : CrusherRecipe.recipeList)
                        if (recipe.oreInputString != null && ((String) recipe.oreInputString).equals(baseOre)) {
                            ItemStack out = Utils.copyStackWithAmount(recipe.output, recipe.output.stackSize * 4);
                            CrusherRecipe r = new CrusherRecipe(out, denseOre, recipe.energy + 8000);
                            if (recipe.secondaryOutput != null) {
                                Object[] newSec = new Object[recipe.secondaryOutput.length * 2];
                                for (int i = 0; i < recipe.secondaryOutput.length; i++) {
                                    newSec[i * 2] = Utils.copyStackWithAmount(
                                        recipe.secondaryOutput[i],
                                        recipe.secondaryOutput[i].stackSize * 2);
                                    newSec[i * 2 + 1] = recipe.secondaryChance[i] * 2;
                                }
                                r.addToSecondaryOutput(newSec);
                            }
                            crushRecipes.add(r);
                            c = true;
                        }
                    boolean a = false;
                    for (ArcFurnaceRecipe recipe : ArcFurnaceRecipe.recipeList)
                        if (recipe.oreInputString != null && ((String) recipe.oreInputString).equals(baseOre)) {
                            ItemStack out = Utils.copyStackWithAmount(recipe.output, recipe.output.stackSize * 4);
                            ArcFurnaceRecipe r = new ArcFurnaceRecipe(
                                out,
                                denseOre,
                                recipe.slag,
                                recipe.time,
                                recipe.energyPerTick);
                            arcRecipes.add(r);
                            a = true;
                        }
                    IELogger.info(
                        "Supporting DenseOre: " + denseOre
                            + "("
                            + baseOre
                            + "), Crushing:"
                            + (c ? "[X]" : "[ ]")
                            + ", Arc Smelting:"
                            + (a ? "[X]" : "[ ]"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            CrusherRecipe.recipeList.addAll(crushRecipes);
            ArcFurnaceRecipe.recipeList.addAll(arcRecipes);
        }
    }
}
