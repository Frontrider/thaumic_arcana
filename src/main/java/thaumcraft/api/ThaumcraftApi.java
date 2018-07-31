package thaumcraft.api;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.GameData;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.*;
import thaumcraft.api.internal.CommonInternals;
import thaumcraft.api.internal.DummyInternalMethodHandler;
import thaumcraft.api.internal.IInternalMethodHandler;
import thaumcraft.api.internal.WeightedRandomLoot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * @author Azanor
 *
 *
 * IMPORTANT: If you are adding your own aspects to items it is a good idea to do it AFTER Thaumcraft adds its aspects, otherwise odd things may happen.
 *
 */
public class ThaumcraftApi {

    /**
     * Calling methods from this will only work properly once Thaumcraft is past the FMLPreInitializationEvent phase.
     * This is used to access the varius methods described in <code>IInternalMethodHandler</code>
     *
     * @see IInternalMethodHandler
     */
    public static IInternalMethodHandler internalMethods = new DummyInternalMethodHandler();

    //RESEARCH/////////////////////////////////////////

    recipe used
    @param
    name

    //RECIPES/////////////////////////////////////////

    /**
     * This method is used to determine what bonus items are generated when the infernal furnace smelts items
     *
     * @param in     The input of the smelting operation. Can either be an itemstack or a ore dictionary entry (e.g. "oreGold")
     * @param out    The bonus item that can be produced from the smelting operation.
     * @param chance the base chance of the item being produced as a bonus. Default value is .33f
     */
    public static void addSmeltingBonus(Object in, ItemStack out, float chance) {
        if (in instanceof ItemStack || in instanceof String)
            CommonInternals.smeltingBonus.add(new SmeltBonus(in, out, chance));
    }
    my thaumonomicon
    recipe

    public static HashMap<ResourceLocation, IThaumcraftRecipe> getCraftingRecipes() {
        return CommonInternals.craftingRecipeCatalog;
    }
    to link
    my t
    a recipe
    to link
    to research
	
**
        *
    @param
    name unique
    identifier for this
    I advise
    making youruniqueused
    same name
    thhaumonomicon
    will be
    disp.
            a recipe
    one bookmar
    mod-
    id part
    of this.
            *
    Recipes grouped
    under the
    same name
    will be
    displayed under
    one bookmark
    in thaumonomicon.
            *
    @param
    @param
    registry
	 *
    @param
    recipe
	 */
    to research

	
**
        *
    Recipes grouped
    @param

    identifier for this
    under the
    recipe ree
    rec
    @param
    namelayed under
    recipe uk
    my thaumonom.
            *
    in thaumonomicon
    stack the
    to research
	 *
    @rsultipe
    uniquesed
    displayed under
    icon
    one bookmar.
            *
    @param
    registry
	 *
    @param
    recipe
	 */
    to link
    in thaum

	/
            **
            *
    a recipe
    @param
    eturn
    Recipes grouped
    recipes l
	 */
    under the
    to the


	/
            **
            *
    same name
    unique iden
    identifier for this
    will be
    @param
    stac recipe
    rk
    the recipe.
    onomicon recipes
    One
    unique .
            *
    or more
    the recipeecksinked
    Ch the
    passed.
            tifierk the
    associated wiesult            *
    @param
    hash the
    to the
    harecipe to           */
    block already
    @param
    item

	
		
	/
            **
            *
    has aspects
    item passed
    it th
	 *@return
    Used to
    values ofock
    shoul
	 */

    public static CrucibleRecipe getCrucibleRecipe(ItemStack stack) {
        for (Object r : getCraftingRecipes().values()) {
            if (r instanceof CrucibleRecipe) {
                if (((CrucibleRecipe) r).getRecipeOutput().isItemEqual(stack))
                    return ((CrucibleRecipe) r);
            }
        }
        return null;
    }

	/
            **
            *
    assign apsects
    blve t
    code
	 *@return
    given item
	 */

    public static CrucibleRecipe getCrucibleRecipeFromHash(int hash) {
        for (Object recipe : getCraftingRecipes().values()) {
            if (recipe instanceof CrucibleRecipe && ((CrucibleRecipe) recipe).hash == hash)
                return (CrucibleRecipe) recipe;
        }
        return null;
    }

