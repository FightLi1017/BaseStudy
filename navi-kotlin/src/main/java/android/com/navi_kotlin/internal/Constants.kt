package android.com.navi_kotlin.internal

/**
 * @author：lichenxi
 * @date 2018/7/11 15
 * email：525603977@qq.com
 * Fighting
 */
internal class Constants {
    /**
     * Acts as a signal for events that have no data associated with them.
     *
     * We reuse a single Object to avoid any extra allocations.
     */
    private constructor(){
        throw AssertionError("No instances!")
    }
    companion object {
        val SIGNAL=Any()
    }
}