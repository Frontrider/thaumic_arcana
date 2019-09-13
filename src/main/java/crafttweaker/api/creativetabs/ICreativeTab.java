package crafttweaker.api.creativetabs;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.*;

@ZenClass("crafttweaker.creativetabs.ICreativeTab")
@ZenRegister
public interface ICreativeTab {
    
    @ZenMethod
    void setBackgroundImageName(String backgroundImage);
    
    @ZenGetter("searchBarWidth")
    int getSearchBarWidth();
    
    @ZenGetter("tabLabel")
    String getTabLabel();
    
    @ZenMethod
    void setNoScrollBar();
    
    @ZenMethod
    void setNoTitle();
    
    Object getInternal();
}