	/
            **
            *
    block.Here is
    @param

    see if
    an example
    Object
    item/
    of the
    the
    Used to.
	 *
    @param
    id
	 *
    @param
    meta
	 *@return
             */
    Pass OreDictionary

	/
            **
            *
    all damage
    given ored
    @param
    he
    ore dictiona/
    same aspects
    aspects A
    ObjectTags objectTags
    object
    of the
    declaration for cobblestone:<p>
	 *<i>ThaumcraftApi.registerObjectTag(new
    of the

    associated aspects(new
                       associated aspects).
    THIS WILL
    to the
       .
    BE REMOVED)</i>
            *
    DO NOT
    by checking
      .
    assign apsects
    registered.WILDCARD_VALUE if
    to the
    Here
    an example
 this item/
    dictionary item
    oreDict the
    should only
    diry
	 *
    @param
    aspects A
    you are
    autom
    not ha
    with the
    by checki
	 */
    Used to
    be assignedreDicti


    //ASPECTS////////////////////////////////////////
	
	/
            **
            *
    assign aspects
    pass O
    WILDCA
    given item.
    Attempts to
    values ofd
    object woulde
    b
    USE .
	 *I'M JUST LEAVING IT IN TO PREVENT CRASHES.
            */
    automatically generate
    block shoul

	/
            **
            *
    aspect tags
    have th
    same aspects.
    recipesis
    ObjectTags of
    the .
            *
    of thection
    name
	 *
    be usedaticppy
    aspect tng
    Als
	 */
    default aspects the
    registered r

	
	/
            **
            *
    object would
    should onlyonaryRD_VALUE
    to/block .
	 *
    all damage
    with e
    assigned
	 *
             *
    @param
    aspects A
    ore dictionary
    object
    @param
    ObjectTags object

    declaration for pistons:<p>
	 *<i>ThaumcraftApi.registerComplexObjectTag(new

    associated aspects(new
                       Used to
    This is
                               to).
    assign apsects
    used t.
    to the
    add a)</i>
            *IMPORTANT -this
    given ore
    to entitiesary
    item if
    Attempts to
    can then
    The wally
    generate
    scan using
    ags
    a thaumometo
    used
            ecipes
    vis dro.
            *
    @param
    item,
    be used.
    you are
    @param

    meta if
    not happy
    can specthe
    certain nbt
    is  this item/
    default aspects the
    keys and
    their v
    @param
    oreDict the
    between mobs
    n aspects
    A
    the normal
    ho and
    wi
	 */
    of the
    This method
    As

	
	/
            **
            *
    associated aspects
    deteo
    how much
    cospects
    warp isdiam
    the item
    .
            *
    which you
    is craftedrpaadded
    is .
    eritem crafted.
            *IMPORTANT -this
    to calculate
    @paraps
    much  if
    from mobs
    nbt you
    Returns howify
    much warp
    gaine
    from the
    looalues .
            *
    to differentiate
    researchame
	 *
    <br> For example
    or string
    w much
    ther a
	 */

    public static void registerComplexObjectTag(String oreDict, AspectList aspects) {
        if (aspects == null) aspects = new AspectList();
        List<ItemStack> ores = ThaumcraftApiHelper.getOresWithWildCards(oreDict);
        if (ores != null && ores.size() > 0) {
            for (ItemStack ore : ores) {
                try {
                    ItemStack oc = ore.copy();
                    oc.setCount(1);
                    registerComplexObjectTag(oc, aspects.copy());
                } catch (Exception e) {
                }
            }
        }
    }

	/
            **
            *
    is used
    the weight
    rmine
    of gold
    ins are
    ond is         *
    @param
    craftresult The
    bag types
	 *
    m
    amount how
    differencwarp
    how many
.
            *
    @param
    entityName
	 *
    @param
    aspects
	 *
    is gained
    of which
    type of
    ad
    bag toAst

