/*
 * Copyright (c) bdew, 2014
 * https://github.com/bdew/deepcore
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/deepcore/master/MMPL-1.0.txt
 */

package net.bdew.deepcore.blocks.turbineController

import net.minecraft.util.Icon
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.texture.IconRegister
import net.minecraftforge.common.ForgeDirection
import net.bdew.deepcore.multiblock.block.BlockController

class BlockTurbineController extends BlockController("TurbineController", classOf[TileTurbineController]) {
  var topIcon: Icon = null
  var bottomIcon: Icon = null

  @SideOnly(Side.CLIENT)
  override def regIcons(ir: IconRegister) {
    topIcon = ir.registerIcon("deepcore:" + name.toLowerCase + "/top")
    bottomIcon = ir.registerIcon("deepcore:" + name.toLowerCase + "/bottom")
  }

  override def getIcon(side: Int, meta: Int) =
    if (side == ForgeDirection.UP.ordinal())
      topIcon
    else if (side == ForgeDirection.DOWN.ordinal())
      bottomIcon
    else
      blockIcon

}
