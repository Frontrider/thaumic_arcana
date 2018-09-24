package hu.frontrider.thaumcraft.researchloader;

import com.google.gson.Gson;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.research.ResearchCategories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

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

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        File suggestedConfigurationFile = event.getSuggestedConfigurationFile();
        researchDirectory = new File(suggestedConfigurationFile.getParentFile() + "/" + MODID + "/research/");

        researchCatDirectory = new File(suggestedConfigurationFile.getParentFile() + "/" + MODID + "/category/");
        researchDirectory.mkdirs();
        researchCatDirectory.mkdirs();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
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
    }

}
