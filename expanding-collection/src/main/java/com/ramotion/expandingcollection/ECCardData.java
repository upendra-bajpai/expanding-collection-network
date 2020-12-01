package com.ramotion.expandingcollection;


import java.util.List;

import androidx.annotation.DrawableRes;

/**
 * Implement this interface to provide data to pager view and content list inside pager card
 *
 * @param <T> Type of items in card content list
 */
public interface ECCardData<T> {

    String getMainBackgroundResource();

    Integer getKey();

    String getHeadBackgroundResource();

    List<T> getListItems();
}
