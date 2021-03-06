/*
 * Copyright (c) bdew, 2013 - 2014
 * https://github.com/bdew/deepcore
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/deepcore/master/MMPL-1.0.txt
 */

package net.bdew.deepcore.items.scanner.overlay

import net.bdew.deepcore.Deepcore
import net.bdew.deepcore.network.{MsgScannerUpdate, NetHandler}
import net.bdew.deepcore.resources.ResourceManager
import net.bdew.lib.Misc
import net.bdew.lib.gui.widgets.{WidgetDynLabel, WidgetSubcontainer}
import net.bdew.lib.gui.{Rect, _}
import org.lwjgl.opengl.GL11

object ScannerOverlay extends WidgetSubcontainer(Rect(0, 0, 76, 106)) {
  val background = Texture(Deepcore.modId, "textures/gui/scanner.png", rect)

  NetHandler.regClientHandler {
    case msg: MsgScannerUpdate => ScannerOverlay.readUpdatePacket(msg)
  }

  // -1 = no module installed; -2 = waiting for switch from server
  var resId = -1
  def resName = if (ResourceManager.isValid(resId))
    ResourceManager.byId(resId).getLocalizedName
  else if (resId == -1)
    Misc.toLocal("deepcore.label.scanner.insert")
  else if (resId == -2)
    Misc.toLocal("deepcore.label.scanner.wait")
  else "ERROR"

  def getColor1 =
    if (ResourceManager.isValid(resId))
      ResourceManager.byId(resId).color1
    else
      Color.black

  def getColor2 =
    if (ResourceManager.isValid(resId))
      ResourceManager.byId(resId).color2
    else
      Color.black

  val mapData = collection.mutable.Map.empty[(Int, Int), Float]

  def readUpdatePacket(pkt: MsgScannerUpdate) {
    import pkt._
    this.resId = pkt.resId
    Deepcore.logInfo("Received map update for (%d,%d) r=%d res=%d", cx, cy, radius, pkt.resId)
    mapData.clear()
    if (radius > 0) {
      val it = map.toIterator
      for (x <- -radius to radius; y <- -radius to radius)
        mapData((cx + x, cy + y)) = it.next()
    }
  }

  def getScanVal(x: Int, y: Int) = mapData.getOrElse((x, y), 0F)

  add(new WidgetScanMap(Rect(6, 25, 67, 67), 6, 6, 0.5F))
  add(new WidgetIndicatorBar(Rect(5, 94, 69, 8)))
  add(new WidgetResIcon(Rect(6, 6, 16, 16)))
  add(new WidgetDynLabel(resName, 26, 10, Color.darkgray))

  override def draw(mouse: Point) {
    GL11.glTranslatef(parent.rect.w - rect.w, parent.rect.h - rect.h - 40, 0)
    drawTexture(rect, background)
    super.draw(mouse)
    GL11.glTranslatef(-parent.rect.w + rect.w, -parent.rect.h + rect.h + 40, 0)
  }
}
