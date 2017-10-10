package edu.uw.materialdemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by joelross on 10/10/17.
 */

//example based on https://www.bignerdranch.com/blog/customizing-coordinatorlayouts-behavior/
public class ShrinkBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    private final static String TAG = "ShrinkBehavior";

    public ShrinkBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        //add SnackbarLayout to the dependency list (if any)
        return dependency instanceof Snackbar.SnackbarLayout || super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {

        if(dependency instanceof Snackbar.SnackbarLayout){
            //calculate how much Snackbar we see
            float snackbarOffset = 0;
            if(parent.doViewsOverlap(child, dependency)){
                snackbarOffset = Math.min(snackbarOffset, dependency.getTranslationY() - dependency.getHeight());
            }
            float scaleFactor = 1 - (-snackbarOffset/dependency.getHeight());

            child.setScaleX(scaleFactor);
            child.setScaleY(scaleFactor);
            return true;
        }else {
            return super.onDependentViewChanged(parent, child, dependency);
        }
    }

}
