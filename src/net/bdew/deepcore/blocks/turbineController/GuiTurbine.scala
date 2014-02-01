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
import net.bdew.lib.gui.{Point, Rect, BaseScreen}
import net.minecraft.util.ResourceLocation
import net.bdew.lib.gui.widgets.{WidgetLabel, WidgetFluidGauge}
import net.bdew.deepcore.gui.Textures
import net.bdew.lib.Misc
import net.bdew.lib.power.WidgetPowerGauge
import net.bdew.deepcore.multiblock.gui.{WidgetInfo, WidgetOutputs}
import java.text.DecimalFormat

class GuiTurbine(val te: TileTurbineController, player: EntityPlayer) extends BaseScreen(new ContainerTurbine(te, player), 176, 160) {
  val texture = new ResourceLocation("deepcore:textures/gui/turbine.png")
  val dec = new DecimalFormat("0.00")
  val int = new DecimalFormat("0")
  override def initGui() {
    super.initGui()
    widgets.add(new WidgetPowerGauge(new Rect(61, 19, 9, 58), Textures.powerFill, te.power))
    widgets.add(new WidgetFluidGauge(new Rect(9, 19, 9, 58), Textures.tankOverlay, te.fuel))
    widgets.add(new WidgetLabel(Misc.toLocal("deepcore.gui.turbine.title"), 8, 6, 4210752))
    widgets.add(new WidgetOutputs(Point(76, 18), te, 6))
    widgets.add(new WidgetInfo(Rect(10, 85, 59, 10), Textures.Icons.turbine, te.numTurbines.cval.toString, Misc.toLocal("deepcore.label.turbine.turbines")))
    widgets.add(new WidgetInfo(Rect(10, 96, 59, 10), Textures.Icons.peak, int.format(te.mjPerTick.cval) + " MJ/t", Misc.toLocal("deepcore.label.turbine.maxprod")))
    widgets.add(new WidgetInfo(Rect(10, 107, 59, 10), Textures.Icons.fluid, dec.format(te.fuelPerTick.cval) + " MB/t", Misc.toLocal("deepcore.label.turbine.fuel")))
    widgets.add(new WidgetInfo(Rect(10, 118, 59, 10), Textures.Icons.power, int.format(te.mjPerTickAvg.cval) + " MJ/t", Misc.toLocal("deepcore.label.turbine.prod")))
  }
}
