/*
 * Copyright (c) bdew, 2013 - 2014
 * https://github.com/bdew/deepcore
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/deepcore/master/MMPL-1.0.txt
 */

package net.bdew.deepcore.resources

import net.bdew.lib.recipes.gencfg.ConfigSection
import net.bdew.deepcore.resources.map.ResourceMapGen
import net.minecraft.util.Icon

class Resource(val name: String, definition: ConfigSection, amap: ResourceMapGen = null) {
  val id = definition.getInt("ID")
  val map = if (amap == null) ResourceManager.mapGenFromCfg(name, definition) else amap
  var moduleIcon: Icon = null
  var resIcon: Icon = null
}
