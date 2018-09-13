# Thaumic Arcana

discord: https://discord.gg/UvzmEfx

A thaumcraft addon, currently featuring a school of biomancy, containign ways to apply magic to living organisms.

Information:
https://minecraft.curseforge.com/projects/thaumic-arcana

### Repo rules
* The usual "be polite".
* One problem = one issue. As a general rule of thumb for any repository, please don't make issues with multiple topics, makes it easier to track them.


## For modders

The creature enchantments are using the standard forge registry, the formula recipes use my own handler (no event for it yet). Thoose need to be added to their handler, at the Init phase. (don't try pre init, it will not be initialised)

Also, you're supposed to get the references via object holders. it was done, so that you can swap out my defaults with registry editing more easily. Please do that for custom maps only. If you need an edit for some other reason, than it might be actually needs to be done on my end.
