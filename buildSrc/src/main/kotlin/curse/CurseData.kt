package curse

import buildAnnotations.Open
import com.sun.xml.internal.fastinfoset.util.StringArray

@Open
class CurseData(
        var changelog:String,
        var changeLogType:String,
        var displayName: String? = null,
        var gameVersions:StringArray,
        var releaseType:ReleaseType,
        vararg projects:Project
)
{
    enum class ReleaseType {
        alpha,beta,release
    }
    @Open
    class Project(
            var slug:String,
            var type:Type
    ){
        enum class Type{
            embeddedLibrary,incompatible,optionalDependency,requiredDependency,tool
        }
    }
}