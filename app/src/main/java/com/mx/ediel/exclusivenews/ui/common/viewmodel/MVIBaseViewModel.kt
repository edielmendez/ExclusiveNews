package com.mx.ediel.exclusivenews.ui.common.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MVIBaseViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> : ViewModel() {

    // Create Initial State of View
    private val initialState : State by lazy { createInitialState() }
    abstract fun createInitialState() : State

    // Get Current State
    /*private val currentState: State
        get() = uiState.value*/

    var state: State by mutableStateOf(initialState)
    private set

    private val _uiState : MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event : MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect : Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeEvents()
    }


    /**
     * Set new Event
     */
    fun setEvent(event : Event) {
        viewModelScope.launch { _event.emit(event) }
    }


    /**
     * Set new Ui State
     */
    protected fun setState(reduce: State.() -> State) {
        /*val newState = currentState.reduce()
        _uiState.value = newState*/
        _uiState.value.reduce().also { reducedState ->
            _uiState.update{
                reducedState
            }
            state = reducedState
        }
    }

    /**
     * Set new Effect
     */
    protected fun setEffect(builder: () -> Effect) {
        viewModelScope.launch { _effect.send(builder()) }
    }

    /**
     * Start listening to Event
     */
    private fun subscribeEvents() {
        /*viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }*/
        viewModelScope.launch { event.collect(::handleEvent) }
    }

    /**
     * Handle each event
     */
    abstract fun handleEvent(event : Event)

}