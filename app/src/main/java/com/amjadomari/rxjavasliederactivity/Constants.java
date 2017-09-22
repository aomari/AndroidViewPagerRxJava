package com.amjadomari.rxjavasliederactivity;

import com.amjadomari.rxjavasliederactivity.animation.AccordionTransformer;
import com.amjadomari.rxjavasliederactivity.animation.BackgroundToForegroundTransformer;
import com.amjadomari.rxjavasliederactivity.animation.CubeInTransformer;
import com.amjadomari.rxjavasliederactivity.animation.CubeOutTransformer;
import com.amjadomari.rxjavasliederactivity.animation.DefaultTransformer;
import com.amjadomari.rxjavasliederactivity.animation.DepthPageTransformer;
import com.amjadomari.rxjavasliederactivity.animation.FlipHorizontalTransformer;
import com.amjadomari.rxjavasliederactivity.animation.FlipVerticalTransformer;
import com.amjadomari.rxjavasliederactivity.animation.ForegroundToBackgroundTransformer;
import com.amjadomari.rxjavasliederactivity.animation.RotateDownTransformer;
import com.amjadomari.rxjavasliederactivity.animation.RotateUpTransformer;
import com.amjadomari.rxjavasliederactivity.animation.ScaleInOutTransformer;
import com.amjadomari.rxjavasliederactivity.animation.StackTransformer;
import com.amjadomari.rxjavasliederactivity.animation.TabletTransformer;
import com.amjadomari.rxjavasliederactivity.animation.ZoomInTransformer;
import com.amjadomari.rxjavasliederactivity.animation.ZoomOutSlideTransformer;
import com.amjadomari.rxjavasliederactivity.animation.ZoomOutTranformer;

import java.util.ArrayList;

class Constants {

    static final ArrayList<TransformerItem> TRANSFORM_CLASSES;

    static {

        TRANSFORM_CLASSES = new ArrayList<>();

        TRANSFORM_CLASSES.add(new TransformerItem(DefaultTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(AccordionTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(BackgroundToForegroundTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(CubeInTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(CubeOutTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(DepthPageTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(FlipHorizontalTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(FlipVerticalTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(ForegroundToBackgroundTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(RotateDownTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(RotateUpTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(ScaleInOutTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(StackTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(TabletTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(ZoomInTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(ZoomOutSlideTransformer.class));

        TRANSFORM_CLASSES.add(new TransformerItem(ZoomOutTranformer.class));
    }
}