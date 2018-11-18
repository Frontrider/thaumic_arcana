package hu.frontrider.thaumcraft.researchloader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hu.frontrider.thaumcraft.researchloader.advancement.ResearchEventHandler;
import hu.frontrider.thaumcraft.researchloader.advancement.Triggers;
import hu.frontrider.thaumcraft.researchloader.crafting.AlchemyRecipe;
import hu.frontrider.thaumcraft.researchloader.crafting.RawAlchemyRecipe;
import hu.frontrider.thaumcraft.researchloader.crafting.RawAspect;
import hu.frontrider.thaumcraft.researchloader.util.Aspects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.lang3.tuple.Pair;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.common.lib.research.ResearchManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Mod(modid = TCResearchLoader.MODID,
        name = TCResearchLoader.NAME,
        version = TCResearchLoader.VERSION,
        dependencies = "required-after:thaumcraft;")
public class TCResearchLoader {

    public static final String MODID = "thaumcraftresearchloader";
    public static final String NAME = "Thaumcraft Research Loader";
    public static final String VERSION = "1.0";

    private File researchDirectory;
    private File researchCatDirectory;
    private File alchemyRecipeDirectory;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        File suggestedConfigurationFile = event.getSuggestedConfigurationFile();
        researchDirectory = new File(suggestedConfigurationFile.getParentFile() + "/" + MODID + "/research/");
        researchCatDirectory = new File(suggestedConfigurationFile.getParentFile() + "/" + MODID + "/category/");
        alchemyRecipeDirectory = new File(suggestedConfigurationFile.getParentFile() + "/" + MODID + "/recipes/alchemy");
        researchDirectory.mkdirs();
        researchCatDirectory.mkdirs();
        alchemyRecipeDirectory.mkdirs();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ResearchEventHandler());
        Gson gson = new GsonBuilder().create();

        try {
            File[] files = researchCatDirectory.listFiles();
            if (files == null)
                return;
            Arrays.stream(files)
                    .map(file -> {
                        try {
                            return new Gson().fromJson(new FileReader(file), ResearchCategoryJson.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .forEach(research -> ResearchCategories.registerCategory(
                            research.key,
                            research.required_research,
                            research.getAspects(),
                            new ResourceLocation(research.icon),
                            new ResourceLocation(research.background),
                            new ResourceLocation(research.background_overlay)
                    ));

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            File[] files = researchDirectory.listFiles();
            if (files == null)
                return;
            Arrays.stream(files)
                    .map(file -> {
                        try {
                            Scanner scanner = new Scanner(new FileInputStream(file));
                            StringBuilder result = new StringBuilder();
                            while (scanner.hasNextLine()) {
                                result.append(scanner.nextLine()).append("\n");
                            }
                            return result.toString();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return "";
                        }
                    })
                    .flatMap(s -> Arrays.stream(s.split("\n")))
                    .forEach(s -> ThaumcraftApi.registerResearchLocation(new ResourceLocation(s.toLowerCase())));

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File[] files = alchemyRecipeDirectory.listFiles();
            if (files == null)
                return;


            Arrays.stream(files)
                    .map(file -> {
                        try {
                            Scanner scanner = new Scanner(new FileInputStream(file));
                            StringBuilder result = new StringBuilder();
                            while (scanner.hasNextLine()) {
                                result.append(scanner.nextLine()).append("\n");
                            }
                            return result.toString();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return "";
                        }
                    })
                    .map(s -> gson.fromJson(s, RawAlchemyRecipe.class))
                    .map(raw -> {
                        AlchemyRecipe alchemyRecipe = new AlchemyRecipe();

                        alchemyRecipe.setResearchKey(raw.getResearchKey());
                        alchemyRecipe.setCatalyst(ResearchManager.parseJSONtoItemStack(raw.getCatalyst()));
                        alchemyRecipe.setResult(ResearchManager.parseJSONtoItemStack(raw.getResult()));

                        AspectList aspects = new AspectList();
                        for (RawAspect aspect : raw.getAspects()) {
                            Aspects.parseAspects(aspects, aspect);
                        }
                        alchemyRecipe.setAspects(aspects);
                        return alchemyRecipe;
                    })
                    .forEach(rawRecipe -> {

                        CrucibleRecipe crucibleRecipe = new CrucibleRecipe(
                                rawRecipe.getResearchKey(),
                                rawRecipe.getResult(),
                                rawRecipe.getCatalyst(),
                                rawRecipe.getAspects()
                        );
                        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(rawRecipe.getLocation()), crucibleRecipe);
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Triggers.init();
        for (int i = 0; i < Triggers.TRIGGER_ARRAY.size(); i++)
        {
            CriteriaTriggers.register(Triggers.TRIGGER_ARRAY .get(i));
        }
    }

}
