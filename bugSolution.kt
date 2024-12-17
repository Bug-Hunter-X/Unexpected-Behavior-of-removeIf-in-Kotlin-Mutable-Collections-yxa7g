The unexpected behavior stems from how `removeIf` iterates and modifies the collection.  In the `MutableList` example, `removeIf` removes elements directly from the list as it iterates, causing no problems.  However, with `MutableMap`, `removeIf`'s `it` is a key-value pair. Removing a key-value pair in the middle of the iteration will affect subsequent iteration steps. The best practice is to create a copy of the keys and iterate over the copy, removing elements from the original map.

Here's a safer approach:

```kotlin
fun main() {
    val map = mutableMapOf("a" to 1, "b" to 2, "c" to 3)
    val keysToRemove = map.filter { it.value % 2 == 0 }.keys
    keysToRemove.forEach { map.remove(it) }
    println(map) // Output: {a=1, c=3}
}
```
This ensures predictable and consistent removal from `MutableMap`.  Always exercise caution when modifying collections while iterating over them.