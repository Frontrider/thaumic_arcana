# Thaumic Arcana

discord: https://discord.gg/UvzmEfx

A thaumcraft addon, currently featuring a school of biomancy, containign ways to apply magic to living organisms.

### Repo rules
* The usual "be polite".
* One problem = one issue. As a general rule of thumb for any repository, please don't make issues with multiple topics, makes it easier to track them.

## Mechanics

### Growth

Apply alchemical principles to farm products in order to make more of them. This includes:
* beef, pork, mutton (no fish)
* wheat, potatoes, carrots, beet
* pumpkins and melons
* all vanilla trees

More options for leather farming.

### Enchanting

Create a new type of enchantment, that can be applied to living creatures (including yourself), instead of inanimate objects.
types:
* fertile: increases birthrates
* protection
* strength
* respiration
* speed
* vitality (regen)
* spider limbs
modifiers:
* negation: turns the enchant to it's opposite (if makes sense), or disables it.

foci that can be used to manipulate them:
* effect selection
* enable
* disable

### Misc

Other features:
* alchemical fertilizer (easy to mass produce)
* metal transmutation


## For modders

The creature enchantments are using the standard forge registry, the formula recipes use my own handler (no event for it yet). Thoose need to be added to their handler, at the Init phase. (don't try pre init, it will not be initialised)

Also, you're supposed to get the references via object holders. it was done, so that you can swap out my defaults with registry editing more easily. Please do that for custom maps only. If you need an edit for some other reason, than it might be actually needs to be done on my end.
