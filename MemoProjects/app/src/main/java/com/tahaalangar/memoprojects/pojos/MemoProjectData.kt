package com.tahaalangar.memoprojects.pojos

import java.io.Serializable

data class MemoProjectData(
    val all_categories: List<AllCategory>,
    val all_posts: List<AllPost>
):Serializable