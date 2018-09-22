import buildAnnotations.Open
import org.gradle.api.Plugin
import org.gradle.api.Project
import tasks.CursePublish

@Open
class MultiCursePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.create("cursePublish", CursePublish::class.java)
    }
}