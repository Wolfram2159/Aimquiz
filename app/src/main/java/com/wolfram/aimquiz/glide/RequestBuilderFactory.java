package com.wolfram.aimquiz.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestListener;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.database.Team;
import com.wolfram.aimquiz.glide.svg.SvgSoftwareLayerSetter;

/**
 * @author Wolfram
 * @date 2019-05-25
 */
public class RequestBuilderFactory {
    private Context context;

    public RequestBuilderFactory(Context context) {
        this.context = context;
    }

    public RequestBuilder getTeamRequestBuilder(int hltv_id, RequestListener<Drawable> requestListener) {

        Uri uri = Uri.parse(Team.HLTV_URL + hltv_id);

        RequestBuilder svgRequestBuilder =
                Glide.with(context)
                        .as(PictureDrawable.class)
                        .listener(new SvgSoftwareLayerSetter());

        return Glide
                .with(context)
                .load(svgRequestBuilder)
                .error(Glide
                        .with(context)
                        .load(uri))
                .listener(requestListener);
    }

    public RequestBuilder getPlayerRequestBuilder(int hltv_id, RequestListener<Drawable> requestListener) {
        Uri pngUri = Uri.parse(Player.HLTV_URL_PNG + hltv_id + ".png");
        Uri jpegUri = Uri.parse(Player.HLTV_URL_JPEG + hltv_id + "/400.jpeg");
        return Glide
                .with(context)
                .load(pngUri)
                .error(Glide
                        .with(context)
                        .load(jpegUri)
                )
                .listener(requestListener);
    }
}
