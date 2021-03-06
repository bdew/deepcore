/*
 * Copyright (c) bdew, 2013 - 2014
 * https://github.com/bdew/deepcore
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/deepcore/master/MMPL-1.0.txt
 */

package net.bdew.deepcore.resources

import net.bdew.lib.gui.Texture
import net.bdew.lib.render.IconPreloader
import net.minecraft.client.renderer.texture.IIconRegister

object IconLoader extends IconPreloader(1) {
  val scannerModuleHint = TextureLoc("deepcore:hint/scannermodule")
  val invalid = TextureLoc("deepcore:invalid")
  override def registerIcons(reg: IIconRegister) {
    for (res <- ResourceManager.list) {
      res.resTexture = Texture(sheet, reg.registerIcon("deepcore:scanner/" + res.name.toLowerCase + "/resource"))
      res.moduleIcon = reg.registerIcon("deepcore:scanner/" + res.name.toLowerCase + "/module")
    }
  }
}
