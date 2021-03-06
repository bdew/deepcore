/*
 * Copyright (c) bdew, 2014
 * https://github.com/bdew/deepcore
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/deepcore/master/MMPL-1.0.txt
 */

package net.bdew.deepcore.world

import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.bdew.deepcore.Deepcore
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraft.world.chunk.Chunk
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.world.{ChunkDataEvent, WorldEvent}

object ChunkDataManager {

  class ChunkData {
    var somecrap: String = ""

    def write(nbt: NBTTagCompound) {
      val tag = new NBTTagCompound()
      tag.setString("somecrap", somecrap)
      nbt.setTag("bdew.deepcore", tag)
    }

    def read(nbt: NBTTagCompound) {
      val tag = nbt.getCompoundTag("bdew.deepcore")
      somecrap = tag.getString("somecrap")
    }
  }

  var worlds = Map.empty[World, Map[Chunk, ChunkData]]

  def init() {
    MinecraftForge.EVENT_BUS.register(this)
  }

  def get(ch: Chunk): ChunkData = get(ch, ch.worldObj)
  def get(ch: Chunk, world: World): ChunkData = {
    if (!worlds.isDefinedAt(world)) {
      Deepcore.logWarn("Attempting to load chunk from unloaded dimension! [%d] %d,%d", ch.worldObj.provider.dimensionId, ch.xPosition, ch.zPosition)
      worlds += (world -> Map.empty)
    }
    val chunks = worlds(world)
    if (chunks.contains(ch)) chunks(ch)
    else {
      val cd = new ChunkData
      worlds += (world -> (worlds(world) + (ch -> cd)))
      cd
    }
  }

  def del(ch: Chunk, world: World) = worlds += (world -> (worlds(world) - ch))

  def has(ch: Chunk, world: World) = worlds.contains(world) && worlds(world).contains(ch)

  @SubscribeEvent
  def onWorldLoad(ev: WorldEvent.Load) {
    if (ev.world.isRemote) return
    Deepcore.logInfo("Loading dimenstion: %d", ev.world.provider.dimensionId)
    if (worlds.isDefinedAt(ev.world))
      Deepcore.logWarn("Dimension %d already loaded, huh?", ev.world.provider.dimensionId)
    else
      worlds += (ev.world -> Map.empty)
  }

  @SubscribeEvent
  def onWorldUnLoad(ev: WorldEvent.Unload) {
    if (ev.world.isRemote) return
    Deepcore.logInfo("Unloding dimension %d - removing %d chunks", ev.world.provider.dimensionId, worlds(ev.world).size)
    worlds -= ev.world
  }

  @SubscribeEvent
  def onChunkLoad(ev: ChunkDataEvent.Load) {
    val ch = ev.getChunk
    //    Deepcore.logInfo("Loading chunk [%d] %d/%d", ch.worldObj.provider.dimensionId, ch.xPosition, ch.zPosition)
    if (has(ch, ev.world)) del(ch, ev.world)
    get(ch, ev.world).read(ev.getData)
  }

  @SubscribeEvent
  def onChunkSave(ev: ChunkDataEvent.Save) {
    val ch = ev.getChunk
    //    Deepcore.logInfo("Saving chunk [%d] %d/%d", ch.worldObj.provider.dimensionId, ch.xPosition, ch.zPosition)
    if (has(ch, ev.world)) {
      get(ch).write(ev.getData)
      if (!ch.isChunkLoaded)
        del(ch, ev.world)
    }
  }
}
