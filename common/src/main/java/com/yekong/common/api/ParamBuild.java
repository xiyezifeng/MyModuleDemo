package com.yekong.common.api;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

/**
 * Created by xigua on 2017/8/1.
 */

public class ParamBuild {
    private String api;
    private List<ParamItem> paramItems;
    private List<String> imgs;
    private static ParamBuild paramBuild;

    private ParamBuild(){}

    public static ParamBuild create(){
        paramBuild = new ParamBuild();
        return paramBuild;
    }

    public ParamBuild api(String api) {
        this.api = api;
        return paramBuild;
    }

    public ParamBuild params(String key, String value) {
        if (this.paramItems == null) {
            this.paramItems = new ArrayList<>();
        }
        this.paramItems.add(new ParamItem(key, value));
        return paramBuild;
    }
    public ParamBuild image(String img) {
        if (this.imgs == null) {
            imgs = new ArrayList<>();
        }
        imgs.add(img);
        return paramBuild;
    }
    public ParamBuild images(List<String> imgs) {
        if (this.imgs == null || this.imgs.isEmpty()) {
            this.imgs = imgs;
        }else{
            this.imgs.addAll(imgs);
        }
        return paramBuild;
    }

    public void buildString(OnBuildString back) {
        String[][] p = new String[][]{};
        if (paramItems != null) {
            p = new String[paramItems.size()][2];
            for (int i = 0; i < paramItems.size(); i++) {
                p[i][0] = paramItems.get(i).key;
                p[i][1] = paramItems.get(i).value;
            }
        }
//        back.buildSuccess(Api.getInstance().buildParams(api, p));
    }


    public void buildPart(final OnBuildPart back) {
        String[][] p = new String[][]{};
        if (paramItems != null) {
            p = new String[paramItems.size()][2];
            for (int i = 0; i < paramItems.size(); i++) {
                p[i][0] = paramItems.get(i).key;
                p[i][1] = paramItems.get(i).value;
            }
        }

        String[] pic = new String[]{};
        if (imgs != null) {
            pic = new String[imgs.size()];
            for (int i = 0; i < imgs.size(); i++) {
                pic[i] = imgs.get(i);
            }
        }
        //body -> back.buildSuccess(body)
        /*Api.getInstance().buildParamWithPic(api, pic, p, new Api.OnParamsCallback() {
            @Override
            public void onCallBack(List<MultipartBody.Part> body) {
                back.buildSuccess(body);
            }
        });*/
    }


    public interface OnBuildString {
        void buildSuccess(String value);
    }

    public interface OnBuildPart {
        void buildSuccess(List<MultipartBody.Part> body);
    }

    public class ParamItem {
        public String key;
        public String value;

        public ParamItem(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
