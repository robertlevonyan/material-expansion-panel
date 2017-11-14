package com.robertlevonyan.views.expandable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by robertlevonyan on 9/28/17.
 */

public class Expandable extends FrameLayout {
    private Drawable icon;
    private int iconStyle;
    private boolean animateExpand;
    private int backgroundColor;
    private int headerBackgroundColor;
    private Drawable expandIndicator;

    private FrameLayout bgCard;
    private ImageView iconImage;
    private ImageView expandIcon;

    private boolean isExpanded;
    private int expandableViewHeight;
    private int collapsedViewHeight;

    private View headerView;
    private View expandableView;
    private LayoutParams expandableViewParams;

    private ExpandingListener expandingListener;
    private boolean isCreated;

    public Expandable(Context context) {
        this(context, null, 0);
    }

    public Expandable(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Expandable(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(attrs);
        initView();
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs == null) return;

        TypedArray ta = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.Expandable, 0, 0);

        icon = ta.getDrawable(R.styleable.Expandable_exp_icon);
        iconStyle = ta.getInt(R.styleable.Expandable_exp_iconStyle, ExpandableUtils.ICON_STYLE_SQUARE);
        animateExpand = ta.getBoolean(R.styleable.Expandable_exp_animateExpand, false);
        backgroundColor = ta.getColor(R.styleable.Expandable_exp_backgroundColor, ContextCompat.getColor(getContext(), R.color.colorDefaultBackground));
        headerBackgroundColor = ta.getColor(R.styleable.Expandable_exp_headerBackgroundColor, ContextCompat.getColor(getContext(), R.color.colorDefaultBackground));
        expandIndicator = ta.getDrawable(R.styleable.Expandable_exp_expandIndicator);

