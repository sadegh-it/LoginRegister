package io.github.sadeghit.loginregister.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghit.loginregister.data.model.User
import io.github.sadeghit.loginregister.data.repository.LoginRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    private val _stateLogin = MutableStateFlow(false)
    val stateLogin: StateFlow<Boolean> = _stateLogin

    private val _stateRegister = MutableStateFlow(false)
    val stateRegister: StateFlow<Boolean> = _stateRegister

    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun login(username: String, password: String) {
        viewModelScope.launch {
            if (username.isBlank() || password.isBlank()) {
                _message.emit("نام کاربری و رمز عبور نم یتواند خالی باشد")
                return@launch
            }

            _isLoading.value = true
            val success = repository.userDataValidation(User(username, password))
            _isLoading.value = false

            _stateLogin.value = success
            _message.emit(if (success) "خوش آمدید!" else "اطلاعات نامعتبر است")
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            if (username.isBlank() || password.isBlank()) {
                _message.emit("نام کاربری و رمز عبور نمی تواند خالی باشد")
                return@launch
            }
            if (password.length < 6) {
                _message.emit("رمز عبور باید حداقل 6 کاراکتر باشد")
                return@launch
            }

            _isLoading.value = true
            repository.setUserData(User(username, password))
            _isLoading.value = false

            _stateRegister.value = true
            _message.emit("ثبت نام با موفقیت انجام شد")
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.setLoginState(false)
            _stateLogin.value = false
        }
    }

    suspend fun checkLoginStatus(): Boolean {
        return repository.isLoggedIn()
    }
}
