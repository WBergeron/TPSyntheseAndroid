package ca.qc.cstj.tpsynthese.data.repositories

import ca.qc.cstj.tenretni.core.ApiResult
import ca.qc.cstj.tenretni.core.Constants
import ca.qc.cstj.tpsynthese.data.datasources.GatewayDataSource
import ca.qc.cstj.tpsynthese.domain.models.Gateway
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GatewayRepository {
    private val gatewayDataSource = GatewayDataSource()

    fun retrieveAll() : Flow<ApiResult<List<Gateway>>> {
        var href = "${Constants.BaseURL.BASE_API}${Constants.BaseURL.GATEWAYS}"
        return flow {
            emit(ApiResult.Loading)
            try {
                emit(ApiResult.Success(gatewayDataSource.retrieveAll(href)))
            }catch (ex:Exception){
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }
    fun retrieveAllForCustomer(href: String) : Flow<ApiResult<List<Gateway>>> {
        var href = "${href}${Constants.BaseURL.GATEWAYS}"
        return flow {
            while (true){
                emit(ApiResult.Loading)
                try {
                    emit(ApiResult.Success(gatewayDataSource.retrieveAllForCustomer(href)))
                }catch (ex:Exception){
                    emit(ApiResult.Error(ex))
                }
                delay(Constants.RefreshDelay.DETAIL_TICKET)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun retrieveOne(href: String) : Flow<ApiResult<Gateway>> {
        return flow {
            while (true) {
                emit(ApiResult.Loading)
                try {
                    emit(ApiResult.Success(gatewayDataSource.retrieveOne(href)))
                } catch (ex: Exception) {
                    emit(ApiResult.Error(ex))
                }
                delay(Constants.RefreshDelay.DETAIL_GATEWAY)
            }
        }.flowOn(Dispatchers.IO)
    }
    fun create(gateway: String, href: String) : Flow<ApiResult<Gateway>> {
        var href = "${href}${Constants.BaseURL.GATEWAYS}"
        return flow {
            try {
                emit(ApiResult.Success(gatewayDataSource.create(gateway, href)))
            }catch (ex: java.lang.Exception) {
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }
    fun reboot(href:String) : Flow<ApiResult<Gateway>>{
        val href = "${href}${Constants.BaseURL.TYPEREBOOT}"
        return flow {
            try {
                emit(ApiResult.Success(gatewayDataSource.reboot(href)))
            }catch (ex: Exception){
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }
    fun update(href:String) : Flow<ApiResult<Gateway>>{
        val href = "${href}${Constants.BaseURL.TYPEUPDATE}"
        return flow {
            try {
                emit(ApiResult.Success(gatewayDataSource.update(href)))
            }catch (ex: Exception){
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }
}