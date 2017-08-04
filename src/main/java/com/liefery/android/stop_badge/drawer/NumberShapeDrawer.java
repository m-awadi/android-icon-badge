package com.liefery.android.stop_badge.drawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.liefery.android.stop_badge.StopBadge;

public class NumberShapeDrawer extends ForegroundShapeDrawer {

    private int number;

    private float scale;

    private Rect r = new Rect();

    public NumberShapeDrawer( int number ) {
        this.number = number;

        this.scale = calculateScale( number );
    }

    @Override
    public void draw(
        Canvas canvas,
        Paint paint,
        int size,
        float shadowSizeX,
        float shadowSizeY ) {
        paint.setTypeface( Typeface.create( Typeface.DEFAULT, Typeface.BOLD ) );

        paint.setTextSize( StopBadge.dpToPx( 36 ) * scale );
        drawCenter( canvas, paint, Integer.toString( number ) );
    }

    private void drawCenter( Canvas canvas, Paint paint, String text ) {
        canvas.getClipBounds( r );
        int cHeight = r.height();
        int cWidth = r.width();
        paint.setTextAlign( Paint.Align.CENTER );
        paint.getTextBounds( text, 0, text.length(), r );
        float x = cWidth / 2f;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText( text, x, y, paint );
    }

    private float calculateScale( int number ) {
        if ( number < 0 )
            throw new IllegalArgumentException(
                "Number too small - 0 allowed as min" );
        else if ( number <= 9 )
            return .7f;
        else if ( number <= 99 )
            return .6f;
        else if ( number <= 999 )
            return .5f;
        else if ( number <= 9999 )
            return .4f;
        else
            throw new IllegalArgumentException(
                "Number too big - 9999 allowed as max" );
    }

}
