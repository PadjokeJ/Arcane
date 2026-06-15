package dev.padjokej.shimmer.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.VegetationBlock;

public class ZaunFlowerBlock extends VegetationBlock {
    public static final MapCodec<ZaunFlowerBlock> CODEC = simpleCodec(ZaunFlowerBlock::new);

    public ZaunFlowerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }


}
