/*
 * Copyright (c) bdew, 2014
 * https://github.com/bdew/deepcore
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/deepcore/master/MMPL-1.0.txt
 */

package net.bdew.deepcore.blocks.turbineController

import net.minecraft.entity.player.EntityPlayer
import net.bdew.lib.gui.{Rect, BaseScreen}
import net.minecraft.util.ResourceLocation
import net.bdew.lib.gui.widgets.WidgetFluidGauge
import net.bdew.deepcore.gui.Textures

class GuiTurbine(val te: TileTurbineController, player: EntityPlayer) extends BaseScreen(new ContainerTurbine(te, player), 176, 166) {
  val texture = new ResourceLocation("deepcore:textures/gui/turbine.png")
  override def initGui() {
    super.initGui()
    //addWidget(new WidgetPowerGauge(new Rect(8, 19, 16, 58), Textures.powerFill, te.power))
    addWidget(new WidgetFluidGauge(new Rect(32, 19, 16, 58), Textures.tankOverlay, te.fuel))

  }
}
