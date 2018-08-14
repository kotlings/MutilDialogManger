package com.mythace.dialogplus.bean;

import android.support.annotation.IntDef;
import android.support.annotation.RestrictTo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;
import static com.mythace.dialogplus.bean.LEVEL.PRIORITY_HEIGHT;
import static com.mythace.dialogplus.bean.LEVEL.PRIORITY_PRIOR;
import static com.mythace.dialogplus.bean.LEVEL.PRIORITY_NORMAL;


@RestrictTo(LIBRARY_GROUP)
@IntDef({PRIORITY_PRIOR, PRIORITY_NORMAL, PRIORITY_HEIGHT})
@Retention(RetentionPolicy.SOURCE)
public @interface LEVEL {

    int PRIORITY_NORMAL = 0x001 >> 1;
    int PRIORITY_PRIOR = 0x001;
    int PRIORITY_HEIGHT = 0x001 << 1;


}
