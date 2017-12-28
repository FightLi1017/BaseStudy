package fight.android.lcx.typeadapter;

import android.support.annotation.IntRange;

/**
 * @author：lichenxi
 * @date 2017/12/8 11
 * email：525603977@qq.com
 * Fighting
 */
public interface Linker<T> {
    /**
     * Return the index of your registered binders for you item
     * @param position the position in items
     * @param  t       you item data
     * @return The index of your registered binders
     */
   @IntRange(from = 0) public int index(int position, T t);
}
