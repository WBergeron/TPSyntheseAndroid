package ca.qc.cstj.tpsynthese.ui.ticket.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.tenretni.core.ApiResult
import ca.qc.cstj.tenretni.core.Constants
import ca.qc.cstj.tpsynthese.data.repositories.GatewayRepository
import ca.qc.cstj.tpsynthese.data.repositories.TicketRepository
import ca.qc.cstj.tpsynthese.domain.models.Customer
import ca.qc.cstj.tpsynthese.domain.models.Gateway
import ca.qc.cstj.tpsynthese.domain.models.Ticket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailTicketViewModel(private val href : String):ViewModel() {
    private val ticketRepository = TicketRepository()
    private val gatewayRepository = GatewayRepository()
    private val _detailTicketUIState = MutableStateFlow<DetailTicketUIState>(DetailTicketUIState.Loading)
    private var gateways = mutableListOf<Gateway>()
    val detailTicketUiState = _detailTicketUIState.asStateFlow()
    private var ticket:Ticket = Ticket()

    fun retrieveOne(){
        viewModelScope.launch {
            ticketRepository.retrieveOne(href).collect(){ apiResult ->
                _detailTicketUIState.update {
                    when(apiResult){
                        is ApiResult.Error -> DetailTicketUIState.Error(apiResult.exception)
                        ApiResult.Loading -> DetailTicketUIState.Loading
                        is ApiResult.Success ->{
                            ticket = apiResult.data
                            DetailTicketUIState.SuccessTicket(ticket)
                        }
                    }
                }
            }
        }
    }
    fun addGateway(rawValue: String) {
        viewModelScope.launch {
            gatewayRepository.create(rawValue, ticket.customer.href).collect{ apiResult ->
                _detailTicketUIState.update {
                    when(apiResult) {
                        is ApiResult.Error -> DetailTicketUIState.GatewayInstallError(apiResult.exception)
                        ApiResult.Loading -> DetailTicketUIState.Loading
                        is ApiResult.Success -> {
                            gateways.add(apiResult.data)
                            DetailTicketUIState.GatewayInstallSuccess(gateways)
                        }
                    }
                }

            }
        }
    }
    fun getGateways(){
        viewModelScope.launch {
            gatewayRepository.retrieveAllForCustomer(ticket.customer.href).collect(){apiResult->
                _detailTicketUIState.update {
                    when(apiResult){
                        is ApiResult.Error -> DetailTicketUIState.Error(apiResult.exception)
                        ApiResult.Loading -> DetailTicketUIState.Loading
                        is ApiResult.Success ->{
                            gateways = apiResult.data.toMutableList()
                            DetailTicketUIState.SuccessGateways(apiResult.data)
                        }
                    }
                }
            }

        }
    }

    fun solveTicket() {
        viewModelScope.launch {
            ticketRepository.postSolveOne(href).collect(){apiResult->
                _detailTicketUIState.update {
                    when(apiResult){
                        is ApiResult.Error -> DetailTicketUIState.Error(apiResult.exception)
                        ApiResult.Loading -> DetailTicketUIState.Loading
                        is ApiResult.Success -> DetailTicketUIState.SuccessTicket(apiResult.data)
                    }
                }
            }
        }
    }

    fun openTicket() {
        viewModelScope.launch {
            ticketRepository.postOpenOne(href).collect(){apiResult->
                _detailTicketUIState.update {
                    when(apiResult){
                        is ApiResult.Error -> DetailTicketUIState.Error(apiResult.exception)
                        ApiResult.Loading -> DetailTicketUIState.Loading
                        is ApiResult.Success -> DetailTicketUIState.SuccessTicket(apiResult.data)
                    }
                }
            }
        }
    }

    class Factory(private val href: String): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance(href)
        }
    }
}