package com.github.pengfeizhou.animation.apng;


import android.content.Context;

import com.github.pengfeizhou.animation.loader.AssetStreamLoader;

/**
 * @Author: pengfei.zhou
 * @CreateDate: 2019/3/28
 * @see com.github.pengfeizhou.animation.loader.AssetStreamLoader use this insted
 */
@Deprecated
public class APNGAssetLoader extends AssetStreamLoader {

    public APNGAssetLoader(Context context, String assetName) {
        super(context, assetName);
    }
}
