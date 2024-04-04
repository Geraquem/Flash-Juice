package com.mmfsin.flashjuice.di

import com.mmfsin.flashjuice.data.repository.FJRepository
import com.mmfsin.flashjuice.domain.interfaces.IFJRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface FJRepositoryModule {
    @Binds
    fun bind(repository: FJRepository): IFJRepository
}