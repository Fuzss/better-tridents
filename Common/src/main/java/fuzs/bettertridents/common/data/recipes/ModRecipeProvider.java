package fuzs.bettertridents.common.data.recipes;

import fuzs.bettertridents.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    public void buildRecipes() {
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.COMBAT, Items.TRIDENT)
                .define('#', ModRegistry.TRIDENT_FRAGMENT_ITEM.value())
                .define('P', Items.PRISMARINE_SHARD)
                .pattern(" ##")
                .pattern(" P#")
                .pattern("P  ")
                .unlockedBy(getHasName(ModRegistry.TRIDENT_FRAGMENT_ITEM.value()),
                        this.has(ModRegistry.TRIDENT_FRAGMENT_ITEM.value()))
                .save(this.output);
    }
}
