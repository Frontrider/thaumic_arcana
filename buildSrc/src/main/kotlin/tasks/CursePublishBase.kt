package tasks

import buildAnnotations.Open
import com.mashape.unirest.http.Unirest
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

@Open
open class CursePublishBase :DefaultTask(){

    @Input
    lateinit var apiKey:String
    @Input
    lateinit var projectID:String
    @Input
    lateinit var target: File
    @Input
    lateinit var changeLog:File


    @Input
    var token:String =""
    //be8412ec-c571-456c-96cc-33324ba49869

    @TaskAction
    fun submit(){
        Unirest.post("https://minecraft.curseforge.com/api/projects/$projectID>/upload-file")
                .header("X-Api-Token",token)
                .field("file",target)

    }
}

