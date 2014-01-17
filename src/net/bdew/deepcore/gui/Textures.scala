/*
 * Copyright (c) bdew, 2014
 * https://github.com/bdew/deepcore
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/deepcore/master/MMPL-1.0.txt
 */

package net.bdew.deepcore.gui

import net.bdew.lib.gui.TextureLocation
import net.minecraft.util.ResourceLocation
import net.bdew.deepcore.Deepcore

object Textures {
  val texture = new ResourceLocation(Deepcore.modId + ":textures/gui/widgets.png")
  val tankOverlay = new TextureLocation(texture, 16, 0)
  val powerFill = new TextureLocation(texture, 0, 0)
  val texturePowerError = new TextureLocation(texture, 32, 0)
  val slotSelect = new TextureLocation(texture, 48, 0)
  def greenProgress(width: Int) = new TextureLocation(texture, 136 - width, 58)
  def whiteProgress(width: Int) = new TextureLocation(texture, 136 - width, 73)
}
