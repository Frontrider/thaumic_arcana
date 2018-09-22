package hu.frontrider.arcana.content.worldgen.generators.magictree

import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.gen.feature.WorldGenAbstractTree
import java.util.*

class MagicTreeGenerator(private val LOG: IBlockState,
                         private val LEAF:IBlockState,
                         private val useExtraRandomHeight: Boolean
) :WorldGenAbstractTree(true){

    override fun generate(worldIn: World, rand: Random, position: BlockPos): Boolean {
        var i = rand.nextInt(3) + 5

        if (this.useExtraRandomHeight) {
            i += rand.nextInt(7)
        }

        var flag = true

        if (position.y >= 1 && position.y + i + 1 <= 256) {
            for (j in position.y..position.y + 1 + i) {
                var k = 1

                if (j == position.y) {
                    k = 0
                }

                if (j >= position.y + 1 + i - 2) {
                    k = 2
                }

                val mutableBlockPos = BlockPos.MutableBlockPos()

                var l = position.x - k
                while (l <= position.x + k && flag) {
                    var i1 = position.z - k
                    while (i1 <= position.z + k && flag) {
                        if (j >= 0 && j < worldIn.height) {
                            if (!this.isReplaceable(worldIn, mutableBlockPos.setPos(l, j, i1))) {
                                flag = false
                            }
                        } else {
                            flag = false
                        }
                        ++i1
                    }
                    ++l
                }
            }

            if (!flag) {
                return false
            } else {
                val down = position.down()
                val state = worldIn.getBlockState(down)
                val isSoil = state.block.canSustainPlant(state, worldIn, down, net.minecraft.util.EnumFacing.UP, Blocks.SAPLING as net.minecraft.block.BlockSapling)

                if (isSoil && position.y < worldIn.height - i - 1) {
                    state.block.onPlantGrow(state, worldIn, down, position)

                    for (i2 in position.y - 3 + i..position.y + i) {
                        val k2 = i2 - (position.y + i)
                        val l2 = 1 - k2 / 2

                        for (i3 in position.x - l2..position.x + l2) {
                            val j1 = i3 - position.x

                            for (k1 in position.z - l2..position.z + l2) {
                                val l1 = k1 - position.z

                                if (Math.abs(j1) != l2 || Math.abs(l1) != l2 || rand.nextInt(2) != 0 && k2 != 0) {
                                    val blockpos = BlockPos(i3, i2, k1)
                                    val state2 = worldIn.getBlockState(blockpos)

                                    if (state2.block.isAir(state2, worldIn, blockpos) || state2.block.isAir(state2, worldIn, blockpos)) {
                                        this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF)
                                    }
                                }
                            }
                        }
                    }

                    for (j2 in 0 until i) {
                        val upN = position.up(j2)
                        val state2 = worldIn.getBlockState(upN)

                        if (state2.block.isAir(state2, worldIn, upN) || state2.block.isLeaves(state2, worldIn, upN)) {
                            this.setBlockAndNotifyAdequately(worldIn, position.up(j2), LOG)
                        }
                    }

                    return true
                } else {
                    return false
                }
            }
        } else {
            return false
        }
    }
}