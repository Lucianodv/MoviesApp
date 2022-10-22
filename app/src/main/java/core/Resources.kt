package core

import java.lang.Exception

sealed class Resources <out T> {

    //retornar estados

    //ESTADO DE CARGA
    class Loading<out T>: Resources<T>()


    //ESTADO DE EXITO
    data class succed<out T>(val data: T): Resources<T>()


    //ESTADO DE FALLA
    data class failure(val exception: Exception): Resources<Nothing>()



}