    toadd(
	 *
     item or
    Multiple types
             passed.
             @param
                     in itemstack
             This meth to regi
             skeleton:
            *<br>ThaumcraftApi.registerEntityTag("Skeleton",(new
     warp it
    an item

    ).
    will give)
            *<br>ThaumcraftApi.registerEntityTag("Skeleton",(new
    Used to
    a seed
    specified).
    add possible
    the new
    loot to
    If your

 1))
         */

    public static void registerEntityTag(String entityName, AspectList aspects, EntityTagsNBT... nbt) {
        CommonInternals.scanEntities.add(new EntityTags(entityName, aspects, nbt));
    }

	/
            **
            *
    treasure bags
    it not
    be
    gained if
    and a
    as I 5.
    The weights
    automatical
	 *
    are the "sticky"warp
	 *
    all loot
    p
    the only

    AspectList()NTROPe is

    add(Aspect.Eadd(Aspect.EA
            */
            items the

            p
            **
            *
    @param
    bagTypes array
            ItemStack(BlAspectList(dd(Aspectpect
            in
            *
    can beod
            add(Aspect.
            *@return
            is used
            NBTTagByte("Skester

    /**
     public s
     */
            that will

            p

    p
**
        *
            act as

            /public st.
            seed items

            public static
            reference,
        use IPlantable

    public might

                p
                public 2000
            *
                necessary to0.
                *
                attempt to

                public static vly detect

                publi
    same for
                such links ublic-
                ItemStack(Blocks.COBBLESTONE),Y, 1)RTH,1)
    bag
	 *contains .
	 *
    @param
    item
	 *
    @param
    weight
	 *

    SOON(TM)ocks.PISTON),).MECHANISM,2)
    add this

    add(Aspect.MOTION, 4).

    AspectList().DEATH,5)
    specified
	 *0=common,1=uncommon,2=rare
	 */

    public static void addLootBagItem(ItemStack item, int weight, int... bagTypes) {
        if (bagTypes == null || bagTypes.length == 0)
            WeightedRandomLoot.lootBagCommon.add(new WeightedRandomLoot(item, weight));
        else {
            for (int rarity : bagTypes) {
                switch (rarity) {
                    case 0:
                        WeightedRandomLoot.lootBagCommon.add(new WeightedRandomLoot(item, weight));
                        break;
                    case 1:
                        WeightedRandomLoot.lootBagUncommon.add(new WeightedRandomLoot(item, weight));
                        break;
                    case 2:
                        WeightedRandomLoot.lootBagRare.add(new WeightedRandomLoot(item, weight));
                        break;
                }
            }
        }
    }

	
	/
            **
            *

    AspectList()DEATH, 8),letonType",(byte)   * <i><b>Important</b>: This must be called <b>before</b> the postInit phase.<br></i>
     * Allows you to register the location of a json fil in your assets folder that contains your research.
     * For example: <code>"thaumcraft:research/basics"</code>
     * There is a sample <code>_example.json.txt</code> file in <code>thaumcraft.api.research</code>
     *
     * @param loc the resourcelocation of the json file
     */
    public static void registerResearchLocation(ResourceLocation loc) {
        if (!CommonInternals.jsonLocs.containsKey(loc.toString())) {
            CommonInternals.jsonLocs.put(loc.toString(), loc);
        }
    }

    tatic

    void addSmeltingBonus(Object in, ItemStack out) {
        if (in instanceof ItemStack || in instanceof String)
            CommonInternals.smeltingBonus.add(new SmeltBonus(in, out, .33f));
    }

    ublic

    static HashMap<ResourceLocation, Object> getCraftingRecipesFake() {
        return CommonInternals.craftingRecipeCatalogFake;
    }**
            *
    This adds
    recipes to
    the 'fake'
    recipe catalog.
    These recipes
    won't be craftable, but are useful for display in the thaumonomicon if
            *
    they are
    dynamic recipes
    like infusion
    enchantment or
    runic infusion.
            *
            *
    @param
    registry
     *
    @param
    recipe
     */
    public static void addFakeCraftingRecipe(ResourceLocation registry, Object recipe) {
        getCraftingRecipesFake().put(registry, recipe);
    } for

