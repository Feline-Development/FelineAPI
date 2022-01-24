package dev.trixxie.felineapi;

import org.bukkit.plugin.java.JavaPlugin;

public final class FelinePlugin extends JavaPlugin {


    static FelinePlugin instance;
    FelineAPI felineAPI = new FelineAPI();

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }
}
