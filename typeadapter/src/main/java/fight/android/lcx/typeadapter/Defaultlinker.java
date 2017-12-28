package fight.android.lcx.typeadapter;

/**
 * @author：lichenxi
 * @date 2017/12/8 13
 * email：525603977@qq.com
 * Fighting
 */
public class Defaultlinker<T> implements Linker<T> {

    @Override
    public int index(int position, T t) {
        return 0;
    }
}