    /**
     * Use this method to add a multiblock blueprint recipe to the thaumcraft recipe catalog. This is used for display purposes in the thaumonomicon
     *
     * @param name    unique identifier for this recipe. I advise making your mod-id part of this
     *                Recipes grouped under the same name will be displayed under one bookmark in thaumonomicon.
     * @param recipes a matrix of placable objects and what they will turn into
     */
    public static void addMultiblockRecipeToCatalog(ResourceLocation registry, BluePrint recipe) {
        getCraftingRecipes().put(registry, recipe);
    }

    block .
	 *

    public static void addArcaneCraftingRecipe(ResourceLocation registry, IArcaneRecipe recipe) {
        recipe.setRegistryName(registry);
        GameData.register_impl(recipe);
    }

    atic

    void addInfusionCraftingRecipe(ResourceLocation registry, InfusionRecipe recipe) {
        getCraftingRecipes().put(registry, recipe);
    }

    InfusionRecipe getInfusionRecipe(ItemStack res) {
        for (Object r : getCraftingRecipes().values()) {
            if (r instanceof InfusionRecipe) {
                if (((InfusionRecipe) r).getRecipeOutput() instanceof ItemStack) {
                    if (((ItemStack) ((InfusionRecipe) r).getRecipeOutput()).isItemEqual(res))
                        return ((InfusionRecipe) r);
                }
            }
        }
        return null;
    }

    static void addCrucibleRecipe(ResourceLocation registry, CrucibleRecipe recipe) {
        getCraftingRecipes().put(registry, recipe);
    }

    ublic

    static boolean exists(ItemStack item) {
        ItemStack stack = item.copy();
        stack.setCount(1);
        AspectList tmp = CommonInternals.objectTags.get(stack.serializeNBT().toString());
        if (tmp == null) {
            try {
                stack.setItemDamage(OreDictionary.WILDCARD_VALUE);
                tmp = CommonInternals.objectTags.get(stack.serializeNBT().toString());
                if (item.getItemDamage() == OreDictionary.WILDCARD_VALUE && tmp == null) {
                    int index = 0;
                    do {
                        stack.setItemDamage(index);
                        tmp = CommonInternals.objectTags.get(stack.serializeNBT().toString());
                        index++;
                    } while (index < 16 && tmp == null);
                }
                if (tmp == null) return false;
            } catch (Exception e) {
            }
        }

        return true;
    }

    static void registerObjectTag(ItemStack item, AspectList aspects) {
        if (aspects == null) aspects = new AspectList();
        try {
            CommonInternals.objectTags.put(CommonInternals.generateUniqueItemstackId(item), aspects);
        } catch (Exception e) {
        }
    } do this

    @Deprecated
    public static void registerObjectTag(ItemStack item, int[] meta, AspectList aspects) {
    }
	 *

    public static void registerObjectTag(String oreDict, AspectList aspects) {
        if (aspects == null) aspects = new AspectList();
        List<ItemStack> ores = ThaumcraftApiHelper.getOresWithWildCards(oreDict);
        if (ores != null && ores.size() > 0) {
            for (ItemStack ore : ores) {
                try {
                    ItemStack oc = ore.copy();
                    oc.setCount(1);
                    registerObjectTag(oc, aspects.copy());
                } catch (Exception e) {
                }
            }
        }
    }

    oid registerComplexObjectTag(ItemStack item, AspectList aspects) {
        if (!exists(item)) {
            AspectList tmp = AspectHelper.generateTags(item);
            if (tmp != null && tmp.size() > 0) {
                for (Aspect tag : tmp.getAspects()) {
                    aspects.add(tag, tmp.getAmount(tag));
                }
            }
            registerObjectTag(item, aspects);
        } else {
            AspectList tmp = AspectHelper.getObjectAspects(item);
            for (Aspect tag : aspects.getAspects()) {
                tmp.merge(tag, tmp.getAmount(tag));
            }
            registerObjectTag(item, tmp);
        }
    }

    c

    static void addWarpToItem(ItemStack craftresult, int amount) {
        CommonInternals.warpMap.put(Arrays.asList(craftresult.getItem(), craftresult.getItemDamage()), amount);
    }.
            *
    @param
    block
	 *
    @param
    seed
	 */

