package com.gorokhov.weatherappgvc.presentation.search

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.gorokhov.weatherappgvc.presentation.root.RootStore.Intent
import com.gorokhov.weatherappgvc.presentation.root.RootStore.State
import com.gorokhov.weatherappgvc.presentation.root.RootStore.Label

internal interface SearchStore : Store<Intent, State, Label> {

    sealed interface Intent {}

    data class State(val todo: Unit)

    sealed interface Label {
    }
}

internal class SearchStoreFactory(
    private val storeFactory: StoreFactory
) {

    fun create(): SearchStore = object : SearchStore,
        Store<Intent, State, Label> by storeFactory.create(
            name = "SearchStore",
            initialState = State(Unit),
            reducer = ReducerImpl,
            executorFactory = ::ExecutorImpl
        ) {}

    private sealed interface Action

    private sealed interface Msg {}

    private inner class BootstrapperImpl: CoroutineBootstrapper<Action>() {

        override fun invoke() {

        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action,
            State, Msg, Label>() {

        override fun executeIntent(
            intent: Intent,
            getState: () -> State
        ) {

        }

        override fun executeAction(
            action: Action,
            getState: () -> State
        ) {

        }
    }

    private object ReducerImpl : Reducer<State, Msg> {

        override fun State.reduce(msg: Msg): State = State(Unit)
    }
}


