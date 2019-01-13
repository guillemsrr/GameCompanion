package bernatriu.gotcompanion.utils

interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}