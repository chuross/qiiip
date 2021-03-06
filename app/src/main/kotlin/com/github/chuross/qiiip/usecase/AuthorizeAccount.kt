package com.github.chuross.qiiip.usecase

import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.domain.user.UserConverter
import com.github.chuross.qiiip.infrastructure.qiita.v2.QiitaV2Api
import com.github.chuross.qiiip.infrastructure.qiita.v2.parameter.TokenParameter
import io.reactivex.Single
import javax.inject.Inject

class AuthorizeAccount(private val code: String) : BaseRxUseCase<User>() {
    @Inject
    lateinit var application: Application

    @Inject
    lateinit var api: QiitaV2Api

    override fun source(): Single<User> {
        return api.login(TokenParameter(Settings.qiita.clientId, Settings.qiita.clientSecret, code))
                .filter { it.value?.isNotBlank() ?: false }
                .doOnSuccess { application.accountPreferences.token = it.value }
                .flatMapSingle { api.getAuthenticatedUser() }
                .map {
                    UserConverter.toModel(it).also {
                        application.accountPreferences.user = it
                    }
                }.doOnError { application.accountPreferences.removeToken() }
    }
}