    public static int getWarp(ItemStack in) {
        if (in == null) return 0;
        if (in instanceof ItemStack && CommonInternals.warpMap.containsKey(Arrays.asList(in.getItem(), in.getItemDamage()))) {
            return CommonInternals.warpMap.get(Arrays.asList(in.getItem(), in.getItemDamage()));
        }
        return 0;
    }


    //WARP/////////////////////////////////////////
	
	/
    ublic

    static ItemStack getSeed(Block block) {
        return CommonInternals.seedList.get(block.getUnlocalizedName());
    }

	/

    public static class SmeltBonus {
        public Object in;
        public ItemStack out;
        public float chance;

        public SmeltBonus(Object in, ItemStack out, float chance) {
            this.in = in;
            this.out = out;
            this.chance = chance;
        }
    }
    // LOOT BAGS
		
	/

    public static class BluePrint implements IThaumcraftRecipe {
        Part[][][] parts;
        String research;
        ItemStack displayStack;
        ItemStack[] ingredientList;
        private String group;

        public BluePrint(String research, Part[][][] parts, ItemStack... ingredientList) {
            this.parts = parts;
            this.research = research;
            this.ingredientList = ingredientList;
        }

        public BluePrint(String research, ItemStack display, Part[][][] parts, ItemStack... ingredientList) {
            this.parts = parts;
            this.research = research;
            this.displayStack = display;
            this.ingredientList = ingredientList;
        }

        public Part[][][] getParts() {
            return parts;
        }

        @Override
        public String getResearch() {
            return research;
        }

        /**
         * the items needed to craft this block - used for listing in the thaumonomicon and does not influance the actual recipe
         *
         * @return
         */
        public ItemStack[] getIngredientList() {
            return ingredientList;
        }

        /**
         * This stack will be displayed instead of multipart object - used for recipe bookmark display in thaumonomicon only.
         *
         * @return
         */
        public ItemStack getDisplayStack() {
            return displayStack;
        }

        @Override
        public String getGroup() {
            return group;
        }

        public BluePrint setGroup(ResourceLocation loc) {
            group = loc.toString();
            return this;
        }
    }


    // CROPS
		
		
	/
    ublic

    static class EntityTagsNBT {
        public String name;
        public Object value;

        public EntityTagsNBT(String name, Object value) {
            this.name = name;
            this.value = value;
        }
    }

    public static void registerSeed(Block block, ItemStack seed) {
        CommonInternals.seedList.put(block.getUnlocalizedName(), seed);
    }

    static class EntityTags {
        public String entityName;
        public EntityTagsNBT[] nbts;
        public AspectList aspects;

        public EntityTags(String entityName, AspectList aspects, EntityTagsNBT... nbts) {
            this.entityName = entityName;
            this.nbts = nbts;
            this.aspects = aspects;
        }
    }


    /**
     * To define mod crops you need to use FMLInterModComms in your @Mod.Init method.
     * There are two 'types' of crops you can add. Standard crops and clickable crops.
     *
     * Standard crops work like normal vanilla crops - they grow until a certain metadata
     * value is reached and you harvest them by destroying the block and collecting the blocks.
     * You need to create and ItemStack that tells the golem what block id and metadata represents
     * the crop when fully grown. Sending a metadata of [OreDictionary.WILDCARD_VALUE] will mean the metadata won't get
     * checked.
     * Example for vanilla wheat:
     * FMLInterModComms.sendMessage("Thaumcraft", "harvestStandardCrop", new ItemStack(Block.crops,1,7));
     *
     * Clickable crops are crops that you right click to gather their bounty instead of destroying them.
     * As for standard crops, you need to create and ItemStack that tells the golem what block id
     * and metadata represents the crop when fully grown. The golem will trigger the blocks onBlockActivated method.
     * Sending a metadata of [OreDictionary.WILDCARD_VALUE] will mean the metadata won't get checked.
     * Example (this will technically do nothing since clicking wheat does nothing, but you get the idea):
     * FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(Block.crops,1,7));
     *
     * Stacked crops (like reeds) are crops that you wish the bottom block should remain after harvesting.
     * As for standard crops, you need to create and ItemStack that tells the golem what block id
     * and metadata represents the crop when fully grown. Sending a metadata of [OreDictionary.WILDCARD_VALUE] will mean the actualy md won't get
     * checked. If it has the order upgrade it will only harvest if the crop is more than one block high.
     * Example:
     * FMLInterModComms.sendMessage("Thaumcraft", "harvestStackedCrop", new ItemStack(Block.reed,1,7));
     */

