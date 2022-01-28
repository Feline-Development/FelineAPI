package dev.trixxie.felineapi;

import dev.trixxie.felineapi.utility.ExperienceUtility;
import org.bukkit.plugin.java.JavaPlugin;

public final class FelinePlugin extends JavaPlugin {


    static FelinePlugin instance;
    ExperienceUtility felineAPI = new ExperienceUtility();

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }
}
