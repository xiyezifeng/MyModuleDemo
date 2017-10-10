package com.yekong.im.plugin;

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.emoticon.IEmoticonTab;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;

/**
 * Created by xigua on 2017/9/18.
 */

public class MyExtensionModule extends DefaultExtensionModule {
    private RedPackagePlugin redPackagePlugin;

    private MyEmoticon myEmoticon;

    /**
     * 扩展栏
     * @param conversationType
     * @return
     */
    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModules =  super.getPluginModules(conversationType);
        redPackagePlugin = new RedPackagePlugin();
        pluginModules.add(redPackagePlugin);
        return pluginModules;
    }

    /**
     * 表情栏
     * @return
     */
    @Override
    public List<IEmoticonTab> getEmoticonTabs() {
        List<IEmoticonTab> emoticonTabs =  super.getEmoticonTabs();
        myEmoticon = new MyEmoticon();
        emoticonTabs.add(myEmoticon);
        return emoticonTabs;
    }
}
