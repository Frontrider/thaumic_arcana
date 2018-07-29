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
 * <p>
 * <p>
 * IMPORTANT: If you are adding your own aspects to items it is a good idea to do it AFTER Thaumcraft adds its aspects, otherwise odd things may happen.
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
    @param
    name unique

    //RECIPES/////////////////////////////////////////
    recipe used
    Recipes grouped

    my thaumonomicon
    under t
    to link
    same
    a recipe
    will be
    to research
    I advise
    one bookmark

	
**
        *
    making your
    identifier for this
    id part
    recipe ushe
    my thaumo
    name
    to link
    a recipe
    @p.
            displayed
    under
    to researchp
    Recipes grou
    mod-
    in thaumonomicon
    of this.
            *
    @param
    name uniqueed
    displayed under
    nomicon
    one bookmar
    in thaumaram
    ed
    the recip.
            *
    @param
    @param
    registry
	 *
    @param
    recipe
	 */
    under the
    @param


	
**
        *
    same name
    recipe
    identifier for this
    will be
    my thaumonomico
    to link
    k
    a recipe
    onomicon stack
    the
    under the
   .
           *
    recipe resulte
    name uniqueused
    in thaumonomicon
    n
    @param
    or m
    recipes .
            *
    @param
    registry
	 *
    @param
    recipe
	 */
    to research

	/
            **
            *
    Recipes grouped
    unique identi
    @param

	 *@return
    same name
    recipe
	 */
    will be
    the recipe
    ha

	/
            **
            *
    displayed under
    @param
    identifier for this
    one bookmark
    unique reciprecipes
    One
    Checks ore
    the passedock
    linked
    bl .
            *
    to the
    has aspects
    fier stack
    the
    assign apsectresultsh
    the
    an example
    e .
            *
    the recipetoalready
    values of
    of t
    block should
  .
          */
    associated with

	
		
	/
            **
            *
    Used to
    @param
    asps
            Ob
	 *@return
    to the
    of the
	 */
    given item

	/
            **
            *
    block.Here is
    THIS WILL
    BE REM
    code
	 *@return
    of the
	 */
    @param
    item the
    dictionary

	/
            **
            *
    item passed
    see if
    Pass OreDictionary
    item/
    all damage
    @parahe
    p
    it .
	 *
    @param
    id
	 *
    @param
    meta
	 *@return
             */
    have the
    associated a

	/
            **
            *
    same aspectsects
    A
    assign aspects
    jectTags object
    to the

    given item
	 */
    At/
    associated aspects
    aspect tagOVED
    declaration for cobblestone:<p>
	 *<i>ThaumcraftApi.registerObjectTag(new

    DO NOT(new
           Used to
    of they
                   by).
    assign apsects
    should onl.
    to the
    be usedul)</i>
            *
    given ore
    you are
    shoitem.
    @param
            oreDict the
    with the
    y.WILDCARD_VALUE if
    ore dictionarym
    aspects A
    object would
         this item/
    ObjectTags object
    be assigneass
    OreDictionary
	 *spects
	 *
    Used to
    values ofock
    shoul bl
    have ttempts
    to
    @param
    aspects A


    //ASPECTS////////////////////////////////////////
	
	/
            **
            *
    automatically generate
    ObjectTags s
    of the

    by checking
    associated asp.
    registered recipes
    USE .
	 *I'M JUST LEAVING IT IN TO PREVENT CRASHES.
            */
    Here is
    assign aps

	/
            **
            *
    an example
    to the
    checd only
 .
         *
    not happy
    be usedaly
    tou are
    their v
    name
	 *
    default aspects the
    not happwith
    thd/
    WILDCARD_VALUE to

	
	/
            **
            *
    all damage
    @parad
    @param
    he
    ObjectTags o/block .
	 *
    same aspects
    associated aspectobject
    This i
    used to
    added iscrects.
            *
    Used to
    which you
    ects
    can th
    scan usin
    declaration for pistons:<p>
	 *<i>ThaumcraftApi.registerComplexObjectTag(new
    given ore
    a thaumometer.
            (new
    dictionary item).
    Attempts to
    to calculate
    i.
            automatically generate
    vis drops
     )</i>
            *IMPORTANT -this
    aspect tags
    from mobs
    yking
    @param
    nbt if
    registered recipes
    cero differente
    between mobs
    example
            .
            *
    @param
    item,
    default aspects the
    <br> For.
            object would
    the nor
    meta if
    be assignedm
    oreDict the
    This method
   this item/
    ore dictionary
    aspects A
    to determine
    all bject
    how mu
	 *
    of the
    warp is
	 */
    the iss
    The waraftre
	 */
    add aspects
    @param


	
	/
            **
            *
    to entities
    item craf
    @param
    en
    much wg
    is gained            *
    Also used
    much warp
    s ga
    from ou
    research pa.
            *IMPORTANT -this
    can specify
    @param
    tain nbt
    or strin if
    keys and
    how much
    uesiate
    will giv
    add possibmal
    loot to
      .
              *
    and wither
    The wei
    name
	 *
    is used
    are the
    Itemloot
            ch
    bag types
    tem
            differ

	/
            **
            *
    is craftedpsult
    The
    of which
    addted amount
    how
    bag to
    arp
    loot to
	 */
             .
             *
    Returns how
    This meth
    is used

    ined
    to registhe
    an item.
            *
    @param
    entityName
	 *
    @param
    aspects
	 *
    item or
    that will

    ssedin itemstack
    a seed
	 *
    @reg
    If your
	 *
    warp it
    seed iteme.
    Used to
    not be

    le
    necessa as
    I.

            skeleton:
            *<br>ThaumcraftApi.registerEntityTag("Skeleton",(new
    treasure bags).
    As a)
            *<br>ThaumcraftApi.registerEntityTag("Skeleton",(new
    the weight
    such li).
    of gold
    p
    new
    coins are 1))
            */
    and a
    AspectList

	/
            **
            *
    diamond isghts

    add(Aspect.Stack gained if
            the only
            add(Aspect.MECHANence is
            add(Aspect.M.
            how many
            AspectList()

	 *
    items the "sticky"warp
	 *
    @param
    bagTypes array

    AspectList()(Aspect .
	 *
    type of
    NBTTagByte Multiple
    types

            publi

    p
**
        *
    can beod

    /**
     * /**
     * ter
     * <p>
     * public
     * <p>
     * public s    p
     * in
     * <p>
     * act as
     * <p>
     * pturn
     * the specified
     * <p>
     * public sts
     * <p>
     * public static
     */
    use IPlantable

    @Deprec
    p
**
        *
    it might

    ppublic ry
    to attempt
    to

    public static v
            reference,
    automatically detect

    publinks ublic 2000
            *

    ItemStack(Blocks.COBBLESTONE),()50.
            *

    add(Aspect.ENTROPY, 1)EARTH, 1)
    same for

    SOON(TM)(Blocks.PISTON),-

    AspectList()ISM, 2)OTION,4)bag
	 *contains .
	 *
    @param
    item
	 *
    @param
    weight
	 *

    add(Aspect.DEATH, 5)DEATH, 8),("SkeletonType",(byte)
    add this

    /**
     * <i><b>Important</b>: This must be called <b>before</b> the postInit phase.<br></i>
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
    }.

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

    c

    static void addSmeltingBonus(Object in, ItemStack out) {
        if (in instanceof ItemStack || in instanceof String)
            CommonInternals.smeltingBonus.add(new SmeltBonus(in, out, .33f));
    }

    specified
	 *0=common,1=uncommon,2=rare
	 */

    public static HashMap<ResourceLocation, IThaumcraftRecipe> getCraftingRecipes() {
        return CommonInternals.craftingRecipeCatalog;
    }

	
	/
            **
            *

    public static HashMap<ResourceLocation, Object> getCraftingRecipesFake() {
        return CommonInternals.craftingRecipeCatalogFake;
    }   *
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
    }

    Use this
    method to
    add a
    multiblock blueprint
    recipe to
    the thaumcraft
    recipe catalog.
    This is
    used for
    display purposes
    in the
    thaumonomicon
     *
             *
    @param
    name unique
    identifier for this
    recipe.I advise
    making your
    mod-
    id part
    of this
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
    recipes a
    matrix of
    placable objects
    and what
    they will
    turn into
     */

    public static void addMultiblockRecipeToCatalog(ResourceLocation registry, BluePrint recipe) {
        getCraftingRecipes().put(registry, recipe);
    }

    static void addArcaneCraftingRecipe(ResourceLocation registry, IArcaneRecipe recipe) {
        recipe.setRegistryName(registry);
        GameData.register_impl(recipe);
    }

    tatic

    void addInfusionCraftingRecipe(ResourceLocation registry, InfusionRecipe recipe) {
        getCraftingRecipes().put(registry, recipe);
    }

    ublic

    static InfusionRecipe getInfusionRecipe(ItemStack res) {
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

    ublic

    static void addCrucibleRecipe(ResourceLocation registry, CrucibleRecipe recipe) {
        getCraftingRecipes().put(registry, recipe);
    } for

    public static CrucibleRecipe getCrucibleRecipe(ItemStack stack) {
        for (Object r : getCraftingRecipes().values()) {
            if (r instanceof CrucibleRecipe) {
                if (((CrucibleRecipe) r).getRecipeOutput().isItemEqual(stack))
                    return ((CrucibleRecipe) r);
            }
        }
        return null;
    }

    block .
	 *

    public static CrucibleRecipe getCrucibleRecipeFromHash(int hash) {
        for (Object recipe : getCraftingRecipes().values()) {
            if (recipe instanceof CrucibleRecipe && ((CrucibleRecipe) recipe).hash == hash)
                return (CrucibleRecipe) recipe;
        }
        return null;
    }

    atic

    boolean exists(ItemStack item) {
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

    void registerObjectTag(ItemStack item, AspectList aspects) {
        if (aspects == null) aspects = new AspectList();
        try {
            CommonInternals.objectTags.put(CommonInternals.generateUniqueItemstackId(item), aspects);
        } catch (Exception e) {
        }
    }

    ated

    public static void registerObjectTag(ItemStack item, int[] meta, AspectList aspects) {
    }

    ublic

    static void registerObjectTag(String oreDict, AspectList aspects) {
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

    static void registerComplexObjectTag(ItemStack item, AspectList aspects) {
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
    } do this

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
	 *

    public static void registerEntityTag(String entityName, AspectList aspects, EntityTagsNBT... nbt) {
        CommonInternals.scanEntities.add(new EntityTags(entityName, aspects, nbt));
    }

    oid addWarpToItem(ItemStack craftresult, int amount) {
        CommonInternals.warpMap.put(Arrays.asList(craftresult.getItem(), craftresult.getItemDamage()), amount);
    }

    c

    static int getWarp(ItemStack in) {
        if (in == null) return 0;
        if (in instanceof ItemStack && CommonInternals.warpMap.containsKey(Arrays.asList(in.getItem(), in.getItemDamage()))) {
            return CommonInternals.warpMap.get(Arrays.asList(in.getItem(), in.getItemDamage()));
        }
        return 0;
    }.
            *
    @param
    block
	 *
    @param
    seed
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


    //WARP/////////////////////////////////////////
	
	/
    ublic

    public static void registerSeed(Block block, ItemStack seed) {
        CommonInternals.seedList.put(block.getUnlocalizedName(), seed);
    }

	/

    static ItemStack getSeed(Block block) {
        return CommonInternals.seedList.get(block.getUnlocalizedName());
    }
    // LOOT BAGS
		
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


    // CROPS
		
		
	/
    ublic

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

    static class EntityTagsNBT {
        public String name;
        public Object value;

        public EntityTagsNBT(String name, Object value) {
            this.name = name;
            this.value = value;
        }
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
