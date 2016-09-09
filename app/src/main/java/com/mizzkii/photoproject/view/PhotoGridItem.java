package com.mizzkii.photoproject.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mizzkii.photoproject.R;

/**
 * Created by MizzKii on 9/4/2016 AD.
 */
public class PhotoGridItem extends FrameLayout {

    ImageView ivImg;
    TextView tvName;
    TextView tvDescription;

    public PhotoGridItem(Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public PhotoGridItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
        initWithAttrs(attrs, 0, 0);
    }

    public PhotoGridItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public PhotoGridItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.grid_item_photo, this);
    }

    private void initInstance() {
        ivImg = (ImageView) findViewById(R.id.ivImg);
        tvName = (TextView) findViewById(R.id.tvName);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
    }

    private void initWithAttrs(AttributeSet attrs, int i, int i1) {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width;

        int newWidth = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int newHeight = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(newWidth, newHeight);

        setMeasuredDimension(newWidth, newHeight);
    }

    public void setNameText(String text) {
        tvName.setText(text);
    }

    public void setDescriptionText(String text) {
        tvDescription.setText(text);
    }

    public void setImageUrl(String url) {
        Glide.with(getContext())
                .load(url)
                .placeholder(R.drawable.loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImg);
    }
}
