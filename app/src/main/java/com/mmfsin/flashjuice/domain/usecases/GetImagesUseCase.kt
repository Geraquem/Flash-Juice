package com.mmfsin.flashjuice.domain.usecases

import android.widget.ImageView
import com.mmfsin.flashjuice.base.BaseUseCase
import com.mmfsin.flashjuice.databinding.IncludeDashboardBinding
import javax.inject.Inject

class GetImagesUseCase @Inject constructor() :
    BaseUseCase<GetImagesUseCase.Params, List<ImageView>>() {

    override suspend fun execute(params: Params): List<ImageView> {
        val images = mutableListOf<ImageView>().apply {
            add(params.images.image1)
            add(params.images.image2)
            add(params.images.image3)
            add(params.images.image4)
            add(params.images.image5)
            add(params.images.image6)
            add(params.images.image7)
            add(params.images.image8)
            add(params.images.image9)
            add(params.images.image10)
            add(params.images.image11)
            add(params.images.image12)
            add(params.images.image13)
            add(params.images.image14)
            add(params.images.image15)
            add(params.images.image16)
            add(params.images.image17)
            add(params.images.image18)
            add(params.images.image19)
            add(params.images.image20)
        }
        return images
    }

    data class Params(
        val images: IncludeDashboardBinding
    )
}