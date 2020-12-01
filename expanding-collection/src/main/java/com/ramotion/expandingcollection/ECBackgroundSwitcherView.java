package com.ramotion.expandingcollection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

/**
 * Custom Image Switcher for display and change background images with some pretty animations.
 * Uses different drawing orders for animation purposes.
 */
public class ECBackgroundSwitcherView extends ImageSwitcher {
    private final int[] REVERSE_ORDER = new int[]{1, 0};
    private final int[] NORMAL_ORDER = new int[]{0, 1};

    private boolean reverseDrawOrder;

    private int bgImageGap;
    private int bgImageWidth;

    private int alphaDuration = 400;
    private int movementDuration = 500;
    private int widthBackgroundImageGapPercent = 12;

    private Animation bgImageInLeftAnimation;
    private Animation bgImageOutLeftAnimation;

    private Animation bgImageInRightAnimation;
    private Animation bgImageOutRightAnimation;

    private AnimationDirection currentAnimationDirection;

    private BitmapWorkerTask mCurrentAnimationTask;

    public ECBackgroundSwitcherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateAndInit(context);
    }

    public ECBackgroundSwitcherView(Context context) {
        super(context);
        inflateAndInit(context);
    }

    private void inflateAndInit(final Context context) {
        setChildrenDrawingOrderEnabled(true);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        bgImageGap = (displayMetrics.widthPixels / 100) * widthBackgroundImageGapPercent;
        bgImageWidth = displayMetrics.widthPixels + bgImageGap * 2;

        this.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView myView = new ImageView(context);
                myView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                myView.setLayoutParams(new FrameLayout.LayoutParams(bgImageWidth, FrameLayout.LayoutParams.MATCH_PARENT));
                myView.setTranslationX(-bgImageGap);
                return myView;
            }
        });

        bgImageInLeftAnimation = createBgImageInAnimation(bgImageGap, 0, movementDuration, alphaDuration);
        bgImageOutLeftAnimation = createBgImageOutAnimation(0, -bgImageGap, movementDuration);
        bgImageInRightAnimation = createBgImageInAnimation(-bgImageGap, 0, movementDuration, alphaDuration);
        bgImageOutRightAnimation = createBgImageOutAnimation(0, bgImageGap, movementDuration);
    }

//    public ECBackgroundSwitcherView withAnimationSettings(int movementDuration, int alphaDuration) {
//        this.movementDuration = movementDuration;
//        this.alphaDuration = alphaDuration;
//        bgImageInLeftAnimation = createBgImageInAnimation(bgImageGap, 0, movementDuration, alphaDuration);
//        bgImageOutLeftAnimation = createBgImageOutAnimation(0, -bgImageGap, movementDuration);
//        bgImageInRightAnimation = createBgImageInAnimation(-bgImageGap, 0, movementDuration, alphaDuration);
//        bgImageOutRightAnimation = createBgImageOutAnimation(0, bgImageGap, movementDuration);
//        return this;
//    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        if (reverseDrawOrder)
            return REVERSE_ORDER[i];
        else
            return NORMAL_ORDER[i];
    }

    public void setReverseDrawOrder(boolean reverseDrawOrder) {
        this.reverseDrawOrder = reverseDrawOrder;
    }

    private synchronized void setImageBitmapWithAnimation(String newBitmap, AnimationDirection animationDirection) {
        if (this.currentAnimationDirection == animationDirection) {
            this.setImageBitmap(newBitmap);
        } else if (animationDirection == AnimationDirection.LEFT) {
            this.setInAnimation(bgImageInLeftAnimation);
            this.setOutAnimation(bgImageOutLeftAnimation);
            this.setImageBitmap(newBitmap);
        } else if (animationDirection == AnimationDirection.RIGHT) {
            this.setInAnimation(bgImageInRightAnimation);
            this.setOutAnimation(bgImageOutRightAnimation);
            this.setImageBitmap(newBitmap);
        }
        this.currentAnimationDirection = animationDirection;
    }

    public void cacheBackgroundAtPosition(ECPager pager, int position) {
        if (position >= 0 && position < pager.getAdapter().getCount()) {
            Integer mainBgImageDrawableResource = pager.getDataFromAdapterDataset(position).getKey();
            if (mainBgImageDrawableResource == null) return;
            BitmapWorkerTask addBitmapToCacheTask = new BitmapWorkerTask(getResources(), mainBgImageDrawableResource);
            addBitmapToCacheTask.execute(mainBgImageDrawableResource);
        }
    }

    public void updateCurrentBackground(ECPager pager, final AnimationDirection direction) {
        int position = pager.getCurrentPosition();
        BackgroundBitmapCache instance = BackgroundBitmapCache.getInstance();
        String mainBgImageDrawableResource = pager.getDataFromAdapterDataset(position).getMainBackgroundResource();
        Integer key=pager.getDataFromAdapterDataset(position).getKey();
        String url=pager.getDataFromAdapterDataset(position).getMainBackgroundResource();
        if (key!=null) {
            Bitmap cachedBitmap = instance.getBitmapFromBgMemCache(key);
            /*if (cachedBitmap == null) {
                if (mainBgImageDrawableResource == null) return;
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                try {
                    URL url = new URL(mainBgImageDrawableResource);
                    cachedBitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
                    instance.addBitmapToBgMemoryCache(key, cachedBitmap);
                } catch (IOException e) {
                    //Log.e(TAG, e.getMessage());
                }

            }*/

            setImageBitmapWithAnimation(url, direction);
        }
    }

    public void updateCurrentBackgroundAsync(ECPager pager, final AnimationDirection direction) {
        if (mCurrentAnimationTask != null && mCurrentAnimationTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
            getInAnimation().cancel();
        }
        int position = pager.getCurrentPosition();
        Integer mainBgImageDrawableResource = pager.getDataFromAdapterDataset(position).getKey();
        String url = pager.getDataFromAdapterDataset(position).getMainBackgroundResource();
        if (mainBgImageDrawableResource == null) return;
        setImageBitmapWithAnimation(url, direction);
       /* mCurrentAnimationTask = new BitmapWorkerTask(getResources(), mainBgImageDrawableResource) {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                setImageBitmapWithAnimation(bitmap, direction);
            }
        };
        mCurrentAnimationTask.execute(mainBgImageDrawableResource);*/
    }


    private void setImageBitmap(String bitmap) {
        ImageView image = (ImageView) this.getNextView();
        Glide.with(getContext()).load(bitmap).into(image);
        //image.setImageBitmap(bitmap);
        showNext();
    }

    private Animation createBgImageInAnimation(int fromX, int toX, int transitionDuration, int alphaDuration) {
        TranslateAnimation translate = new TranslateAnimation(fromX, toX, 0, 0);
        translate.setDuration(transitionDuration);

        AlphaAnimation alpha = new AlphaAnimation(0F, 1F);
        alpha.setDuration(alphaDuration);

        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(translate);
        set.addAnimation(alpha);
        return set;
    }

    private Animation createBgImageOutAnimation(int fromX, int toX, int transitionDuration) {
        TranslateAnimation ta = new TranslateAnimation(fromX, toX, 0, 0);
        ta.setDuration(transitionDuration);
        ta.setInterpolator(new DecelerateInterpolator());
        return ta;
    }

    enum AnimationDirection {
        LEFT, RIGHT
    }
}
