package hu.frontrider.thaumcraft.researchloader.advancement;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.common.lib.research.ResearchManager;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static hu.frontrider.thaumcraft.researchloader.TCResearchLoader.MODID;

public class ResearchAdvancement implements ICriterionTrigger<ResearchAdvancement.Instance> {
    private ResourceLocation ID;
    private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

    public ResearchAdvancement(String research)
    {
        super();
        ID= new ResourceLocation(MODID, research.toLowerCase());
    }
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<ResearchAdvancement.Instance> listener) {
        ResearchAdvancement.Listeners consumeitemtrigger$listeners = this.listeners.get(playerAdvancementsIn);

        if (consumeitemtrigger$listeners == null) {
            consumeitemtrigger$listeners = new ResearchAdvancement.Listeners(playerAdvancementsIn);
            this.listeners.put(playerAdvancementsIn, consumeitemtrigger$listeners);
        }

        consumeitemtrigger$listeners.add(listener);
    }

    @Override
    public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<ResearchAdvancement.Instance> listener) {
        ResearchAdvancement.Listeners consumeitemtrigger$listeners = this.listeners.get(playerAdvancementsIn);

        if (consumeitemtrigger$listeners != null) {
            consumeitemtrigger$listeners.remove(listener);

            if (consumeitemtrigger$listeners.isEmpty()) {
                this.listeners.remove(playerAdvancementsIn);
            }
        }
    }

    @Override
    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
        this.listeners.remove(playerAdvancementsIn);
    }

    @Override
    public ResearchAdvancement.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
        return new ResearchAdvancement.Instance(this);
    }

    public class Instance extends AbstractCriterionInstance {
        public Instance(ResearchAdvancement parent) {
            super(parent.getId());
        }

        public boolean test() {
            return true;
        }
    }

    public void trigger(EntityPlayerMP player) {
        ResearchAdvancement.Listeners listeners = this.listeners.get(player.getAdvancements());

        if (listeners != null) {
            listeners.trigger();
        }
    }

    static class Listeners {
        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<Instance>> listeners = Sets.newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn) {
            this.playerAdvancements = playerAdvancementsIn;
        }

        public boolean isEmpty() {
            return this.listeners.isEmpty();
        }

        public void add(ICriterionTrigger.Listener<ResearchAdvancement.Instance> listener) {
            this.listeners.add(listener);
        }

        public void remove(ICriterionTrigger.Listener<ResearchAdvancement.Instance> listener) {
            this.listeners.remove(listener);
        }

        public void trigger() {
            List<Listener<Instance>> list = null;

            for (ICriterionTrigger.Listener<ResearchAdvancement.Instance> listener : this.listeners) {
                if (listener.getCriterionInstance().test()) {
                    if (list == null) {
                        list = Lists.newArrayList();
                    }

                    list.add(listener);
                }
            }

            if (list != null) {
                for (ICriterionTrigger.Listener<ResearchAdvancement.Instance> listener1 : list) {
                    listener1.grantCriterion(this.playerAdvancements);
                }
            }
        }
    }
}