        ta.recycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isCreated) {
            buildView();
            isCreated = true;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (!isCreated) {
            buildView();
            isCreated = true;
        }
    }

    private void buildView() {
        headerView = getChildAt(1);
        expandableView = getChildAt(2);

        if (headerView == null) {
            return;
        }

        if (expandableView == null) {
            return;
        }

        if (getChildCount() > 3) {
            throw new IllegalStateException("ExpandableView can have only two child views" + getChildCount());
        }

        removeView(headerView);
        removeView(expandableView);

        expandableViewParams = (FrameLayout.LayoutParams) expandableView.getLayoutParams();
        expandableView.measure(0, 0);
        expandableViewHeight = expandableView.getMeasuredHeight() + collapsedViewHeight;
        expandableViewParams.height = 0;
        expandableViewParams.setMargins(0, collapsedViewHeight, 0, 0);
        expandableView.setLayoutParams(expandableViewParams);

        bgCard.addView(expandableView);
        buildHeader();
        initExpandableClick();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams thisParams = getLayoutParams();

        thisParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        thisParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        this.setLayoutParams(thisParams);
    }

    private void initView() {
        collapsedViewHeight = (int) getResources().getDimension(R.dimen.collapsed_size);
        initBackground();
    }

    private void initBackground() {
        if (bgCard == null) {
            bgCard = new FrameLayout(getContext());
        }

        LinearLayout.LayoutParams bgParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bgCard.setLayoutParams(bgParams);
        bgCard.setLayoutParams(bgParams);
        bgCard.setBackgroundColor(backgroundColor);

        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).equals(bgCard)) return;
        }
        addView(bgCard);
        setBackgroundColor(headerBackgroundColor);
    }

    private void buildHeader() {
        RelativeLayout headerLayout = new RelativeLayout(getContext());
        FrameLayout.LayoutParams headerViewParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, collapsedViewHeight);

        headerLayout.setLayoutParams(headerViewParams);
        bgCard.addView(headerLayout);
        bgCard.setBackgroundColor(headerBackgroundColor);

        initHeaderIcon(headerLayout);
        initExpandIcon(headerLayout);
        initHeaderContent(headerLayout);
    }

    private void initHeaderIcon(RelativeLayout headerView) {
        if (icon == null) return;

        if (iconImage == null) {
            iconImage = new ImageView(getContext());
        }

        switch (iconStyle) {
            case ExpandableUtils.ICON_STYlE_CIRCLE:
                iconImage.setImageBitmap(ExpandableUtils.getCircleBitmap(icon, backgroundColor));
                break;
            case ExpandableUtils.ICON_STYlE_ROUNDED_SQUARE:
                iconImage.setImageBitmap(ExpandableUtils.getRoundedSquareBitmap(icon, backgroundColor));
                break;
            default:
                iconImage.setImageDrawable(icon);
                break;
        }
        iconImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        int margin = (int) getContext().getResources().getDimension(R.dimen.icon_margin);

        RelativeLayout.LayoutParams iconParams = new RelativeLayout.LayoutParams((int) getResources().getDimension(R.dimen.collapsed_icon_size), (int) getResources().getDimension(R.dimen.collapsed_icon_size));
        iconParams.setMargins(margin, margin, margin, margin);
        iconImage.setLayoutParams(iconParams);
        iconImage.setId(ExpandableUtils.ID_HEADER_ICON);

        headerView.addView(iconImage);
    }

    private void initExpandIcon(RelativeLayout headerLayout) {
        expandIcon = new ImageView(getContext());

        int margin = (int) getContext().getResources().getDimension(R.dimen.icon_margin);

        RelativeLayout.LayoutParams expandIconParams = new RelativeLayout.LayoutParams((int) getResources().getDimension(R.dimen.expand_drawable_size), (int) getResources().getDimension(R.dimen.expand_drawable_size));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            expandIconParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        } else {
            expandIconParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
        expandIconParams.addRule(RelativeLayout.CENTER_VERTICAL);
        expandIconParams.setMargins(margin, margin, margin, margin);

        expandIcon.setId(ExpandableUtils.ID_EXPAND_ICON);
        expandIcon.setLayoutParams(expandIconParams);
        expandIcon.setImageDrawable(expandIndicator == null ? ContextCompat.getDrawable(getContext(), R.drawable.ic_down) : expandIndicator);

        headerLayout.addView(expandIcon);
    }

    private void initHeaderContent(RelativeLayout headerLayout) {
        FrameLayout headerContent = new FrameLayout(getContext());

        int margin = (int) getContext().getResources().getDimension(R.dimen.icon_margin);

        RelativeLayout.LayoutParams headerContentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            headerContentParams.addRule(RelativeLayout.END_OF, ExpandableUtils.ID_HEADER_ICON);
            headerContentParams.addRule(RelativeLayout.START_OF, ExpandableUtils.ID_EXPAND_ICON);
        } else {
            headerContentParams.addRule(RelativeLayout.RIGHT_OF, ExpandableUtils.ID_HEADER_ICON);
            headerContentParams.addRule(RelativeLayout.LEFT_OF, ExpandableUtils.ID_EXPAND_ICON);
        }
        headerContentParams.addRule(RelativeLayout.CENTER_VERTICAL);

        headerContentParams.setMargins(margin, margin / 2, margin, margin / 2);

        headerContent.setLayoutParams(headerContentParams);
        headerContent.addView(headerView);

        headerLayout.addView(headerContent);
    }

    private void initExpandableClick() {
        bgCard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (animateExpand) {
                    if (!isExpanded) {
                        expandWithAnimation();
                    } else {
                        collapseWithAnimation();
                    }
                } else {
                    if (!isExpanded) {
                        expand();
                    } else {
                        collapse();
                    }
                }
            }
        });
    }

    private void expand() {
        expandableViewParams.height = expandableViewHeight;
        expandableView.setLayoutParams(expandableViewParams);
        expandIcon.setRotation(180f);
        isExpanded = true;
        if (expandingListener != null) {
            expandingListener.onExpanded();
        }
    }

    private void expandWithAnimation() {
        ValueAnimator sizeAnimator = ValueAnimator.ofFloat(1f, 1.2f);
        sizeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();

                if (iconImage != null) {
                    iconImage.setScaleX(value);
                    iconImage.setScaleY(value);
                }
            }
        });

        ValueAnimator translateAnimator = ValueAnimator.ofFloat(1f, 10f);
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();

                if (iconImage != null) {
                    iconImage.setTranslationX(value);
                    iconImage.setTranslationY(value);
                }
                headerView.setTranslationY(value / 2);
            }
        });

        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(expandIcon, "rotation", 0f, 180f);

        ValueAnimator expandAnimator = ValueAnimator.ofInt(0, expandableViewHeight);
        expandAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                expandableViewParams.height = (int) valueAnimator.getAnimatedValue();
                expandableView.setLayoutParams(expandableViewParams);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(sizeAnimator, translateAnimator, rotationAnimator, expandAnimator);
        set.setInterpolator(new DecelerateInterpolator());
        set.setDuration(200);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (expandingListener != null) {
                    expandingListener.onExpanded();
                }
            }
        });

        set.start();
        isExpanded = true;
    }

    private void collapse() {
        expandableViewParams.height = 0;
        expandableView.setLayoutParams(expandableViewParams);
        expandIcon.setRotation(0);
        isExpanded = false;
        if (expandingListener != null) {
            expandingListener.onCollapsed();
        }
    }

    private void collapseWithAnimation() {
        ValueAnimator sizeAnimator = ValueAnimator.ofFloat(1.2f, 1f);
        sizeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();

                if (iconImage != null) {
                    iconImage.setScaleX(value);
                    iconImage.setScaleY(value);
                }
            }
        });

        ValueAnimator translateAnimator = ValueAnimator.ofFloat(10f, 1f);
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();

                if (iconImage != null) {
                    iconImage.setTranslationX(value);
                    iconImage.setTranslationY(value);
                }
                headerView.setTranslationY(value / 2);
            }
        });

        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(expandIcon, "rotation", 180f, 0f);

        ValueAnimator expandAnimator = ValueAnimator.ofInt(expandableViewHeight, 0);
        expandAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                expandableViewParams.height = (int) valueAnimator.getAnimatedValue();
                expandableView.setLayoutParams(expandableViewParams);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(sizeAnimator, translateAnimator, rotationAnimator, expandAnimator);
        set.setInterpolator(new DecelerateInterpolator());
        set.setDuration(200);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (expandingListener != null) {
                    expandingListener.onCollapsed();
                }
            }
        });

        set.start();
        isExpanded = false;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getIconStyle() {
        return iconStyle;
    }

    public void setIconStyle(int iconStyle) {
        if (iconStyle < 0 || iconStyle > 3) {
            throw new IllegalStateException("Icon style must be one of the following: " +
                    "ExpandableUtils.ICON_STYLE_SQUARE, ExpandableUtils.ICON_STYlE_CIRCLE, ExpandableUtils.ICON_STYlE_ROUNDED_SQUARE");
        }
        this.iconStyle = iconStyle;
    }

    public boolean isAnimateExpand() {
        return animateExpand;
    }

    public void setAnimateExpand(boolean animateExpand) {
        this.animateExpand = animateExpand;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void changeBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getHeaderBackgroundColor() {
        return headerBackgroundColor;
    }

    public void setHeaderBackgroundColor(int headerBackgroundColor) {
        this.headerBackgroundColor = headerBackgroundColor;
    }

    public void setExpandIndicatorDrawable(Drawable expandIndicator) {
        this.expandIndicator = expandIndicator;
    }

    public void setExpandIndicator(@DrawableRes int expandIndicatorRes) {
        expandIndicator = ContextCompat.getDrawable(getContext(), expandIndicatorRes);
    }

    public void setExpandingListener(ExpandingListener expandingListener) {
        this.expandingListener = expandingListener;
    }
}
