/*
 * Copyright (c) bdew, 2013 - 2014
 * https://github.com/bdew/deepcore
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/deepcore/master/MMPL-1.0.txt
 */

package net.bdew.deepcore.network

import net.bdew.lib.network.Message

case class MsgScannerSwitch(dir: Int) extends Message

case class MsgScannerUpdate(cx: Int, cy: Int, radius: Int, resId: Int, map: Array[Float]) extends Message