package com.github.pengfeizhou.animation.apng.decode;

import com.github.pengfeizhou.animation.apng.io.APNGReader;

import java.io.IOException;

/**
 * @Description: https://developer.mozilla.org/en-US/docs/Mozilla/Tech/APNG#.27fdAT.27:_The_Frame_Data_Chunk
 * @Author: pengfei.zhou
 * @CreateDate: 2019/3/27
 */
class FDATChunk extends Chunk {
    static final int ID = Chunk.fourCCToInt("fdAT");
    int sequence_number;

    @Override
    void innerParse(APNGReader reader) throws IOException {
        sequence_number = reader.readInt();
    }
}
