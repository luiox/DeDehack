package me.caiyudu.dedehack.mixin.client;

import me.caiyudu.dedehack.gui.chest.SalGuiChest;
import me.caiyudu.dedehack.gui.entity.SalGuiDupeButton;
import me.caiyudu.dedehack.managers.ModuleManager;
import me.caiyudu.dedehack.module.misc.ChestStealerModule;
import me.caiyudu.dedehack.module.misc.ManualDupeModule;
import me.caiyudu.dedehack.module.ui.ChestModule;
import me.caiyudu.dedehack.module.ui.DupeModule;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

//Shout-out and a half to Kami-Blue for this code: https://github.com/kami-blue
@Mixin(GuiContainer.class)
public abstract class MixinGuiContainer extends GuiScreen {

    private SalGuiChest dedeGuiChest;
    private final ChestStealerModule ChestStealer = (ChestStealerModule) ModuleManager.Get().GetMod(ChestStealerModule.class);
    private final ChestModule chestModule = (ChestModule) ModuleManager.Get().GetMod(ChestModule.class);

    private SalGuiDupeButton dedeGuiDupeButton;
    private final DupeModule dupeModule = (DupeModule) ModuleManager.Get().GetMod(DupeModule.class);
    private final ManualDupeModule manualDupeModule = (ManualDupeModule) ModuleManager.Get().GetMod(ManualDupeModule.class);

    @Shadow protected int guiLeft;
    @Shadow protected int guiTop;


    @Inject(method = "initGui", at = @At("RETURN"))
    public void initGui(CallbackInfo info)
    {
        /// Clear old ones :)
        buttonList.clear();

        dedeGuiChest = new SalGuiChest(1337, this.width / 2 - 75, this.guiTop - 20, "Steal");
        dedeGuiDupeButton = new SalGuiDupeButton(1338, this.width / 2 - 50, this.guiTop - 20, "Dupe");
        this.buttonList.add(dedeGuiDupeButton);
        this.buttonList.add(dedeGuiChest);
        dedeGuiDupeButton.setWidth(100);
        dedeGuiChest.setWidth(150);
        updateButton();

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1337) {
            ChestStealer.toggle();
        } else if(button.id == 1338) {
            manualDupeModule.toggle();
        } else {
            super.actionPerformed(button);
        }

    }

    @Inject(method = "updateScreen", at = @At("HEAD"))
    public void updateScreen(CallbackInfo ci) {
        updateButton();
    }

    private void updateButton() {
        if(chestModule.isEnabled() && chestModule.validGui) {
            dedeGuiChest.visible = true;
            if (ChestStealer.isEnabled())
                dedeGuiChest.displayString = "Stop";
            else if (!ChestStealer.isEnabled())
                dedeGuiChest.displayString = ChestStealer.Mode.getValue().toString();
        } else {
            dedeGuiChest.visible = false;
        }

        if(dupeModule.isEnabled() && dupeModule.validGui) {
            dedeGuiDupeButton.visible = true;
            dedeGuiDupeButton.displayString = "Dupe";
        } else {
            dedeGuiDupeButton.visible = false;
        }
    }

}
