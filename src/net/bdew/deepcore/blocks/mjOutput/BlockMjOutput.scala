/*
 * Copyright (c) bdew, 2014
 * https://github.com/bdew/deepcore
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/deepcore/master/MMPL-1.0.txt
 */

package net.bdew.deepcore.blocks.mjOutput

import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.bdew.deepcore.blocks.BaseModule
import net.bdew.lib.multiblock.block.BlockOutput
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.util.ForgeDirection

object BlockMjOutput extends BaseModule("MJOutput", "PowerOutput", classOf[TileMjOutput]) with BlockOutput[TileMjOutput] {
  var enabledIcon: IIcon = null

  override def getIcon(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int) =
    if (getTE(world, x, y, z).canConnectoToFace(ForgeDirection.values()(side)))
      enabledIcon
    else
      blockIcon

  @SideOnly(Side.CLIENT)
  override def registerBlockIcons(ir: IIconRegister) {
    blockIcon = ir.registerIcon("deepcore:mjoutput/disabled")
    enabledIcon = ir.registerIcon("deepcore:mjoutput/main")
  }
}