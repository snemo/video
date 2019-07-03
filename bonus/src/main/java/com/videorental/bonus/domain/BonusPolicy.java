package com.videorental.bonus.domain;

import com.videorental.bonus.exception.BonusException;
import com.videorental.common.movie.MovieCategory;

/**
 * Can be extended in future to store Bonus points policy in the store.
 *
 * @author oleciwoj
 */
class BonusPolicy {

    static int points(MovieCategory category) {
        switch (category) {
            case NEW_RELEASE:
                return 2;
            case REGULAR:
            case OLD:
                return 1;
            default:
                throw new BonusException("Cannot obtain bonus policy. Not supported category: " + category);
        }
    }
}