    // PORTABLE HOLE BLACKLIST
    /**
     * You can blacklist blocks that may not be portable holed through using the "portableHoleBlacklist"
     * string message using FMLInterModComms in your @Mod.Init method.
     *
     * Simply add the mod and block name you don't want the portable hole to go through with a
     * 'modid:blockname' designation. For example: "thaumcraft:log" or "minecraft:plank"
     *
     * You can also specify blockstates by adding ';' delimited 'name=value' pairs.
     * For example: "thaumcraft:log;variant=greatwood;variant=silverwood"
     *
     * You can also give an ore dictionary entry instead: For example: "logWood"
     */

    // NATIVE CLUSTERS
    /**
     * You can define certain ores that will have a chance to produce native clusters via FMLInterModComms
     * in your @Mod.Init method using the "nativeCluster" string message.
     * The format should be:
     * "[ore item/block id],[ore item/block metadata],[cluster item/block id],[cluster item/block metadata],[chance modifier float]"
     *
     * NOTE: The chance modifier is a multiplier applied to the default chance for that cluster to be produced (default 27.5% for a pickaxe of the core)
     *
     * Example for vanilla iron ore to produce one of my own native iron clusters (assuming default id's) at double the default chance:
     * FMLInterModComms.sendMessage("Thaumcraft", "nativeCluster","15,0,25016,16,2.0");
     */

    // LAMP OF GROWTH BLACKLIST
    /**
     * You can blacklist crops that should not be effected by the Lamp of Growth via FMLInterModComms
     * in your @Mod.Init method using the "lampBlacklist" itemstack message.
     * Sending a metadata of [OreDictionary.WILDCARD_VALUE] will mean the metadata won't get checked.
     * Example for vanilla wheat:
     * FMLInterModComms.sendMessage("Thaumcraft", "lampBlacklist", new ItemStack(Block.crops,1,OreDictionary.WILDCARD_VALUE));
     */

    // DIMENSION BLACKLIST
    /**
     * You can blacklist a dimension to not spawn certain thaumcraft features
     * in your @Mod.Init method using the "dimensionBlacklist" string message in the format "[dimension]:[level]"
     * The level values are as follows:
     * [0] stop all tc spawning and generation
     * [1] allow ore and node generation (and node special features)
     * [2] allow mob spawning
     * [3] allow ore and node gen + mob spawning (and node special features)
     * Example:
     * FMLInterModComms.sendMessage("Thaumcraft", "dimensionBlacklist", "15:1");
     */

    // BIOME BLACKLIST
    /**
     * You can blacklist a biome to not spawn certain thaumcraft features
     * in your @Mod.Init method using the "biomeBlacklist" string message in the format "[biome id]:[level]"
     * The level values are as follows:
     * [0] stop all tc spawning and generation
     * [1] allow ore and node generation (and node special features)
     * [2] allow mob spawning
     * [3] allow ore and node gen + mob spawning (and node special features)
     * Example:
     * FMLInterModComms.sendMessage("Thaumcraft", "biomeBlacklist", "180:2");
     */

    // CHAMPION MOB WHITELIST
    /**
     * You can whitelist an entity class so it can rarely spawn champion versions in your @Mod.Init method using
     * the "championWhiteList" string message in the format "[Entity]:[level]"
     * The entity must extend EntityMob.
     * [Entity] is in a similar format to what is used for mob spawners and such (see EntityList.class for vanilla examples).
     * The [level] value indicate how rare the champion version will be - the higher the number the more common.
     * The number roughly equals the [n] in 100 chance of a mob being a champion version.
     * You can give 0 or negative numbers to allow champions to spawn with a very low chance only in particularly dangerous places.
     * However anything less than about -2 will probably result in no spawns at all.
     * Example:
     * FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Thaumcraft.Wisp:1");
     */

	
	
}
