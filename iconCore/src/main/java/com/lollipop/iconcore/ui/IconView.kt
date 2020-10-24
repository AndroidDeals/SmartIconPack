package com.lollipop.iconcore.ui

/**
 * @author lollipop
 * @date 10/24/20 20:28
 */
interface IconView {

    /**
     * 以id的形式加载一个icon
     */
    fun loadIcon(iconId: Int)

    /**
     * 以名称的形式加载一个icon
     */
    fun loadIcon(iconName: String)

}