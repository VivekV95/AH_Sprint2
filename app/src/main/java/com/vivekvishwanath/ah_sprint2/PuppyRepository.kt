package com.vivekvishwanath.ah_sprint2

class PuppyRepository {
    companion object {
        val puppyList = mutableListOf<Puppy>()
        fun createPuppyList() {
            for (i in 0 until puppyNames.size) {
                puppyList.add(Puppy(puppyNames[i], puppyIds[i], false))
            }
        }
    }
}