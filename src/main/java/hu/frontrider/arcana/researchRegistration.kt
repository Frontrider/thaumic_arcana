package hu.frontrider.arcana

import com.google.gson.Gson
import hu.frontrider.thaumcraft.researchloader.ResearchCategoryJson
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.relauncher.libraries.ModList
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.research.ResearchCategories
import java.io.File


/**
 * Registers research, uses the loader if present.
 * */
fun registerResearchLoader(suggestedConfigurationFile:File){


    val researchFiles = arrayOf(
            "${ThaumicArcana.MODID}:research/biomancy",
            "${ThaumicArcana.MODID}:research/biomancy/enchanting",
            "${ThaumicArcana.MODID}:research/biomancy/plantproducts",
            "${ThaumicArcana.MODID}:research/biomancy/animalproducts",
            "${ThaumicArcana.MODID}:research/biomancy/golems",
            "${ThaumicArcana.MODID}:research/biomancy/plantexperiments",
            "${ThaumicArcana.MODID}:research/biomancy/metallurgy",
            "${ThaumicArcana.MODID}:research/biomancy/slime",
            //"${ThaumicArcana.MODID}:research/gauntlet",
            "${ThaumicArcana.MODID}:research/scans"
    )
    val research = ResearchCategoryJson()
            .setKey("BIOMANCY")
            .setRequired_research("MINDBIOTHAUMIC")
            .setAspectList(
                    AspectList().add(Aspect.ALCHEMY, 30).add(Aspect.LIFE, 10).add(Aspect.MAGIC, 10).add(Aspect.LIGHT, 5).add(Aspect.AVERSION, 5).add(Aspect.EARTH, 5).add(Aspect.WATER, 5)
            )
            .setIcon("${ThaumicArcana.MODID}:textures/research/cat_biomancy.png")
            .setBackground("thaumcraft:textures/gui/gui_research_back_7.jpg")
            .setBackground_overlay("thaumcraft:textures/gui/gui_research_back_over.png")
    Loader.instance().activeModList.firstOrNull{
        it.modId =="thaumcraftresearchloader"
    }?.apply{

        val researchFile = File(suggestedConfigurationFile.parent + "/thaumcraftresearchloader/research/${ThaumicArcana.MODID}.txt")
        researchFile.parentFile.mkdirs()
        if (!researchFile.exists()) {
            researchFile.createNewFile()
            val printWriter = researchFile.printWriter(Charsets.UTF_8)

            researchFiles.forEach {
                printWriter.write(it + "\n")
            }
            printWriter.close()
        }
        val category = File(suggestedConfigurationFile.parent + "/thaumcraftresearchloader/category/${ThaumicArcana.MODID}.json")
        category.parentFile.mkdirs()
        if (!category.exists()) {
            category.createNewFile()
            val printWriter = category.printWriter(Charsets.UTF_8)

            printWriter.println(Gson().toJson(research))
            printWriter.close()
        }
        return
    }

    //register research if the loader is not present.
researchFiles.forEach{s -> ThaumcraftApi.registerResearchLocation(ResourceLocation(s.toLowerCase()))}
    ResearchCategories.registerCategory(
            research.key,
            research.required_research,
            research.aspects,
            ResourceLocation(research.icon),
            ResourceLocation(research.background),
            ResourceLocation(research.background_overlay)
    )
}